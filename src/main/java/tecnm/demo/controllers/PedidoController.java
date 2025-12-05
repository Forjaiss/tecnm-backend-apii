package tecnm.demo.controllers;

import tecnm.demo.models.DetalleCarrito;
import tecnm.demo.models.Pedido;
import tecnm.demo.repositories.CarritoRepository;
import tecnm.demo.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired private PedidoRepository pedidoRepo;
    @Autowired private CarritoRepository carritoRepo;

    @PostMapping("/checkout")
    public String checkout(@RequestBody Pedido datos) {
        // 1. Leer carrito
        List<DetalleCarrito> carrito = carritoRepo.findByUsuario(datos.usuariosId);
        if (carrito.isEmpty()) return "El carrito está vacío";

        // 2. Calcular total
        double total = 0;
        for (DetalleCarrito item : carrito) total += (item.precio * item.cantidad);
        datos.importeProductos = total;

        // 3. Guardar Pedido
        Long idPedido = pedidoRepo.crearPedido(datos);

        // 4. Guardar Detalles
        for (DetalleCarrito item : carrito) {
            pedidoRepo.guardarDetalle(idPedido, item.productosId, item.cantidad, item.precio);
        }

        // 5. Borrar Carrito
        carritoRepo.deleteByUsuario(datos.usuariosId);

        return "Pedido #" + idPedido + " generado con éxito";
    }
}