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
    private final JdbcTemplate db;

    public CarritoRepository(JdbcTemplate db) {
        this.db = db;
    }

    // Buscar si un producto ya existe en el carrito del usuario
    public DetalleCarrito findItem(Long uid, Long pid) {
        try {
            String sql = "SELECT * FROM detalles_carrito WHERE usuarios_id = ? AND productos_id = ?";
            return db.queryForObject(sql, new CarritoMapper(), uid, pid);
        } catch (Exception e) {
            return null;
        }
    }

    // Guardar nuevo producto en el carrito
    public void save(DetalleCarrito d) {
        String sql = "INSERT INTO detalles_carrito (cantidad, precio, productos_id, usuarios_id) VALUES (?, ?, ?, ?)";
        db.update(sql, d.cantidad, d.precio, d.productosId, d.usuariosId);
    }

    // Actualizar cantidad (sumar)
    public void updateCantidad(Long id, int nuevaCantidad) {
        String sql = "UPDATE detalles_carrito SET cantidad = ? WHERE id = ?";
        db.update(sql, nuevaCantidad, id);
    }

    // Ver todo el carrito de un usuario
    public List<DetalleCarrito> findByUsuario(Long uid) {
        String sql = "SELECT * FROM detalles_carrito WHERE usuarios_id = ?";
        return db.query(sql, new CarritoMapper(), uid);
    }

    // Vaciar carrito (Borrar todo)
    public void deleteByUsuario(Long uid) {
        String sql = "DELETE FROM detalles_carrito WHERE usuarios_id = ?";
        db.update(sql, uid);
    }

    // Convertir SQL a Objeto Java
    public static class CarritoMapper implements RowMapper<DetalleCarrito> {
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