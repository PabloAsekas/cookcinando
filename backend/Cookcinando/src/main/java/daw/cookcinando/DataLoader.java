package daw.cookcinando;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.User;
import daw.cookcinando.model.UserBasic;
import daw.cookcinando.repository.RecipeRepository;
import daw.cookcinando.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		
		List<String> ingredients = new ArrayList<>();
		List<String> typesFood = new ArrayList<>();
		
		ingredients.add("Ingrediente1");
		ingredients.add("Ingrediente2");
		ingredients.add("Ingrediente3");
		ingredients.add("Ingrediente4");
		
		typesFood.add("Comida1");
		typesFood.add("Comida2");
		typesFood.add("Comida3");
		typesFood.add("Comida4");
		
		UserBasic user_basic = new UserBasic("A", "A", "A", "A", "A","basic","pass","ROLE_BASIC");
		UserBasic user_basic2 = new UserBasic("B", "B", "B", "B", "B","bbasic","pass","ROLE_BASIC");
		UserBasic user_admin = new UserBasic("C", "C", "C", "C", "C","cbasic","pass","ROLE_BASIC", "ROLE_ENTERPRISE", "ROLE_ADMIN");
		userRepository.save(user_basic);
		userRepository.save(user_basic2);
		userRepository.save(user_admin);
		
		Recipe recipe = new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", 
				  					"<h3>Ingredientes: </h3>"
						              + "<h3>Preparación: </h3>"
									  + "<h3>Consejos:</h3>"	  
						              , ingredients, typesFood, user_basic);
		
		recipeRepository.save(recipe);
		
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));

		
		user_basic.getMyRecipes().add(recipeRepository.findOne((long)1));
		
		
		
	}
	
	

}