package tecnm.demo.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tecnm.demo.models.Domicilio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DomicilioRepository {

    private final JdbcTemplate jdbcTemplate;

    public DomicilioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 1. Obtener TODAS las direcciones
    public List<Domicilio> findAll() {
        String sql = "SELECT * FROM domicilios";
        return jdbcTemplate.query(sql, new DomicilioRowMapper());
    }

    // 2. Obtener direcciones POR USUARIO (Muy útil)
    public List<Domicilio> findByUsuario(Long idUsuario) {
        String sql = "SELECT * FROM domicilios WHERE usuarios_id = ?";
        return jdbcTemplate.query(sql, new DomicilioRowMapper(), idUsuario);
    }

    // 3. Guardar nueva dirección
    public void save(Domicilio d) {
        String sql = """
            INSERT INTO domicilios (calle, numero, colonia, cp, estado, ciudad, usuarios_id)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
            d.calle,
            d.numero,
            d.colonia,
            d.cp,
            d.estado,
            d.ciudad,
            d.usuariosId
        );
    }

    // Mapeador de SQL a Java
    private static class DomicilioRowMapper implements RowMapper<Domicilio> {
        @Override
        public Domicilio mapRow(ResultSet rs, int rowNum) throws SQLException {
            Domicilio d = new Domicilio();
            d.id = rs.getLong("id");
            d.calle = rs.getString("calle");
            d.numero = rs.getString("numero");
            d.colonia = rs.getString("colonia");
            d.cp = rs.getString("cp");
            d.estado = rs.getString("estado");
            d.ciudad = rs.getString("ciudad");
            d.usuariosId = rs.getLong("usuarios_id"); // Ojo al guion bajo
            return d;
        }
    }
}