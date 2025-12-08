package tecnm.demo.controllers;

import tecnm.demo.models.DetalleCarrito;
import tecnm.demo.repositories.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired private CarritoRepository repo;

    @PostMapping
    public String agregar(@RequestBody DetalleCarrito item) {
        
        DetalleCarrito existe = repo.findItem(item.usuariosId, item.productosId);

        if (existe != null) {
         
            int nuevaCant = existe.cantidad + item.cantidad;
            repo.updateCantidad(existe.id, nuevaCant);
            return "Cantidad actualizada a: " + nuevaCant;
        } else {
           
            repo.save(item);
            return "Producto agregado";
        }
    }

    @GetMapping("/{uid}")
    public List<DetalleCarrito> ver(@PathVariable Long uid) {
        return repo.findByUsuario(uid);
    }
    
    @DeleteMapping("/{uid}")
    public String vaciar(@PathVariable Long uid) {
        repo.deleteByUsuario(uid);
        return "Carrito vaciado";
    }
}