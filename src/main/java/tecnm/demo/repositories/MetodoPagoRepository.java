package tecnm.demo.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tecnm.demo.models.MetodoPago;
import java.util.List;

@Repository
public class MetodoPagoRepository {
    private final JdbcTemplate jdbcTemplate;

    public MetodoPagoRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<MetodoPago> findAll() {
        return jdbcTemplate.query("SELECT * FROM metodos_pago", (rs, i) -> {
            MetodoPago m = new MetodoPago();
            m.id = rs.getLong("id");
            m.nombre = rs.getString("nombre");
            m.comision = rs.getDouble("comision");
            return m;
        });
    }

    public void save(MetodoPago m) {
        jdbcTemplate.update("INSERT INTO metodos_pago (nombre, comision) VALUES (?, ?)", m.nombre, m.comision);
    }
}