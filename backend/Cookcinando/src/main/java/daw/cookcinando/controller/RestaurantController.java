package daw.cookcinando.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import daw.cookcinando.UserComponent;
import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.Restaurant;
import daw.cookcinando.model.User;
import daw.cookcinando.repository.RestaurantRepository;

@Controller
public class RestaurantController {
	
	/*@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	//@Autowired
	//private UserRepository userRepository;
	
	private List<String> typesFood = new ArrayList<>();
	
	@RequestMapping("/restarantes") //Enlace para los restaurantes
	public String restaurantes (Model model) {
		model.addAttribute("restaurantes", restaurantRepository.findAll());
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
		} else {
			model.addAttribute("usernotlogged", true);
		}
		return "restaurantes";
	}
	
	@RequestMapping("/restaurantes/{id}")
	public String verRestaurante(Model model, @PathVariable long id, HttpServletRequest request) {
		Restaurant restaurant = restaurantRepository.findOne(id);
		model.addAttribute("restaurante", restaurant);		
		List<Restaurant> recomendadas = new ArrayList<Restaurant>();
		for (long i = 1; i < 4; i++) {
			recomendadas.add(restaurantRepository.getOne(i));
		}
		model.addAttribute("recomendadas", recomendadas);
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			if (restaurante.getAuthor().getId() == userLogged.getId()) {
				model.addAttribute("creator", true);
			} else {
				model.addAttribute("notcreator", true);
			}
		}
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
		} else {
			model.addAttribute("usernotlogged", true);
		}
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "restaurante";
	}
	
	@RequestMapping("/privado/restaurantes/crear")
	public String nuevaRestaurante(Model model, HttpServletRequest request) {
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "crearRestaurante";
	}
	
	@RequestMapping("/privado/restaurantes/form-crear")
	public String crearrestaurante(Model model, HttpServletRequest request, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam String cuerpo, @RequestParam String ingredientes, @RequestParam String comidas) {
		List<String> ingredientesRecetas = new ArrayList<>();
		String ingredientesSeparados[] = ingredientes.split(",");
		for(int i=0; i<ingredientesSeparados.length; i++){
			ingredientesRecetas.add(ingredientesSeparados[i]);
		}
		List<String> comidasRecetas = new ArrayList<>();
		String comidasSeparadas[] = comidas.split(",");
		for(int i=0; i<comidasSeparadas.length; i++){
			comidasRecetas.add(comidasSeparadas[i]);
		}
		User userLogged = userComponent.getLoggedUser();
		Recipe recipe = new Recipe(titulo, descripcion, "", cuerpo, ingredientesRecetas, comidasRecetas, userLogged);
		recipeRepository.save(recipe);
//		model.addAttribute("receta", recipe);
//		List<Recipe> recomendadas = new ArrayList<Recipe>();
//		for (long i = 1; i < 4; i++) {
//			recomendadas.add(recipeRepository.getOne(i));
//		}
//		model.addAttribute("recomendadas", recomendadas);
//		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
//		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return ("redirect:/recetas/"+recipe.getId());
	}
	
	@RequestMapping("/privado/recetas/editar/{id}")
	public String modificarReceta(Model model, @PathVariable Long id){
		Recipe receta = recipeRepository.findOne(id);
		model.addAttribute("receta", receta);
		return "formularioEditar";
	}
	
	@RequestMapping("/privado/recetas/form-editar/{id}")
	public String editarreceta(Model model, @PathVariable Long id, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam String cuerpo, @RequestParam String ingredientes, @RequestParam String comidas) {
		List<String> ingredientesRecetas = new ArrayList<>();
		String ingredientesSeparados[] = ingredientes.split(",");
		for(int i=0; i<ingredientesSeparados.length; i++){
			ingredientesRecetas.add(ingredientesSeparados[i]);
		}
		List<String> comidasRecetas = new ArrayList<>();
		String comidasSeparadas[] = comidas.split(",");
		for(int i=0; i<comidasSeparadas.length; i++){
			comidasRecetas.add(comidasSeparadas[i]);
		}
		Recipe recipe = recipeRepository.findOne(id);
		recipe.setTitle(titulo);
		recipe.setDescription(descripcion);
		recipe.setPreparation(cuerpo);
		recipe.setIngredients(ingredientesRecetas);
		recipe.setTypesFood(comidasRecetas);
		recipeRepository.save(recipe);
		return ("redirect:/recetas/"+id);
	}
}*/
	
}