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
		
		return recipeRepository.findByTitle(title);
		
		/*List<Recipe> recipes = new ArrayList<Recipe>();
		
		for(Recipe r : recipeRepository.findAll()){
			if(r.getTitle().toLowerCase().contains(title.toLowerCase())){
				recipes.add(r);
			}
		}
		return recipes;*/
	}
	
	
	public List<Recipe> findByIngredient(String ingredient) {
		
		return recipeRepository.findByIngredient(ingredient);
		/*List<Recipe> recipes = new ArrayList<Recipe>();
		
		for(Recipe r : recipeRepository.findAll()){
			
			for(int i=0; i<r.getIngredients().size(); i++){
				if(r.getIngredients().get(i).toLowerCase().equals(ingredient.toLowerCase())){
					recipes.add(r);
					break;
				}
			}
		}
		return recipes;*/
	}
	
	public List<Recipe> findByTypeFood(String typeFood) {
		
		return recipeRepository.findByTypeFood(typeFood);
		
		/*List<Recipe> recipes = new ArrayList<Recipe>();
		
		for(Recipe r : recipeRepository.findAll()){
			
			for(int i=0; i<r.getTypesFood().size(); i++){
				if(r.getTypesFood().get(i).toLowerCase().equals(typeFood.toLowerCase())){
					recipes.add(r);
					break;
				}
			}
		}
		return recipes;*/
	}
	
}
