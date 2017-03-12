package daw.cookcinando.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import daw.cookcinando.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	@Query(value = "SELECT * FROM Recipe WHERE LOWER(title) like %?1%", nativeQuery = true)
	List<Recipe> findByTitle(String title);
}