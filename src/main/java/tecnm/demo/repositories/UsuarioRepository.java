package tecnm.demo.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tecnm.demo.models.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<Usuario> findAll() {
        return jdbcTemplate.query("SELECT * FROM usuarios", new UsuarioRowMapper());
    }

    public Usuario findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM usuarios WHERE id = ?", new UsuarioRowMapper(), id);
        } catch (Exception e) { return null; }
    }

    public void save(Usuario u) {
        String sql = "INSERT INTO usuarios (nombre, email, telefono, sexo, fecha_nacimiento, contrasena, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, NOW())";
        jdbcTemplate.update(sql, u.nombre, u.email, u.telefono, u.sexo, u.fechaNacimiento, u.contrasena);
    }

    // UPDATE (Faltaba)
    public int update(Long id, Usuario u) {
        String sql = "UPDATE usuarios SET nombre=?, email=?, telefono=?, sexo=?, fecha_nacimiento=? WHERE id=?";
        return jdbcTemplate.update(sql, u.nombre, u.email, u.telefono, u.sexo, u.fechaNacimiento, id);
    }

    // DELETE (Faltaba)
    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM usuarios WHERE id = ?", id);
    }

    private static class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario u = new Usuario();
            u.id = rs.getLong("id");
            u.nombre = rs.getString("nombre");
            u.email = rs.getString("email");
            u.telefono = rs.getString("telefono");
            u.sexo = rs.getString("sexo");
            u.fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
            u.contrasena = rs.getString("contrasena");
            return u;
        }
    }
}