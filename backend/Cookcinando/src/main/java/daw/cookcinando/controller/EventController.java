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
import daw.cookcinando.model.Event;
import daw.cookcinando.model.Restaurant;
import daw.cookcinando.model.User;
import daw.cookcinando.repository.EventRepository;
import daw.cookcinando.repository.UserRepository;

@Controller
public class EventController {
	
	private static final String FILES_FOLDER = "target/classes/static/img";
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private UserRepository userRepository;
	
	private List<String> typesFood = new ArrayList<>();
	
	private List<String> courses = new ArrayList<>();
	
	@RequestMapping("/eventos") //Enlace para las eventos
	public String eventos (Model model) {
		model.addAttribute("eventos", eventRepository.findAll());
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
		} else {
			model.addAttribute("usernotlogged", true);
		}
		return "eventos";
	}
	
	@RequestMapping("/eventos/{id}")
	public String verEvento(Model model, @PathVariable long id, HttpServletRequest request) {
		Event event = eventRepository.findOne(id);
		model.addAttribute("evento", event);		
		List<Event> recomendadas = new ArrayList<Event>();
		for (long i = 1; i < 4; i++) {
			recomendadas.add(eventRepository.getOne(i));
		}
		model.addAttribute("recomendadas", recomendadas);
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			if (event.getAuthor().getId() == userLogged.getId()) {
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
		return "evento";
	}
	
	@RequestMapping("/privado/eventos/crear")
	public String nuevoEvento(Model model, HttpServletRequest request) {
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "crearEvento";
	}
	
	@RequestMapping("/privado/eventos/form-crear")
	public String crearevento(Model model, HttpServletRequest request, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam MultipartFile imagenCabecera, @RequestParam String comidas, @RequestParam String clases) {
		List<String> comidasEventos = new ArrayList<>();
		String comidasSeparadas[] = comidas.split(",");
		for(int i=0; i<comidasSeparadas.length; i++){
			comidasEventos.add(comidasSeparadas[i]);
		}
		List<String> cursosEventos = new ArrayList<>();
		String cursosSeparados[] = clases.split(",");
		for(int i=0; i<cursosSeparados.length; i++){
			cursosEventos.add(cursosSeparados[i]);
		}
		User userLogged = userComponent.getLoggedUser();
		if (!imagenCabecera.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				Event event = new Event(titulo, descripcion, "",comidasEventos, cursosEventos, userLogged);
				eventRepository.save(event);
				long id = event.getId();
				String fileName = "cabReceta" + id + ".jpg";
				String thumbnail = "../img/" + fileName;
				event.setThumbnail(thumbnail);
				eventRepository.save(event);
				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				imagenCabecera.transferTo(uploadedFile);
				return ("redirect:/eventos/"+event.getId());
			} catch (Exception e) {
				model.addAttribute("error",e.getClass().getName() + ":" + e.getMessage());
			}
		} else {
			Event event = new Event(titulo, descripcion, "", comidasEventos, cursosEventos, userLogged);
			eventRepository.save(event);
			return ("redirect:/eventos/"+event.getId());
		}
		return ("redirect:/eventos");

//		model.addAttribute("evento", event);
//		List<Event> recomendadas = new ArrayList<Event>();
//		for (long i = 1; i < 4; i++) {
//			recomendadas.add(eventRepository.getOne(i));
//		}
//		model.addAttribute("recomendadas", recomendadas);
//		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
//		model.addAttribute("admin", request.isUserInRole("ADMIN"));
	}
	
	@RequestMapping("/privado/eventos/editar/{id}")
	public String modificarEvento(Model model, @PathVariable Long id){
		Event evento = eventRepository.findOne(id);
		model.addAttribute("evento", evento);
		return "formularioEditarEvento";
	}
	
	@RequestMapping("/privado/eventos/form-editar/{id}")
	public String editarreceta(Model model, @PathVariable Long id, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam MultipartFile imagenCabecera, @RequestParam String comidas, @RequestParam String cursos) {
		List<String> comidasEventos = new ArrayList<>();
		String comidasSeparadas[] = comidas.split(",");
		for(int i=0; i<comidasSeparadas.length; i++){
			comidasEventos.add(comidasSeparadas[i]);
		}
		List<String> cursosEventos = new ArrayList<>();
		String cursosSeparados[] = cursos.split(",");
		for(int i=0; i<cursosSeparados.length; i++){
			cursosEventos.add(cursosSeparados[i]);
		}
		Event event = eventRepository.findOne(id);
		if (!imagenCabecera.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				
				String fileName = "cabReceta" + id + ".jpg";
				String thumbnail = "../img/" + fileName;
				
				event.setTitle(titulo);
				event.setDescription(descripcion);
				event.setTypesFood(comidasEventos);
				event.setCourses(cursosEventos);
				event.setThumbnail(thumbnail);
				eventRepository.save(event);
				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				imagenCabecera.transferTo(uploadedFile);
				return ("redirect:/eventos/"+event.getId());
			} catch (Exception e) {
				model.addAttribute("error",e.getClass().getName() + ":" + e.getMessage());
			}
		} else {
			event.setTitle(titulo);
			event.setDescription(descripcion);
			event.setTypesFood(comidasEventos);
			event.setCourses(cursosEventos);
			eventRepository.save(event);
		}
		return ("redirect:/eventos/"+id);
	}
	
	
	@RequestMapping("/privado/eventos/eliminar/{id}")
	public String eliminarEvento(Model model, @PathVariable Long id){
		Event event = eventRepository.findOne(id);
//		User userLogged = userComponent.getLoggedUser();
//		if(evento.getAuthor() == userLogged){
			eventRepository.delete(event);
//		}
		return ("redirect:/eventos/");
	}
	
	@RequestMapping("/privado/eventos/add-fav/{id}")
	public String aniadirEventoFavorito(Model model, @PathVariable Long id){
		Event event = eventRepository.findOne(id);
		User userLogged = userComponent.getLoggedUser();
//	añadir el id evento a la lista de eventos favoritos del usuario userlogged
		return ("redirect:/eventos/");
	}
	
	@RequestMapping("/privado/eventos/remove-fav/{id}")
	public String quitarEventoFavorito(Model model, @PathVariable long id){
		Event event = eventRepository.findOne(id);
		User userLogged = userRepository.findOne(userComponent.getLoggedUser().getId()); 
		userLogged.getFavEvents().remove(event);
		userRepository.saveAndFlush(userLogged);
		//System.out.println(userLogged.getFavRecipes().get(0));
		return ("redirect:/eventos/");
	}
	
	@RequestMapping("/privado/mis-eventos")
	public String misRecetas(Model model, HttpServletRequest request) {
		
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		
		User user = userRepository.findOne(userComponent.getLoggedUser().getId()); 
		
		List<Event> myEvents = user.getMyEvents();
		
		model.addAttribute("myEvents", myEvents);
		
		return "misEventos";
	}
	
	@RequestMapping("/privado/todas-eventos")
	public String todosEventos(Model model, HttpServletRequest request) {
		
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		
		List<Event> allEvents = eventRepository.findAll();
		
		model.addAttribute("allEvents", allEvents);
		
		return "todosEventos";
	}
}