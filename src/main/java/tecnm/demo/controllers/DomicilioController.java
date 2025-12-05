package tecnm.demo.controllers;

import tecnm.demo.models.Domicilio;
import tecnm.demo.repositories.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/domicilios")
public class DomicilioController {
    @Autowired private DomicilioRepository repo;

    @GetMapping
    public List<Domicilio> todos() { return repo.findAll(); }

    @GetMapping("/usuario/{id}")
    public List<Domicilio> porUsuario(@PathVariable Long id) { return repo.findByUsuario(id); }

    @PostMapping
    public String crear(@RequestBody Domicilio d) {
        repo.save(d);
        return "Guardado";
    }

    @PutMapping("/{id}")
    public String actualizar(@PathVariable Long id, @RequestBody Domicilio d) {
        return (repo.update(id, d) > 0) ? "Actualizado" : "No encontrado";
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        return (repo.delete(id) > 0) ? "Eliminado" : "No encontrado";
    }
}