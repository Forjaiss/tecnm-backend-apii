package tecnm.demo.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tecnm.demo.models.Pedido; 
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

@Repository
public class PedidoRepository {
    private final JdbcTemplate jdbcTemplate;

    public PedidoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long crearPedido(Pedido p) {
        String sql = "INSERT INTO pedidos (numero, importe_productos, importe_envio, usuarios_id, metodos_pago_id, fecha) VALUES (?, ?, ?, ?, ?, NOW())";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, UUID.randomUUID().toString());
            ps.setDouble(2, p.importeProductos);
            ps.setDouble(3, p.importeEnvio);
            ps.setLong(4, p.usuariosId);
            ps.setLong(5, p.metodosPagoId);
            return ps;
        }, keyHolder);

        return (Long) keyHolder.getKeys().get("id");
    }

    public void guardarDetalle(Long pedidoId, Long prodId, Integer cant, Double precio) {
        jdbcTemplate.update("INSERT INTO detalles_pedido (cantidad, precio, productos_id, pedidos_id) VALUES (?, ?, ?, ?)",
            cant, precio, prodId, pedidoId);
    }
}