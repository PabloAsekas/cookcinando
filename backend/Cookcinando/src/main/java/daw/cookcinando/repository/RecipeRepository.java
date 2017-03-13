package daw.cookcinando.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import daw.cookcinando.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	@Query(value = "SELECT * FROM Recipe WHERE LOWER(title) like %?1%", nativeQuery = true)
	List<Recipe> findByTitle(String title);
	
	@Query(value = "SELECT * FROM recipe r, recipe_ingredients i where r.id = i.recipe_id and i.ingredients = ?1", nativeQuery = true)
	List<Recipe> findByIngredient(String ingredient);
	
	@Query(value = "SELECT * FROM recipe r, recipe_types_food t where r.id = t.recipe_id and t.types_food = ?1", nativeQuery = true)
	List<Recipe> findByTypeFood(String type);
}