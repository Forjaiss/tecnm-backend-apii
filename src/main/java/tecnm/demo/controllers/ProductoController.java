package tecnm.demo.controllers;

import tecnm.demo.models.Producto;
import tecnm.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired private ProductoRepository repo;

    @GetMapping
    public List<Producto> todos() { return repo.findAll(); }

    @GetMapping("/{id}") 
    public ResponseEntity<Producto> uno(@PathVariable Long id) {
        Producto p = repo.findById(id);
        if(p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @PostMapping
    public String crear(@RequestBody Producto p) {
        repo.save(p);
        return "Producto creado";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody Producto p) {
        if(repo.update(id, p) > 0) return ResponseEntity.ok("Producto actualizado");
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        if(repo.delete(id) > 0) return ResponseEntity.ok("Producto eliminado");
        return ResponseEntity.notFound().build();
    }
}