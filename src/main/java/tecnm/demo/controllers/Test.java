package tecnm.demo.controllers; // <-- Corregido

import org.springframework.http.ResponseEntity;
// Imports de Spring
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tecnm.models.Producto;

@RestController
public class Test {

    // Endpoint /hello
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hola API Rest!";
    }

    // Endpoint /producto (un solo producto)
    @GetMapping("/producto")
    public Producto getProducto() {
        Producto p = new Producto();
        p.nombre = "Coca Cola";
        p.precio = 18.5;
        p.codigoBarras = "7501055312306";
        return p;
    }

    // Endpoint /productos (lista de productos)
    @GetMapping("/productos")
    public Producto[] getProductos() {
        Producto p1 = new Producto();
        p1.nombre = "Coca Cola";
        p1.precio = 18.5;
        p1.codigoBarras = "7501055312306";

        Producto p2 = new Producto();
        p2.nombre = "Pepsi";
        p2.precio = 17.5;
        p2.codigoBarras = "7501055312307";

        Producto p3 = new Producto();
        p3.nombre = "Fanta";
        p3.precio = 16.5;
        p3.codigoBarras = "7501055312308";

        return new Producto[]{p1, p2, p3};
    }
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable int id) {
        
        // --- Creamos la lista de productos (igual que en el endpoint 3) ---
        // (En el futuro, esto vendría de una base de datos)
        Producto[] productos = new Producto[3];
        Producto p1 = new Producto();
        p1.nombre = "Coca Cola";
        p1.precio = 18.5;
        p1.codigoBarras = "7501055312306";
        productos[0] = p1;

        Producto p2 = new Producto();
        p2.nombre = "Pepsi";
        p2.precio = 17.5;
        p2.codigoBarras = "7501055312307";
        productos[1] = p2;

        Producto p3 = new Producto();
        p3.nombre = "Fanta";
        p3.precio = 16.5;
        p3.codigoBarras = "7501055312308";
        productos[2] = p3;
        // --- Fin de la lista ---


        // Lógica para encontrar el producto
        if (id < 0 || id >= productos.length) {
            // Si el ID está fuera de rango (ej. 3, 4, -1)
            // Devuelve un error 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Si encontramos el producto, lo devolvemos
        // con un código 200 OK
        return ResponseEntity.ok(productos[id]);
    }
}