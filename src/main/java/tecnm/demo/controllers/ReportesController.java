package tecnm.demo.controllers;

import tecnm.demo.repositories.ReportesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
public class ReportesController {
    @Autowired private ReportesRepository repo;

    @GetMapping("/ventas") public List<Map<String, Object>> ventas() { return repo.ventas(); }
    @GetMapping("/envios") public List<Map<String, Object>> envios() { return repo.envios(); }
    @GetMapping("/inventario") public List<Map<String, Object>> inventario() { return repo.inventario(); }
    @GetMapping("/atencion") public List<Map<String, Object>> atencion() { return repo.atencion(); }
    @GetMapping("/gestion") public List<Map<String, Object>> gestion() { return repo.gestion(); }
    @GetMapping("/premium") public List<Map<String, Object>> premium() { return repo.premium(); }
    @GetMapping("/rendimiento") public List<Map<String, Object>> rendimiento() { return repo.rendimiento(); }
    @GetMapping("/resumen") public List<Map<String, Object>> resumen() { return repo.resumen(); }
}