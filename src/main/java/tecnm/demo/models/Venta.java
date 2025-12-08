package tecnm.demo.models;

import jakarta.persistence.*; 
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "metodo_pago_id")
    private Long metodoPagoId;

    @Column(name = "total")
    private Double total;

    private LocalDateTime fecha;

    public Venta() {}

    public Venta(Long usuarioId, Long metodoPagoId, Double total) {
        this.usuarioId = usuarioId;
        this.metodoPagoId = metodoPagoId;
        this.total = total;
        this.fecha = LocalDateTime.now();
    }

  
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}