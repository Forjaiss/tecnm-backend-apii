package tecnm.demo.models; // <--- IMPORTANTE

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public LocalDateTime fecha;
    public String numero; // UUID
    
    @Column(name = "importe_productos")
    public Double importeProductos;
    
    @Column(name = "importe_envio")
    public Double importeEnvio;

    // Estos campos se calculan solos en la BD (Columnas generadas)
    // Por eso ponemos insertable = false, updatable = false
    @Column(name = "importe_iva", insertable = false, updatable = false)
    public Double importeIva;

    @Column(name = "total", insertable = false, updatable = false)
    public Double total;

    @Column(name = "usuarios_id")
    public Long usuariosId;

    @Column(name = "metodos_pago_id")
    public Long metodosPagoId;

    public Pedido() {}
}