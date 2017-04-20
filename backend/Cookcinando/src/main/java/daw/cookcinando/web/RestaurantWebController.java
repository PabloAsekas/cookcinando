package daw.cookcinando.web;

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
import daw.cookcinando.model.UserAdmin;
import daw.cookcinando.model.UserEnterprise;
import daw.cookcinando.repository.RestaurantRepository;
import daw.cookcinando.repository.UserRepository;
import daw.cookcinando.service.RestaurantService;
import daw.cookcinando.service.UserService;

@Controller
public class RestaurantWebController {
	
	private static final String FILES_FOLDER = "target/classes/static/img";
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private UserService userService;
	
	private List<String> typesFood = new ArrayList<>();
	
	
	@RequestMapping("/restaurantes") //Enlace para las restaurantes
	public String recetas (Model model) {
		model.addAttribute("restaurantes", restaurantService.findAll());
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
		} else {
			model.addAttribute("usernotlogged", true);
		}
		
		Page<Restaurant> restaurants = restaurantService.findAll(new PageRequest(0, 10));
		
		model.addAttribute("restaurantes", restaurants);
		
		return "restaurantes";
	}
	
	@RequestMapping("/restaurantes/{id}")
	public String verRestaurante(Model model, @PathVariable long id, HttpServletRequest request) {
		Restaurant restaurante = restaurantService.findOne(id);
		model.addAttribute("restaurante", restaurante);		
		List<Restaurant> recomendadas = new ArrayList<Restaurant>();
		int j=0;
		for (Restaurant rec : restaurantService.findAll()) {
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
				User user = userService.findOne(userComponent.getLoggedUser().getId()); 
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
				restaurantService.save(restaurant);
				long id = restaurant.getId();
				String fileName = "cabRestaurante" + id + ".jpg";
				String thumbnail = "../img/" + fileName;
				restaurant.setThumbnail(thumbnail);
				restaurantService.save(restaurant);
				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				imagenCabecera.transferTo(uploadedFile);
				return ("redirect:/restaurantes/"+restaurant.getId());
			} catch (Exception e) {
				model.addAttribute("error",e.getClass().getName() + ":" + e.getMessage());
			}
		} else {
			Restaurant restaurant = new Restaurant(titulo, descripcion, "", cuerpo, comidasRestaurantes, userLogged);
			restaurantService.save(restaurant);
			return ("redirect:/restaurantes/"+restaurant.getId());
		}
		return ("redirect:/restaurantes");

	}
	
	@RequestMapping("/privado/restaurantes/editar/{id}")
	public String modificarRestaurante(Model model, HttpServletRequest request, @PathVariable Long id){
		Restaurant restaurant = restaurantService.findOne(id);
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
		Restaurant restaurant = restaurantService.findOne(id);
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
					restaurantService.save(restaurant);
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
				restaurantService.save(restaurant);
			}
		}
		return ("redirect:/restaurantes/"+id);
	}
	
	
	@RequestMapping("/privado/restaurantes/eliminar/{id}")
	public String eliminarRestaurant(Model model, @PathVariable Long id){
		Restaurant restaurant = restaurantService.findOne(id);
		User userLogged = userComponent.getLoggedUser();
		if (userLogged.getId() == restaurant.getAuthor().getId() || userLogged.isAdmin()){
			restaurantService.delete(restaurant.getId());
		}
		return ("redirect:/restaurantes/");
	}
	
	@RequestMapping("/privado/restaurantes/add-fav/{id}")
	public String aniadirRestauranteFavorito(Model model, @PathVariable Long id){
		Restaurant restaurant = restaurantService.findOne(id);
		User userLogged = userService.findOne(userComponent.getLoggedUser().getId()); 
		userLogged.getFavRestaurants().add(restaurant);
		userService.saveAndFlush(userLogged);
		return ("redirect:/restaurantes/");
	}
	
	@RequestMapping("/privado/restaurantes/remove-fav/{id}")
	public String quitarRestauranteFavorito(Model model, @PathVariable long id){
		Restaurant restaurant = restaurantService.findOne(id);
		User userLogged = userService.findOne(userComponent.getLoggedUser().getId()); 
		userLogged.getFavRestaurants().remove(restaurant);
		userService.saveAndFlush(userLogged);
		//System.out.println(userLogged.getFavRecipes().get(0));
		return ("redirect:/restaurantes/");
	}
	
	@RequestMapping("/privado/mis-restaurantes")
	public String misRestaurantes(Model model, HttpServletRequest request) {
		
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		
		User user = userService.findOne(userComponent.getLoggedUser().getId()); 
		
		if(user instanceof UserEnterprise){
			UserEnterprise user_enterprise = (UserEnterprise) user;
			List<Restaurant> myRestaurants = user_enterprise.getMyRestaurants();
			model.addAttribute("myRestaurants", myRestaurants);
		}
		
		else if(user instanceof UserAdmin){
			UserAdmin user_admin = (UserAdmin) user;
			List<Restaurant> myRestaurants = user_admin.getMyRestaurants();
			model.addAttribute("myRestaurants", myRestaurants);
		}
		
		return "misRestaurantes";
	}
	
	@RequestMapping("/privado/todos-restaurantes")
	public String todosRestaurantes(Model model, HttpServletRequest request) {
		
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		
		List<Restaurant> allRestaurants = restaurantService.findAll();
		
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
	
	@RequestMapping("/restaurantes/tipo-comida")
	public String restaurantesPorTipoComida(Model model, @RequestParam String typeFood) throws Exception {
		
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
		} else {
			model.addAttribute("usernotlogged", true);
		}
		
		List<Restaurant> restaurant = restaurantService.findByTypeFood(typeFood);
		model.addAttribute("restaurantes", restaurant);
		
		return "restaurantes";
	}
	
	@RequestMapping(method = RequestMethod.GET, value= "/moreRestaurants")
	public String moreRestaurants(Model model, @RequestParam int page) {

		Page<Restaurant> restaurants = restaurantService.findAll(new PageRequest(page, 10));
		model.addAttribute("items", restaurants);

		return "listItemsPageRestaurants";
	}
			
}