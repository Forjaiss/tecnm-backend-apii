package tecnm.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_carrito")
public class DetalleCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Integer cantidad;
    public Double precio;

    @Column(name = "productos_id")
    public Long productosId;

    @Column(name = "usuarios_id")
    public Long usuariosId;

    public DetalleCarrito() {}
}