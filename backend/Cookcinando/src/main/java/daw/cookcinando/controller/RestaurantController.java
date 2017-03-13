package daw.cookcinando.controller;

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

import daw.cookcinando.UserComponent;
import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.Restaurant;
import daw.cookcinando.model.User;
import daw.cookcinando.repository.RestaurantRepository;
import daw.cookcinando.repository.UserRepository;

@Controller
public class RestaurantController {
	
	private static final String FILES_FOLDER = "target/classes/static/img";
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private UserRepository userRepository;
	
	private List<String> typesFood = new ArrayList<>();
	
	
	@RequestMapping("/restaurantes") //Enlace para las restaurantes
	public String recetas (Model model) {
		model.addAttribute("restaurantes", restaurantRepository.findAll());
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
		} else {
			model.addAttribute("usernotlogged", true);
		}
		
		Page<Restaurant> restaurants = restaurantRepository.findAll(new PageRequest(0, 10));
		
		model.addAttribute("restaurantes", restaurants);
		
		return "restaurantes";
	}
	
	@RequestMapping("/restaurantes/{id}")
	public String verRestaurante(Model model, @PathVariable long id, HttpServletRequest request) {
		Restaurant restaurante = restaurantRepository.findOne(id);
		model.addAttribute("restaurante", restaurante);		
		List<Restaurant> recomendadas = new ArrayList<Restaurant>();
		int j=0;
		for (Restaurant rec : restaurantRepository.findAll()) {
			j++;
			recomendadas.add(rec);
			if(j==3){
				break;
			}
		}
		model.addAttribute("recomendadas", recomendadas);
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
			if (userLogged.getId() == restaurante.getAuthor().getId() || userLogged.isAdmin()) {
				model.addAttribute("creator", true);
			} else {
				User user = userRepository.findOne(userComponent.getLoggedUser().getId()); 
				if(user.getFavRestaurants().contains(restaurante)){
					model.addAttribute("notcreatorFAV", true);
				}else{
					model.addAttribute("notcreator", true);
				}
			}
		} else {
			model.addAttribute("usernotlogged", true);
		}
		return "restaurante";
	}
	
	@RequestMapping("/privado/restaurantes/crear")
	public String nuevoRestaurante(Model model, HttpServletRequest request) {
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "crearRestaurante";
	}
	
	@RequestMapping("/privado/restaurantes/form-crear")
	public String crearrestaurante(Model model, HttpServletRequest request, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam MultipartFile imagenCabecera, @RequestParam String cuerpo, @RequestParam String comidas) {
		List<String> comidasRestaurantes = new ArrayList<>();
		String comidasSeparadas[] = comidas.split(",");
		for(int i=0; i<comidasSeparadas.length; i++){
			comidasRestaurantes.add(comidasSeparadas[i]);
		}
		User userLogged = userComponent.getLoggedUser();
		if (!imagenCabecera.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				Restaurant restaurant = new Restaurant(titulo, descripcion, "", cuerpo, comidasRestaurantes, userLogged);
				restaurantRepository.save(restaurant);
				long id = restaurant.getId();
				String fileName = "cabRestaurante" + id + ".jpg";
				String thumbnail = "../img/" + fileName;
				restaurant.setThumbnail(thumbnail);
				restaurantRepository.save(restaurant);
				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				imagenCabecera.transferTo(uploadedFile);
				return ("redirect:/restaurantes/"+restaurant.getId());
			} catch (Exception e) {
				model.addAttribute("error",e.getClass().getName() + ":" + e.getMessage());
			}
		} else {
			Restaurant restaurant = new Restaurant(titulo, descripcion, "", cuerpo, comidasRestaurantes, userLogged);
			restaurantRepository.save(restaurant);
			return ("redirect:/restaurantes/"+restaurant.getId());
		}
		return ("redirect:/restaurantes");

	}
	
	@RequestMapping("/privado/restaurantes/editar/{id}")
	public String modificarRestaurante(Model model, HttpServletRequest request, @PathVariable Long id){
		Restaurant restaurant = restaurantRepository.findOne(id);
		model.addAttribute("restaurante", restaurant);
		User userLogged = userComponent.getLoggedUser();
		if (userLogged.getId() == restaurant.getAuthor().getId() || userLogged.isAdmin()) {
			model.addAttribute("creator", true);
		} else {
			model.addAttribute("deny", true);
		}
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "formularioEditarRestaurante";
	}
	
	@RequestMapping("/privado/restaurantes/form-editar/{id}")
	public String editarrestaurante(Model model, HttpServletRequest request, @PathVariable Long id, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam MultipartFile imagenCabecera, @RequestParam String cuerpo, @RequestParam String comidas) {
		List<String> comidasRestaurantes = new ArrayList<>();
		String comidasSeparadas[] = comidas.split(",");
		for(int i=0; i<comidasSeparadas.length; i++){
			comidasRestaurantes.add(comidasSeparadas[i]);
		}
		Restaurant restaurant = restaurantRepository.findOne(id);
		User userLogged = userComponent.getLoggedUser();
		if (userLogged.getId() == restaurant.getAuthor().getId() || userLogged.isAdmin()) {
			if (!imagenCabecera.isEmpty()) {
				try {
	
					File filesFolder = new File(FILES_FOLDER);
					if (!filesFolder.exists()) {
						filesFolder.mkdirs();
					}
					
					String fileName = "cabRestaurante" + id + ".jpg";
					String thumbnail = "../img/" + fileName;
					
					restaurant.setTitle(titulo);
					restaurant.setDescription(descripcion);
					restaurant.setBody(cuerpo);
					restaurant.setTypesFood(comidasRestaurantes);
					restaurant.setThumbnail(thumbnail);
					restaurantRepository.save(restaurant);
					File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
					imagenCabecera.transferTo(uploadedFile);
					return ("redirect:/restaurantes/"+restaurant.getId());
				} catch (Exception e) {
					model.addAttribute("error",e.getClass().getName() + ":" + e.getMessage());
				}
			} else {
				restaurant.setTitle(titulo);
				restaurant.setDescription(descripcion);
				restaurant.setBody(cuerpo);
				restaurant.setTypesFood(comidasRestaurantes);
				restaurantRepository.save(restaurant);
			}
		}
		return ("redirect:/restaurantes/"+id);
	}
	
	
	@RequestMapping("/privado/restaurantes/eliminar/{id}")
	public String eliminarRestaurant(Model model, @PathVariable Long id){
		Restaurant restaurant = restaurantRepository.findOne(id);
		User userLogged = userComponent.getLoggedUser();
		if (userLogged.getId() == restaurant.getAuthor().getId() || userLogged.isAdmin()){
			restaurantRepository.delete(restaurant);
		}
		return ("redirect:/restaurantes/");
	}
	
	@RequestMapping("/privado/restaurantes/add-fav/{id}")
	public String aniadirRestauranteFavorito(Model model, @PathVariable Long id){
		Restaurant restaurant = restaurantRepository.findOne(id);
		User userLogged = userRepository.findOne(userComponent.getLoggedUser().getId()); 
		userLogged.getFavRestaurants().add(restaurant);
		userRepository.saveAndFlush(userLogged);
		return ("redirect:/restaurantes/");
	}
	
	@RequestMapping("/privado/restaurantes/remove-fav/{id}")
	public String quitarRestauranteFavorito(Model model, @PathVariable long id){
		Restaurant restaurant = restaurantRepository.findOne(id);
		User userLogged = userRepository.findOne(userComponent.getLoggedUser().getId()); 
		userLogged.getFavRestaurants().remove(restaurant);
		userRepository.saveAndFlush(userLogged);
		//System.out.println(userLogged.getFavRecipes().get(0));
		return ("redirect:/restaurantes/");
	}
	
	@RequestMapping("/privado/mis-restaurantes")
	public String misRestaurantes(Model model, HttpServletRequest request) {
		
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		
		User user = userRepository.findOne(userComponent.getLoggedUser().getId()); 
		
		List<Restaurant> myRestaurants = user.getMyRestaurants();
		
		model.addAttribute("myRestaurants", myRestaurants);
		
		return "misRestaurantes";
	}
	
	@RequestMapping("/privado/todos-restaurantes")
	public String todosRestaurantes(Model model, HttpServletRequest request) {
		
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		
		List<Restaurant> allRestaurants = restaurantRepository.findAll();
		
		model.addAttribute("allRestaurants", allRestaurants);
		
		return "todosRestaurantes";
			
	}
	
	
	//@RequestMapping("/restaurantes")
	//public String restaurantesPorTitulo(Model model, @RequestParam String title) throws Exception {
		
		//User userLogged = userComponent.getLoggedUser();
		//if (userLogged != null) {
			//model.addAttribute("userlogged", true);
		//} else {
			//model.addAttribute("usernotlogged", true);
		//}
		
		//List<Restaurant> restaurantes = restaurantRepository.findByTitle(title.toLowerCase());
		//model.addAttribute("restaurantes", restaurantes);
		
		//return "restaurantes";
	//}
	
	@RequestMapping(method = RequestMethod.GET, value= "/moreRestaurants")
	public String moreRestaurants(Model model, @RequestParam int page) {

		Page<Restaurant> restaurants = restaurantRepository.findAll(new PageRequest(page, 10));
		model.addAttribute("items", restaurants);

		return "listItemsPage";
	}
			
}