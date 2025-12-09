package tecnm.demo.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tecnm.demo.models.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductoRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Producto> findAll() {
        return jdbcTemplate.query("SELECT * FROM productos", new ProductoRowMapper());
    }

    public Producto findById(Long id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new ProductoRowMapper(), id);
        } catch (Exception e) { return null; }
    }

    public void save(Producto p) {
        String sql = """
            INSERT INTO productos (nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id, img_url, stock)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        jdbcTemplate.update(sql, p.nombre, p.precio, p.sku, p.color, p.marca, p.descripcion, 
                            p.peso, p.alto, p.ancho, p.profundidad, p.categoriasId, p.imgUrl, p.stock);
    }

    public int update(Long id, Producto p) {
        String sql = """
            UPDATE productos SET nombre=?, precio=?, sku=?, color=?, marca=?, descripcion=?, 
            peso=?, alto=?, ancho=?, profundidad=?, categorias_id=?, img_url=?, stock=?
            WHERE id = ?
        """;
        return jdbcTemplate.update(sql, p.nombre, p.precio, p.sku, p.color, p.marca, p.descripcion, 
                            p.peso, p.alto, p.ancho, p.profundidad, p.categoriasId, p.imgUrl, p.stock, id);
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM productos WHERE id = ?", id);
    }

    // 👇 ESTE ES EL MÉTODO QUE TE FALTABA 👇
    public int updateStock(Long id, Integer nuevoStock) {
        String sql = "UPDATE productos SET stock = ? WHERE id = ?";
        return jdbcTemplate.update(sql, nuevoStock, id);
    }
    // 👆👆👆

    private static class ProductoRowMapper implements RowMapper<Producto> {
        @Override
        public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Producto p = new Producto();
            p.id = rs.getLong("id");
            p.nombre = rs.getString("nombre");
            p.precio = rs.getDouble("precio");
            p.sku = rs.getString("sku");
            p.color = rs.getString("color");
            p.marca = rs.getString("marca");
            p.descripcion = rs.getString("descripcion");
            p.peso = rs.getDouble("peso");
            p.alto = rs.getDouble("alto");
            p.ancho = rs.getDouble("ancho");
            p.profundidad = rs.getDouble("profundidad");
            p.categoriasId = rs.getLong("categorias_id");
            p.imgUrl = rs.getString("img_url");
            p.stock = rs.getInt("stock");
            return p;
        }
    }
}