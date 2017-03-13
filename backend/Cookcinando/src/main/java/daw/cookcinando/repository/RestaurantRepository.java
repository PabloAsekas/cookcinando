package daw.cookcinando.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import daw.cookcinando.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	//@Query(value = "SELECT * FROM Restaurant WHERE LOWER(title) like %?1%", nativeQuery = true)
	//List<Restaurant> findByTitle(String title);
		
}
