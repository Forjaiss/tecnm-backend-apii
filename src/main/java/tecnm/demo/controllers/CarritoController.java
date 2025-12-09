package tecnm.demo.controllers;

import tecnm.demo.models.DetalleCarrito;
import tecnm.demo.repositories.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carrito") // <--- ¡Esta línea es la clave para que no de 404!
public class CarritoController {

    @Autowired private CarritoRepository repo;

    // 1. Ver carrito de un usuario
    @GetMapping("/{idUsuario}")
    public List<DetalleCarrito> verCarrito(@PathVariable Long idUsuario) {
        return repo.findByUsuario(idUsuario);
    }

    // 2. Agregar producto al carrito
    @PostMapping
    public String agregar(@RequestBody DetalleCarrito item) {
        // Buscamos si ya existe para sumar cantidad, o creamos uno nuevo
        DetalleCarrito existente = repo.findItem(item.usuariosId, item.productosId);
        
        if (existente != null) {
            // Si ya existe, actualizamos
            repo.updateCantidad(existente.id, existente.cantidad + item.cantidad);
            return "Cantidad actualizada en el carrito";
        } else {
            // Si es nuevo, lo guardamos
            repo.save(item);
            return "Producto agregado al carrito";
        }
    }

    // 3. Borrar carrito (Opcional, para limpiar manual)
    @DeleteMapping("/{idUsuario}")
    public String limpiar(@PathVariable Long idUsuario) {
        repo.deleteByUsuario(idUsuario);
        return "Carrito vaciado";
    }
}