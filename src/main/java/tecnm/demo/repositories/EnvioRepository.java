package tecnm.demo.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tecnm.demo.models.Envio;
import java.util.List;

@Repository
public class EnvioRepository {
    private final JdbcTemplate jdbcTemplate;

    public EnvioRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<Envio> findAll() {
        return jdbcTemplate.query("SELECT * FROM envios", (rs, i) -> {
            Envio e = new Envio();
            e.id = rs.getLong("id");
            e.estado = rs.getString("estado");
            e.numeroSeguimiento = rs.getString("numero_seguimiento");
            e.domiciliosId = rs.getLong("domicilios_id");
            e.pedidosId = rs.getLong("pedidos_id");
            // Mapeo básico de fechas si es necesario
            // e.fecha = rs.getTimestamp("fecha").toLocalDateTime();
            return e;
        });
    }

    public void updateEstado(Long id, String estado) {
        jdbcTemplate.update("UPDATE envios SET estado = ? WHERE id = ?", estado, id);
    }
}