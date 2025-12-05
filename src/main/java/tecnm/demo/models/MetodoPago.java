package tecnm.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "metodos_pago")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nombre;
    public Double comision;

    public MetodoPago() {}
}