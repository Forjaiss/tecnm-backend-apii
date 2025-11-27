package tecnm.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tecnm.demo.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}