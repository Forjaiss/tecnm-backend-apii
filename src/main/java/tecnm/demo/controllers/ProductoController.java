package tecnm.demo.controllers;

import tecnm.demo.models.Producto;
import tecnm.demo.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository repositorio;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return repositorio.findAll();
    }
}