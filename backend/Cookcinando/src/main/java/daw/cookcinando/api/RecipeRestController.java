package daw.cookcinando.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import daw.cookcinando.UserComponent;
import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.User;
import daw.cookcinando.service.RecipeService;



@RestController
@RequestMapping("/api/recipes")
public class RecipeRestController {

	@Autowired
	private RecipeService recipeservice;
	
	@Autowired
	private UserComponent userComponent;

	@JsonView(Recipe.Basic.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Page<Recipe> getRecipes(@RequestParam (required=false) String page) {
		if(page == null){
			return recipeservice.findAll(new PageRequest(0, 9));
		}
		else {
			int numPage =  Integer.parseInt(page); 
			return recipeservice.findAll(new PageRequest(numPage, 9));
		}
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
	public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
		if (userComponent.isLoggedUser()) {
			recipe.setAuthor(userComponent.getLoggedUser());
			recipeservice.save(recipe);
			return new ResponseEntity<>(recipe, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@JsonView(RecipeDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Recipe> updateRecipe(@PathVariable long id, @RequestBody Recipe updatedRecipe) {
		Recipe receta = recipeservice.findOne(id);
		if (receta != null) {
			User loggedUser = userComponent.getLoggedUser();
			if (loggedUser != null) {
				if (loggedUser.getId() == receta.getAuthor().getId() || loggedUser.isAdmin()) {
					updatedRecipe.setId(id);
					updatedRecipe.setAuthor(receta.getAuthor());
					recipeservice.save(updatedRecipe);
					return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Recipe> deleteRecipe(@PathVariable long id) {
		Recipe receta = recipeservice.findOne(id);
		if (receta != null) {
			User loggedUser = userComponent.getLoggedUser();
			if (loggedUser != null) {
				if (loggedUser.getId() == recipeservice.findOne(id).getAuthor().getId() || loggedUser.isAdmin()) {
					recipeservice.delete(id);
					return new ResponseEntity<>(null, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(Recipe.Basic.class)
	@RequestMapping(value = "/recommended", method = RequestMethod.GET)
	public List<Recipe> getRecommended() {
		List<Recipe> recomendadas = new ArrayList<Recipe>();
		int j=0;
		for (Recipe rec : recipeservice.findAll()) {
			j++;
			recomendadas.add(rec);
			if(j==3){
				break;
			}
		}
		return recomendadas;
	}
	
	@JsonView(Recipe.Basic.class)
	@RequestMapping(value = "/by-title/", method = RequestMethod.GET)
	public ResponseEntity<List<Recipe>> recipesByTitle(@RequestParam String title){
		List<Recipe> recipesByTitle = recipeservice.findByTitle(title);
		if(recipesByTitle.size() > 0) {
			return new ResponseEntity(recipesByTitle, HttpStatus.OK);
		}
		else return new ResponseEntity(HttpStatus.NOT_FOUND);	
	}
	
	@JsonView(Recipe.Basic.class)
	@RequestMapping(value = "/by-ingredient/", method = RequestMethod.GET)
	public ResponseEntity<List<Recipe>> recipesByIngredient(@RequestParam String ingredient){
		List<Recipe> recipesByIngredient = recipeservice.findByIngredient(ingredient);
		if(recipesByIngredient.size() > 0) {
			return new ResponseEntity(recipesByIngredient, HttpStatus.OK);
		}
		else return new ResponseEntity(HttpStatus.NOT_FOUND);	
	}
	
	@JsonView(Recipe.Basic.class)
	@RequestMapping(value = "/by-typeFood/", method = RequestMethod.GET)
	public ResponseEntity<List<Recipe>> recipesByTypeFood(@RequestParam String typeFood){
		List<Recipe> recipesByTypeFood = recipeservice.findByTypeFood(typeFood);
		if(recipesByTypeFood.size() > 0) {
			return new ResponseEntity(recipesByTypeFood, HttpStatus.OK);
		}
		else return new ResponseEntity(HttpStatus.NOT_FOUND);	
	}	
	
private static final String USER_IMAGE_FOLDER = "../../frontend/src/assets/img";
	
	@JsonView(RecipeDetail.class)
	@RequestMapping(value = "/uploadImage/{idRecipe}", method = RequestMethod.POST)
	public ResponseEntity<Recipe> handleFileUpload(@RequestBody MultipartFile file, @PathVariable long idRecipe) {

		//String fileName = file.getOriginalFilename() + ".jpg";
		//long idLogged=userComponent.getIdLoggedUser();
			Recipe receta = recipeservice.findOne(idRecipe);

			//User u=userService.findOne(idLogged);
			String fileName =idRecipe  + "thumbnailrecipe.jpg";
			if (!file.isEmpty()) {
				try {
					File filesFolder = new File(USER_IMAGE_FOLDER);
					if (!filesFolder.exists()) {
						filesFolder.mkdirs();
					}
					
					File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
					file.transferTo(uploadedFile);
					
					receta.setThumbnail("assets/img/" + fileName);
					recipeservice.save(receta);
					//u.setProfileImage(fileName);
					//userService.save(u);

					return new ResponseEntity<>(receta, HttpStatus.OK);


				} catch (Exception e) {
					return new ResponseEntity<>(receta, HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>(receta, HttpStatus.NOT_FOUND);

			}
		
			
	}
}

