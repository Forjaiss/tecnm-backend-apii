package tecnm.demo.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tecnm.demo.models.Categoria;
import java.util.List;

@Repository
public class CategoriaRepository {
    private final JdbcTemplate jdbcTemplate;

    public CategoriaRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<Categoria> findAll() {
        return jdbcTemplate.query("SELECT * FROM categorias", (rs, i) -> {
            Categoria c = new Categoria();
            c.id = rs.getLong("id");
            c.nombre = rs.getString("nombre");
            return c;
        });
    }
    
    public void save(Categoria c) {
        jdbcTemplate.update("INSERT INTO categorias (nombre) VALUES (?)", c.nombre);
    }
}