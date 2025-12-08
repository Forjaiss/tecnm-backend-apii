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

    public DomicilioRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<Domicilio> findAll() {
        return jdbcTemplate.query("SELECT * FROM domicilios", new DomicilioRowMapper());
    }

    public List<Domicilio> findByUsuario(Long usuarioId) {
        return jdbcTemplate.query("SELECT * FROM domicilios WHERE usuarios_id = ?", new DomicilioRowMapper(), usuarioId);
    }

    public void save(Domicilio d) {
        String sql = "INSERT INTO domicilios (calle, numero, colonia, cp, estado, ciudad, usuarios_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, d.calle, d.numero, d.colonia, d.cp, d.estado, d.ciudad, d.usuariosId);
    }

   
    public int update(Long id, Domicilio d) {
        String sql = "UPDATE domicilios SET calle=?, numero=?, colonia=?, cp=?, estado=?, ciudad=? WHERE id=?";
        return jdbcTemplate.update(sql, d.calle, d.numero, d.colonia, d.cp, d.estado, d.ciudad, id);
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM domicilios WHERE id=?", id);
    }

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
            d.usuariosId = rs.getLong("usuarios_id");
            return d;
        }
    }
}