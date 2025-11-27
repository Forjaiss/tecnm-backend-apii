package tecnm.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nombre;
    public Double precio;
    public String sku; 
    public String color;
    public String marca;
    public String descripcion;

    public Double peso;
    public Double alto;
    public Double ancho;
    public Double profundidad;

    @Column(name = "categorias_id")
    public Long categoriasId;


    public Integer stock;
    
    @Column(name = "img_url")
    public String imgUrl;


    public Producto() {}
}