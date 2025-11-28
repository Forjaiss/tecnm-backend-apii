package tecnm.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "domicilios")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String calle;
    public String numero;
    public String colonia;
    

    public String cp; 
    
    public String estado;
    public String ciudad;


    @Column(name = "usuarios_id")
    public Long usuariosId;

    public Domicilio() {}
}