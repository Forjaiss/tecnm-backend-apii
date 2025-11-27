package tecnm.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nombre;
    

    public String email;
    public String password; 
    public String telefono;
    public String direccion;

    public Usuario() {}
}