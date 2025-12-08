package tecnm.demo.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tecnm.demo.models.DetalleCarrito; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarritoRepository {
    private final JdbcTemplate jdbcTemplate;

    public CarritoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Buscar si ya existe
    public DetalleCarrito findItem(Long usuarioId, Long productoId) {
        String sql = "SELECT * FROM detalles_carrito WHERE usuarios_id = ? AND productos_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CarritoMapper(), usuarioId, productoId);
        } catch (Exception e) { return null; }
    }

    public void save(DetalleCarrito item) {
        String sql = "INSERT INTO detalles_carrito (cantidad, precio, productos_id, usuarios_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, item.cantidad, item.precio, item.productosId, item.usuariosId);
    }

    public void updateCantidad(Long id, Integer cantidad) {
        jdbcTemplate.update("UPDATE detalles_carrito SET cantidad = ? WHERE id = ?", cantidad, id);
    }

    public List<DetalleCarrito> findByUsuario(Long usuarioId) {
        return jdbcTemplate.query("SELECT * FROM detalles_carrito WHERE usuarios_id = ?", new CarritoMapper(), usuarioId);
    }
    
    public void deleteByUsuario(Long usuarioId) {
        jdbcTemplate.update("DELETE FROM detalles_carrito WHERE usuarios_id = ?", usuarioId);
    }

    private static class CarritoMapper implements RowMapper<DetalleCarrito> {
        @Override
        public DetalleCarrito mapRow(ResultSet rs, int rowNum) throws SQLException {
            DetalleCarrito d = new DetalleCarrito();
            d.id = rs.getLong("id");
            d.cantidad = rs.getInt("cantidad");
            d.precio = rs.getDouble("precio");
            d.productosId = rs.getLong("productos_id");
            d.usuariosId = rs.getLong("usuarios_id");
            return d;
        }
    }
}