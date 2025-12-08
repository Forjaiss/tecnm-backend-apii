package tecnm.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tecnm.demo.dtos.CheckoutDto;
import tecnm.demo.models.Venta;
import tecnm.demo.repositories.VentaRepository;

@RestController
@RequestMapping("/api")
public class CheckoutController {

    @Autowired
    private VentaRepository ventaRepository;

    @PostMapping("/checkout")
    public ResponseEntity<?> procesarCheckout(@RequestBody CheckoutDto datos) {
      
        Venta nuevaVenta = new Venta(
            datos.getUsuariosId(),
            datos.getMetodosPagoId(),
            datos.getImporteEnvio()
        );

       
        ventaRepository.save(nuevaVenta);

       
        return ResponseEntity.ok("{\"mensaje\": \"Venta registrada correctamente\"}");
    }
}