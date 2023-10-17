package co.edu.udea.api.repository;

import co.edu.udea.api.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

    // Definición de un método personalizado para buscar héroes por un término en el nombre
    @Query("SELECT e FROM Hero e WHERE e.name LIKE %:nombre%") 
    List<Hero> findByNameContaining(@Param("nombre") String name);

}
