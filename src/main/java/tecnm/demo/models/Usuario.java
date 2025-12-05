package tecnm.demo.models; // <--- ESTA LÍNEA ES VITAL

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
    
    // Estos son los que te faltaban:
    public String sexo;
    @Column(name = "fecha_nacimiento")
    public LocalDate fechaNacimiento;
    public String contrasena;
    
    @Column(name = "fecha_registro")
    public LocalDateTime fechaRegistro;

    public Usuario() {}
}