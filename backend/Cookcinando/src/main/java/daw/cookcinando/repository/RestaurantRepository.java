package daw.cookcinando.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	@Query(value = "SELECT * FROM restaurant r, restaurant_types_food t where r.id = t.restaurant_id and t.types_food = ?1", nativeQuery = true)
	List<Restaurant> findByTypeFood(String type);
}
