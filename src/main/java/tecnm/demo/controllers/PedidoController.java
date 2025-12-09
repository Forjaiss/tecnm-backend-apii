package tecnm.demo.controllers;

import tecnm.demo.models.*;
import tecnm.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired private PedidoRepository pedidoRepo;
    @Autowired private CarritoRepository carritoRepo;
    @Autowired private ProductoRepository productoRepo;

    @PostMapping("/checkout")
    public String checkout(@RequestBody Pedido datos) {
        try {
            // 1. OBTENER CARRITO
            List<DetalleCarrito> carrito = carritoRepo.findByUsuario(datos.usuariosId);
            
            if (carrito == null || carrito.isEmpty()) {
                return "Error: El carrito está vacío o el usuario no existe.";
            }

            // 2. CALCULAR TOTAL (Dinero) 💰
            double totalProductos = 0.0;
            for (DetalleCarrito item : carrito) {
                if (item.precio != null && item.cantidad != null) {
                    totalProductos += (item.precio * item.cantidad);
                }
            }
            datos.importeProductos = totalProductos;

            // 3. CREAR PEDIDO
            Long idPedido = pedidoRepo.crearPedido(datos);

            // 4. PROCESAR DETALLES Y STOCK 📉
            for (DetalleCarrito item : carrito) {
                // Guardar historial
                pedidoRepo.guardarDetalle(idPedido, item.productosId, item.cantidad, item.precio);
                
                // Actualizar Stock (Resta del inventario)
                try {
                    Producto p = productoRepo.findById(item.productosId);
                    if (p != null) {
                        int stockActual = (p.stock != null) ? p.stock : 0;
                        int nuevoStock = stockActual - item.cantidad;
                        if (nuevoStock < 0) nuevoStock = 0;
                        
                        productoRepo.updateStock(item.productosId, nuevoStock);
                    }
                } catch (Exception ex) {
                    System.out.println("Advertencia: No se pudo actualizar stock del producto " + item.productosId);
                }
            }

            // 5. VACIAR CARRITO 🧹
            carritoRepo.deleteByUsuario(datos.usuariosId);

            return "¡Éxito! Pedido #" + idPedido + " generado. Total: $" + (totalProductos + datos.importeEnvio);

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR GRAVE DEL SISTEMA: " + e.getMessage();
        }
    }
}