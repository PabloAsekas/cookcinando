package daw.cookcinando.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.User;
import daw.cookcinando.service.RecipeService;



@RestController
@RequestMapping("/api/recipes")
public class RecipeRestController {

	@Autowired
	private RecipeService recipeservice;

	@JsonView(Recipe.Basic.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<Recipe> getRecipes() {
		return recipeservice.findAll();
	}

	interface RecipeDetail extends Recipe.Basic, Recipe.Users, User.Basic { }
	
	@JsonView(RecipeDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Recipe> getRecipe(@PathVariable long id) {

		Recipe recipe = recipeservice.findOne(id);
		if (recipe != null) {
			return new ResponseEntity<>(recipe, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(RecipeDetail.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Recipe createRecipe(@RequestBody Recipe recipe) {

		recipeservice.save(recipe);

		return recipe;
	}

	@JsonView(RecipeDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Recipe> updateRecipe(@PathVariable long id, @RequestBody Recipe updatedRecipe) {

		Recipe receta = recipeservice.findOne(id);
		if (receta != null) {

			updatedRecipe.setId(id);
			recipeservice.save(updatedRecipe);

			return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Recipe> deleteRecipe(@PathVariable long id) {

		recipeservice.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}

