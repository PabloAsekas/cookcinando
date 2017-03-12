package daw.cookcinando;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.Restaurant;
import daw.cookcinando.model.User;
import daw.cookcinando.model.UserAdmin;
import daw.cookcinando.model.UserBasic;
import daw.cookcinando.model.UserEnterprise;
import daw.cookcinando.repository.RecipeRepository;
import daw.cookcinando.repository.RestaurantRepository;
import daw.cookcinando.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
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
		
		UserBasic user_basic = new UserBasic("Manuela", "Carmena", "Descripción muy larga de lo que quiera poner el usuario para describirse así mismo porque seguro que puede hacerlo", 
				"../../img/avatar.jpg", "noTeLoPerdoreNUNCA","basic","pass","ROLE_BASIC");
		UserBasic user_basic2 = new UserBasic("Pablo", "Motos", "Mu gracioso, mejor persona", "http://www.hola.com/imagenes/noticias-de-actualidad/2016/04/15/hormiguero.jpg", "elHormiguero","bbasic","pass","ROLE_BASIC");
		UserBasic user_admin = new UserBasic("Francisco", "Marhuenda", "Er mejor periodista visto", "https://pbs.twimg.com/profile_images/622733889858052096/tm7zeQ06.jpg", "rio","cbasic","pass","ROLE_BASIC", "ROLE_ENTERPRISE", "ROLE_ADMIN");
		userRepository.save(user_basic);
		userRepository.save(user_basic2);
		userRepository.save(user_admin);
		
		Recipe recipe = new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", 
				  					"<h3>Ingredientes: </h3>"
						              + "<h3>Preparación: </h3>"
									  + "<h3>Consejos:</h3>"	  
						              , ingredients, typesFood, null);
		
		recipeRepository.save(recipe);
		recipe.setAuthor(user_basic);
		recipeRepository.saveAndFlush(recipe);
		
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic2));
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic2));
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic2));
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic2));
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood, user_basic));

		
		//user_basic.getMyRecipes().add(recipeRepository.findOne((long)1));
		
		UserBasic basic = new UserBasic("A", "A", "A", "A", "A","basic1","pass","ROLE_BASIC");
		
		UserEnterprise enterprise = new UserEnterprise("B", "B", "B", "B", "B","enterprise","pass","ROLE_BASIC","ROLE_ENTERPRISE");
		
		UserAdmin admin = new UserAdmin("C", "C", "C", "C", "C","admin","adminpass","ROLE_BASIC","ROLE_ENTERPRISE", "ROLE_ADMIN");
		
		userRepository.save(basic);
		userRepository.save(enterprise);
		userRepository.save(admin);
		
		Restaurant restaurant1 = new Restaurant("Telepizza", "Pizza super buena", "../../img/telepizza.png", "<h1>DESCRIPCION DEL RESTAURANTE</h1>", typesFood, admin);
		Restaurant restaurant2 = new Restaurant("KFC", "Pollo genial", "../../img/kfc.png", "<h1>DESCRIPCION DEL RESTAURANTE</h1>", typesFood, admin);
		Restaurant restaurant3 = new Restaurant("Telepizza", "Pizza super buena", "../../img/telepizza.png", "<h1>DESCRIPCION DEL RESTAURANTE</h1>", typesFood, admin);
		Restaurant restaurant4 = new Restaurant("KFC", "Pollo genial", "../../img/kfc.png", "<h1>DESCRIPCION DEL RESTAURANTE</h1>", typesFood, admin);
		Restaurant restaurant5 = new Restaurant("Telepizza", "Pizza super buena", "../../img/telepizza.png", "<h1>DESCRIPCION DEL RESTAURANTE</h1>", typesFood, user_basic2);
		Restaurant restaurant6 = new Restaurant("KFC", "Pollo genial", "../../img/kfc.png", "<h1>DESCRIPCION DEL RESTAURANTE</h1>", typesFood, admin);
		Restaurant restaurant7 = new Restaurant("Telepizza", "Pizza super buena", "../../img/telepizza.png", "<h1>DESCRIPCION DEL RESTAURANTE</h1>", typesFood, admin);
		Restaurant restaurant8 = new Restaurant("KFC", "Pollo genial", "../../img/kfc.png", "<h1>DESCRIPCION DEL RESTAURANTE</h1>", typesFood, admin);
		
		restaurantRepository.saveAndFlush(restaurant1);
		restaurantRepository.save(restaurant2);
		restaurantRepository.save(restaurant3);
		restaurantRepository.save(restaurant4);
		restaurantRepository.save(restaurant5);
		restaurantRepository.save(restaurant6);
		restaurantRepository.save(restaurant7);
		restaurantRepository.save(restaurant8);
		
	}
	
	

}
