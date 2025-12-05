package tecnm.demo.controllers;

import tecnm.demo.models.Categoria;
import tecnm.demo.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired private CategoriaRepository repo;

    @GetMapping
    public List<Categoria> listar() { return repo.findAll(); }

    @PostMapping
    public String crear(@RequestBody Categoria c) {
        repo.save(c);
        return "Categoría creada";
    }
}