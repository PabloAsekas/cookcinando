package daw.cookcinando.web;

import java.awt.print.Pageable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.sym.Name;

import daw.cookcinando.UserComponent;
import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.User;
import daw.cookcinando.repository.RecipeRepository;
import daw.cookcinando.repository.UserRepository;
import daw.cookcinando.service.EventService;
import daw.cookcinando.service.RecipeService;

@Controller
public class RecipeWebController<def> {
	
	
	private static final String FILES_FOLDER = "target/classes/static/img";
	
	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private UserRepository userRepository;
	
	private List<String> ingredients = new ArrayList<>();
	private List<String> typesFood = new ArrayList<>();
	
	@RequestMapping("/recetas/") //Enlace para las recetas
	public String recetas (Model model) {
		model.addAttribute("recetas", recipeService.findAll());
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
		} else {
			model.addAttribute("usernotlogged", true);
		}
		
		Page<Recipe> recipes = recipeService.findAll(new PageRequest(0, 10));
		
		model.addAttribute("recetas", recipes);
		
		return "recetas";
	}
	
	@RequestMapping("/recetas/{id}")
	public String verReceta(Model model, @PathVariable long id, HttpServletRequest request) {
		Recipe recipe = recipeService.findOne(id);
		model.addAttribute("receta", recipe);		
		List<Recipe> recomendadas = new ArrayList<Recipe>();
		int j=0;
		for (Recipe rec : recipeService.findAll()) {
			j++;
			recomendadas.add(rec);
			if(j==3){
				break;
			}
		}
		model.addAttribute("recomendadas", recomendadas);
		User userLogged = userComponent.getLoggedUser();
		User author = recipe.getAuthor();
		model.addAttribute("author", author);
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
			if (userLogged.getId() == recipe.getAuthor().getId() || userLogged.isAdmin()) {
				model.addAttribute("creator", true);
			} else {
				User user = userRepository.findOne(userComponent.getLoggedUser().getId()); 
				if(user.getFavRecipes().contains(recipe)){
					model.addAttribute("notcreatorFAV", true);
				}else{
					model.addAttribute("notcreator", true);
				}
			}
		} else {
			model.addAttribute("usernotlogged", true);
		}
		return "receta";
	}
	
	@RequestMapping("/privado/recetas/crear")
	public String nuevaReceta(Model model, HttpServletRequest request) {
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "crearReceta";
	}
	
	@RequestMapping("/privado/recetas/form-crear")
	public String crearreceta(Model model, HttpServletRequest request, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam MultipartFile imagenCabecera, @RequestParam String cuerpo, @RequestParam String ingredientes, @RequestParam String comidas) {
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
		if (!imagenCabecera.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				Recipe recipe = new Recipe(titulo, descripcion, "", cuerpo, ingredientesRecetas, comidasRecetas, userLogged);
				recipeService.save(recipe);
				long id = recipe.getId();
				String fileName = "cabReceta" + id + ".jpg";
				String thumbnail = "../img/" + fileName;
				recipe.setThumbnail(thumbnail);
				recipeService.save(recipe);
				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				imagenCabecera.transferTo(uploadedFile);
				return ("redirect:/recetas/"+recipe.getId());
			} catch (Exception e) {
				model.addAttribute("error",e.getClass().getName() + ":" + e.getMessage());
			}
		} else {
			Recipe recipe = new Recipe(titulo, descripcion, "", cuerpo, ingredientesRecetas, comidasRecetas, userLogged);
			recipeService.save(recipe);
			return ("redirect:/recetas/"+recipe.getId());
		}
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return ("redirect:/recetas");
	}
	
	@RequestMapping("/privado/recetas/editar/{id}")
	public String modificarReceta(Model model, HttpServletRequest request, @PathVariable Long id){
		Recipe recipe = recipeService.findOne(id);
		model.addAttribute("receta", recipe);
		User userLogged = userComponent.getLoggedUser();
		if (userLogged.getId() == recipe.getAuthor().getId() || userLogged.isAdmin()) {
			model.addAttribute("creator", true);
		} else {
			model.addAttribute("deny", true);
		}
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "formularioEditarReceta";
	}
	
	@RequestMapping("/privado/recetas/form-editar/{id}")
	public String editarreceta(Model model, HttpServletRequest request, @PathVariable Long id, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam MultipartFile imagenCabecera, @RequestParam String cuerpo, @RequestParam String ingredientes, @RequestParam String comidas) {
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
		Recipe recipe = recipeService.findOne(id);
		User userLogged = userComponent.getLoggedUser();
		if (userLogged.getId() == recipe.getAuthor().getId() || userLogged.isAdmin()) {
			if (!imagenCabecera.isEmpty()) {
				try {
	
					File filesFolder = new File(FILES_FOLDER);
					if (!filesFolder.exists()) {
						filesFolder.mkdirs();
					}
					
					String fileName = "cabReceta" + id + ".jpg";
					String thumbnail = "../img/" + fileName;
					
					recipe.setTitle(titulo);
					recipe.setDescription(descripcion);
					recipe.setPreparation(cuerpo);
					recipe.setIngredients(ingredientesRecetas);
					recipe.setTypesFood(comidasRecetas);
					recipe.setThumbnail(thumbnail);
					recipeService.save(recipe);
					File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
					imagenCabecera.transferTo(uploadedFile);
					return ("redirect:/recetas/"+recipe.getId());
				} catch (Exception e) {
					model.addAttribute("error",e.getClass().getName() + ":" + e.getMessage());
				}
			} else {
				recipe.setTitle(titulo);
				recipe.setDescription(descripcion);
				recipe.setPreparation(cuerpo);
				recipe.setIngredients(ingredientesRecetas);
				recipe.setTypesFood(comidasRecetas);
				recipeService.save(recipe);
			}
		}
		return ("redirect:/recetas/"+id);
	}
	
	
	@RequestMapping("/privado/recetas/eliminar/{id}")
	public String eliminarReceta(Model model, @PathVariable Long id){
		Recipe recipe = recipeService.findOne(id);
		User userLogged = userComponent.getLoggedUser();
		if (userLogged.getId() == recipe.getAuthor().getId() || userLogged.isAdmin()) {
			recipeService.delete(recipe.getId());
		}
		return ("redirect:/recetas/");
	}
	
	@RequestMapping("/privado/recetas/add-fav/{id}")
	public String aniadirRecetaFavorito(Model model, @PathVariable long id){
		Recipe recipe = recipeService.findOne(id);
		User userLogged = userRepository.findOne(userComponent.getLoggedUser().getId()); 
		userLogged.getFavRecipes().add(recipe);
		userRepository.saveAndFlush(userLogged);
		//System.out.println(userLogged.getFavRecipes().get(0));
		return ("redirect:/recetas/");
	}
	
	@RequestMapping("/privado/recetas/remove-fav/{id}")
	public String quitarRecetaFavorito(Model model, @PathVariable long id){
		Recipe recipe = recipeService.findOne(id);
		User userLogged = userRepository.findOne(userComponent.getLoggedUser().getId()); 
		userLogged.getFavRecipes().remove(recipe);
		userRepository.saveAndFlush(userLogged);
		//System.out.println(userLogged.getFavRecipes().get(0));
		return ("redirect:/recetas/");
	}
	
	@RequestMapping("/privado/mis-recetas")
	public String misRecetas(Model model, HttpServletRequest request) {
		
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		
		User user = userRepository.findOne(userComponent.getLoggedUser().getId()); 
		
		List<Recipe> myRecipes = user.getMyRecipes();
		
		model.addAttribute("myRecipes", myRecipes);
		
		return "misRecetas";
	}
	
	@RequestMapping("/privado/todas-recetas")
	public String todasRecetas(Model model, HttpServletRequest request) {
		
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		
		List<Recipe> allRecipes = recipeService.findAll();
		
		model.addAttribute("allRecipes", allRecipes);
		
		return "todasRecetas";
	}
		
	@RequestMapping("/recetas")
	public String buscador(Model model, @RequestParam String search,@RequestParam (required=false) String ingrediente, 
								   @RequestParam (required=false) String tipoComida) throws Exception {
		
		List<Recipe> recetas = new ArrayList<Recipe>();
		
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
		} else {
			model.addAttribute("usernotlogged", true);
		}
		
		if(ingrediente != null && tipoComida == null) {
			recetas = recipeService.findByIngredient(search);
			if(recetas.isEmpty()){
				return "recetas-not-found";
			}
			else {
				model.addAttribute("recetas", recetas);
				return "recetas";
			}
		}
		
		else if(ingrediente == null && tipoComida != null){
			recetas = recipeService.findByTypeFood(search);
			if(recetas.isEmpty()){
				return "recetas-not-found";
			}
			else {
				model.addAttribute("recetas", recetas);
				return "recetas";
			}
		}
		
		else if(ingrediente != null && tipoComida != null){
			recetas = recipeService.findByIngredient(search);
			for(Recipe recipe: recipeService.findByTypeFood(search)){
				recetas.add(recipe);
			}
			if(recetas.isEmpty()){
				return "recetas-not-found";
			}
			else {
				model.addAttribute("recetas", recetas);
				return "recetas";
			}
		}
		
		else if(search != "" && ingrediente == null && tipoComida == null) {
			recetas = recipeService.findByTitle(search);
			if(recetas.isEmpty()){
				return "recetas-not-found";
			}
			else {
				model.addAttribute("recetas", recetas);
				return "recetas";
			}
		}
		
		return "recetas-not-found";
	}
	
	@RequestMapping("/recetas/ingrediente")
	public String recetasPorIngrediente(Model model, @RequestParam String ingredient) throws Exception {
		
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
		} else {
			model.addAttribute("usernotlogged", true);
		}
		
		List<Recipe> recetas = recipeService.findByIngredient(ingredient);
		model.addAttribute("recetas", recetas);
		
		return "recetas";
	}
		
	@RequestMapping("/recetas/tipo-comida")
	public String recetasPorTipoComida(Model model, @RequestParam String typeFood) throws Exception {
		
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
		} else {
			model.addAttribute("usernotlogged", true);
		}
		
		List<Recipe> recetas = recipeService.findByTypeFood(typeFood);
		model.addAttribute("recetas", recetas);
		
		return "recetas";
	}
		
	
	@RequestMapping(method = RequestMethod.GET, value= "/moreRecipes")
	public String moreRecipes(Model model, @RequestParam int page) {

		Page<Recipe> recipes = recipeService.findAll(new PageRequest(page, 10));
		model.addAttribute("items", recipes);

		return "listItemsPage";
	}
	
}