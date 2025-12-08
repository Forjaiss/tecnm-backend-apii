package tecnm.demo.models; 

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nombre;
    public String email;
    public String telefono;
    
   
    public String sexo;
    @Column(name = "fecha_nacimiento")
    public LocalDate fechaNacimiento;
    public String contrasena;
    
    @Column(name = "fecha_registro")
    public LocalDateTime fechaRegistro;

    public Usuario() {}
}