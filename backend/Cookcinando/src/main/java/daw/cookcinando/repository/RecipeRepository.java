package daw.cookcinando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import daw.cookcinando.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}