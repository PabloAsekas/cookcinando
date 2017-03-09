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
	
	@Autowired
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
			if (restaurant.getAuthor().getId() == userLogged.getId()) {
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
	public String crearrestaurante(Model model, HttpServletRequest request, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam String imagen, @RequestParam String comidas) {
		List<String> comidasRestaurantes = new ArrayList<>();
		String comidasSeparadas[] = comidas.split(",");
		for(int i=0; i<comidasSeparadas.length; i++){
			comidasRestaurantes.add(comidasSeparadas[i]);
		}
		User userLogged = userComponent.getLoggedUser();
		Restaurant restaurant = new Restaurant(titulo, descripcion, imagen, comidasRestaurantes, userLogged);
		restaurantRepository.save(restaurant);
		return ("redirect:/restaurantes/"+restaurant.getId());
	}
	
	@RequestMapping("/privado/restaurantes/editar/{id}")
	public String modificarRestaurante(Model model, @PathVariable Long id){
		Restaurant restaurante = restaurantRepository.findOne(id);
		model.addAttribute("restaurante", restaurante);
		return "formularioEditar";
	}
	
	@RequestMapping("/privado/restaurantes/form-editar/{id}")
	public String editarestaurante(Model model, @PathVariable Long id, @RequestParam String titulo, @RequestParam String descripcion,@RequestParam String imagen, @RequestParam String comidas) {
		List<String> comidasRestaurantes = new ArrayList<>();
		String comidasSeparadas[] = comidas.split(",");
		for(int i=0; i<comidasSeparadas.length; i++){
			comidasRestaurantes.add(comidasSeparadas[i]);
		}
		Restaurant restaurant = restaurantRepository.findOne(id);
		restaurant.setTitle(titulo);
		restaurant.setDescription(descripcion);
		restaurant.setThumbnail(imagen);
		restaurant.setTypesFood(comidasRestaurantes);
		restaurantRepository.save(restaurant);
		return ("redirect:/restaurantes/"+id);
	}
}
	
