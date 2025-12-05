package tecnm.demo.controllers;

import tecnm.demo.models.MetodoPago;
import tecnm.demo.repositories.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {
    @Autowired private MetodoPagoRepository repo;

    @GetMapping
    public List<MetodoPago> listar() { return repo.findAll(); }

    @PostMapping
    public String crear(@RequestBody MetodoPago m) {
        repo.save(m);
        return "Creado";
    }
}