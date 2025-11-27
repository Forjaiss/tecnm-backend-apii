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
        String sql = "SELECT * FROM productos";
        return jdbcTemplate.query(sql, new ProductoRowMapper());
    }

    public Producto save(Producto p) {
        String sql = """
            INSERT INTO productos (nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id, img_url, stock)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

       
        jdbcTemplate.update(sql,
            p.nombre,
            p.precio,
            p.sku,
            p.color,
            p.marca,
            p.descripcion,
            p.peso,
            p.alto,
            p.ancho,
            p.profundidad,
            p.categoriasId,
            p.imgUrl,
            p.stock
        );

        return p;
    }


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
            p.categoriasId = rs.getLong("categorias_id"); // Ojo con el guion bajo
            p.imgUrl = rs.getString("img_url");
            p.stock = rs.getInt("stock");
            return p;
        }
    }
}