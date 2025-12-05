package tecnm.demo.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "envios")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "fecha_entrega")
    public LocalDateTime fechaEntrega;

    public LocalDateTime fecha;
    public String estado;
    
    @Column(name = "numero_seguimiento")
    public String numeroSeguimiento;

    @Column(name = "domicilios_id")
    public Long domiciliosId;

    @Column(name = "pedidos_id")
    public Long pedidosId;

    public Envio() {}
}