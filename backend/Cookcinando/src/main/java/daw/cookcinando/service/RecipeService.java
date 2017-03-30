package daw.cookcinando.service;

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
	
	public Recipe findByTitle(String title) {
		Recipe recipe = null;
		
		for(Recipe r : recipeRepository.findAll()){
			if(r.getTitle() == title){
				recipe = r;
				break;
			}
		}
		return recipe;
	}
	
	
	public Recipe findByIngredients(List<String> ingredients) {
		Recipe recipe = null;
		
		for(Recipe r : recipeRepository.findAll()){
			if(r.getIngredients() == ingredients){
				recipe = r;
				break;
			}
		}
		return recipe;
	}
	
	public Recipe findByTypesFood(List<String> typesFood) {
		Recipe recipe = null;
		
		for(Recipe r : recipeRepository.findAll()){
			if(r.getTypesFood() == typesFood){
				recipe = r;
				break;
			}
		}
		return recipe;
	}
	
}
