package daw.cookcinando.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;

import daw.cookcinando.UserComponent;
import daw.cookcinando.model.Restaurant;
import daw.cookcinando.model.User;
import daw.cookcinando.repository.RestaurantRepository;

@Controller
public class RestaurantController {
	
	private static final String FILES_FOLDER = "target/classes/static/img";
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	//@Autowired
	//private UserRepository userRepository;
	
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
		return "restaurantes";
	}
	
	@RequestMapping("/restaurantes/{id}")
	public String verRestaurante(Model model, @PathVariable long id, HttpServletRequest request) {
		Restaurant restaurante = restaurantRepository.findOne(id);
		model.addAttribute("restaurante", restaurante);		
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
	public String nuevoRestaurante(Model model, HttpServletRequest request) {
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "crearRestaurante";
	}
	
	@RequestMapping("/privado/restaurantes/form-crear")
	public String crearrestaurante(Model model, HttpServletRequest request, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam MultipartFile imagenCabecera, @RequestParam String comidas) {
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
				Restaurant restaurant = new Restaurant(titulo, descripcion, "", comidasRestaurantes, userLogged);
				restaurantRepository.save(restaurant);
				long id = restaurant.getId();
				String fileName = "cabReceta" + id + ".jpg";
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
			Restaurant restaurant = new Restaurant(titulo, descripcion, "", comidasRestaurantes, userLogged);
			restaurantRepository.save(restaurant);
			return ("redirect:/restaurantes/"+restaurant.getId());
		}
		return ("redirect:/restaurantes");

	}
	
	@RequestMapping("/privado/restaurantes/editar/{id}")
	public String modificarRestaurante(Model model, @PathVariable Long id){
		Restaurant restaurant = restaurantRepository.findOne(id);
		model.addAttribute("restaurante", restaurant);
		return "formularioEditar";
	}
	
	@RequestMapping("/privado/restaurantes/form-editar/{id}")
	public String editarrestaurante(Model model, @PathVariable Long id, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam MultipartFile imagenCabecera, @RequestParam String comidas) {
		List<String> comidasRestaurantes = new ArrayList<>();
		String comidasSeparadas[] = comidas.split(",");
		for(int i=0; i<comidasSeparadas.length; i++){
			comidasRestaurantes.add(comidasSeparadas[i]);
		}
		Restaurant restaurant = restaurantRepository.findOne(id);
		if (!imagenCabecera.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				
				String fileName = "cabReceta" + id + ".jpg";
				String thumbnail = "../img/" + fileName;
				
				restaurant.setTitle(titulo);
				restaurant.setDescription(descripcion);
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
			restaurant.setTypesFood(comidasRestaurantes);
			restaurantRepository.save(restaurant);
		}
		return ("redirect:/restaurantes/"+id);
	}
	
	
	@RequestMapping("/privado/restaurantes/eliminar/{id}")
	public String eliminarRestaurant(Model model, @PathVariable Long id){
		Restaurant restaurante = restaurantRepository.findOne(id);
//		User userLogged = userComponent.getLoggedUser();
//		if(receta.getAuthor() == userLogged){
			restaurantRepository.delete(restaurante);
//		}
		return ("redirect:/restaurantes/");
	}
	
	@RequestMapping("/privado/restaurantes/add-fav/{id}")
	public String aniadirRestauranteFavorito(Model model, @PathVariable Long id){
		Restaurant restaurante = restaurantRepository.findOne(id);
		User userLogged = userComponent.getLoggedUser();
//	añadir a la receta id a la lista de recetas favoritas del usuario userlogged
		return ("redirect:/restaurantes/");
	}
	
	
}