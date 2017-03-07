package daw.cookcinando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
