package daw.cookcinando.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import daw.cookcinando.model.Recipe;
import daw.cookcinando.repository.RecipeRepository;

@Service
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
	
	public Recipe findOne(long id) {
		return recipeRepository.findOne(id);
	}
	
	public Page<Recipe> findAll(Pageable pageable) {
		return recipeRepository.findAll(pageable);
	}
	
	public List<Recipe> findAll() {
		return recipeRepository.findAll();
	}
	
	public void save(Recipe recipe) {
		recipeRepository.save(recipe);
	}
	
	public void delete(long id) {
		recipeRepository.delete(id);
	}
	
	//CAMBIADO PARA BUSCADOR
	public List<Recipe> findByTitle(String title) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		for(Recipe r : recipeRepository.findAll()){
			if(r.getTitle().contains(title)){
				recipes.add(r);
				break;
			}
		}
		return recipes;
	}
	
	
	public List<Recipe> findByIngredients(List<String> ingredients) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		for(Recipe r : recipeRepository.findAll()){
			if(r.getIngredients().contains(ingredients)){
				recipes.add(r);
				break;
			}
		}
		return recipes;
	}
	
	public List<Recipe> findByTypesFood(List<String> typesFood) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		for(Recipe r : recipeRepository.findAll()){
			if(r.getTypesFood().contains(typesFood)){
				recipes.add(r);
				break;
			}
		}
		return recipes;
	}
	
}
