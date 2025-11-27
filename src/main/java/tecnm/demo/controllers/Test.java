package tecnm.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    // Déjalo solo para ver si la API responde, sin lógica de productos
    @GetMapping("/hello")
    public String helloWorld() {
        return "¡El servidor está vivo y conectado!";
    }
}