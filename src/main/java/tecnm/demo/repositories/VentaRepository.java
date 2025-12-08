package tecnm.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tecnm.demo.models.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

}