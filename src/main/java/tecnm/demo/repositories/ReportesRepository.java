package tecnm.demo.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class ReportesRepository {
    private final JdbcTemplate jdbcTemplate;

    public ReportesRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    // Métodos para todas tus vistas SQL
    public List<Map<String, Object>> ventas() { return jdbcTemplate.queryForList("SELECT * FROM vista_estadisticas_ventas"); }
    public List<Map<String, Object>> envios() { return jdbcTemplate.queryForList("SELECT * FROM vista_envios_pendientes"); }
    public List<Map<String, Object>> inventario() { return jdbcTemplate.queryForList("SELECT * FROM vista_inventario_optimizado"); }
    public List<Map<String, Object>> atencion() { return jdbcTemplate.queryForList("SELECT * FROM vista_pedidos_atencion"); }
    public List<Map<String, Object>> gestion() { return jdbcTemplate.queryForList("SELECT * FROM vista_productos_gestion"); }
    public List<Map<String, Object>> premium() { return jdbcTemplate.queryForList("SELECT * FROM vista_productos_premium"); }
    public List<Map<String, Object>> rendimiento() { return jdbcTemplate.queryForList("SELECT * FROM vista_rendimiento_categorias"); }
    public List<Map<String, Object>> resumen() { return jdbcTemplate.queryForList("SELECT * FROM vista_resumen_pedidos"); }
}