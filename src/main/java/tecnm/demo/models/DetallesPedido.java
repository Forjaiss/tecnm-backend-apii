package tecnm.demo.models; 

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_pedido")
public class DetallesPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    @Column(name = "id_pedido")
    public Long idPedido;

    @Column(name = "id_producto")
    public Long idProducto;

    public Integer cantidad;
    public Double precio_unitario;

    public DetallesPedido() {}
}