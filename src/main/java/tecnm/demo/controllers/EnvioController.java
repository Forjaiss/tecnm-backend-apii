package tecnm.demo.controllers;

import tecnm.demo.models.Envio;
import tecnm.demo.repositories.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {
    @Autowired private EnvioRepository repo;

    @GetMapping
    public List<Envio> listar() { return repo.findAll(); }

    // Actualizar estado: /api/envios/1?estado=ENTREGADO
    @PutMapping("/{id}")
    public String actualizar(@PathVariable Long id, @RequestParam String estado) {
        repo.updateEstado(id, estado);
        return "Estado actualizado";
    }
}