package tecnm.demo.controllers;

import tecnm.demo.models.Domicilio;
import tecnm.demo.repositories.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/domicilios")
public class DomicilioController {

    @Autowired
    private DomicilioRepository repositorio;

    @GetMapping
    public List<Domicilio> obtenerTodos() {
        return repositorio.findAll();
    }

    
    @GetMapping("/usuario/{id}")
    public List<Domicilio> obtenerPorUsuario(@PathVariable Long id) {
        return repositorio.findByUsuario(id);
    }

  
    @PostMapping
    public String guardarDomicilio(@RequestBody Domicilio domicilio) {
        repositorio.save(domicilio);
        return "Domicilio guardado con éxito";
    }
}