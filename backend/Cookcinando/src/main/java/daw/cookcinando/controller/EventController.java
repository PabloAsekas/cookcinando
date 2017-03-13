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
	
	//private List<String> typesFood = new ArrayList<>();
	
	//private List<String> courses = new ArrayList<>();
	
	@RequestMapping("/eventos") //Enlace para las eventos
	public String eventos (Model model) {
		model.addAttribute("eventos", eventRepository.findAll());
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
		} else {
			model.addAttribute("usernotlogged", true);
		}
		
		Page<Event> events = eventRepository.findAll(new PageRequest(0, 10));
		
		model.addAttribute("eventos", events);
		
		return "eventos";
	}
	
	@RequestMapping("/eventos/{id}")
	public String verEvento(Model model, @PathVariable long id, HttpServletRequest request) {
		Event event = eventRepository.findOne(id);
		model.addAttribute("evento", event);		
		List<Event> recomendadas = new ArrayList<Event>();
		int j=0;
		for (Event rec : eventRepository.findAll()) {
			j++;
			recomendadas.add(rec);
			if(j==3){
				break;
			}
		}
		model.addAttribute("recomendadas", recomendadas);
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
			if (userLogged.getId() == event.getAuthor().getId() || userLogged.isAdmin()) {
				model.addAttribute("creator", true);
			} else {
				User user = userRepository.findOne(userComponent.getLoggedUser().getId()); 
				if(user.getFavEvents().contains(event)){
					model.addAttribute("notcreatorFAV", true);
				}else{
					model.addAttribute("notcreator", true);
				}
			}
		} else {
			model.addAttribute("usernotlogged", true);
		}
		return "evento";
	}
	
	@RequestMapping("/privado/eventos/crear")
	public String nuevoEvento(Model model, HttpServletRequest request) {
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "crearEvento";
	}
	
	@RequestMapping("/privado/eventos/form-crear")
	public String crearevento(Model model, HttpServletRequest request, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam MultipartFile imagenCabecera, @RequestParam String cuerpo, @RequestParam String comidas) {
		List<String> comidasEventos = new ArrayList<>();
		String comidasSeparadas[] = comidas.split(",");
		for(int i=0; i<comidasSeparadas.length; i++){
			comidasEventos.add(comidasSeparadas[i]);
		}
		/*List<String> cursosEventos = new ArrayList<>();
		String cursosSeparados[] = clases.split(",");
		for(int i=0; i<cursosSeparados.length; i++){
			cursosEventos.add(cursosSeparados[i]);
		}*/
		User userLogged = userComponent.getLoggedUser();
		if (!imagenCabecera.isEmpty()) {
			try {
				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				Event event = new Event(titulo, descripcion, "", cuerpo, comidasEventos, userLogged);
				eventRepository.save(event);
				long id = event.getId();
				String fileName = "cabEvento" + id + ".jpg";
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
			Event event = new Event(titulo, descripcion, "", cuerpo, comidasEventos, userLogged);
			eventRepository.save(event);
			return ("redirect:/eventos/"+event.getId());
		}
		return ("redirect:/eventos");
	}
	
	@RequestMapping("/privado/eventos/editar/{id}")
	public String modificarEvento(Model model, HttpServletRequest request, @PathVariable Long id){
		Event evento = eventRepository.findOne(id);
		model.addAttribute("evento", evento);
		User userLogged = userComponent.getLoggedUser();
		if (userLogged.getId() == evento.getAuthor().getId() || userLogged.isAdmin()) {
			model.addAttribute("creator", true);
		} else {
			model.addAttribute("deny", true);
		}
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		return "formularioEditarEvento";
	}
	
	@RequestMapping("/privado/eventos/form-editar/{id}")
	public String editarreceta(Model model, @PathVariable Long id, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam MultipartFile imagenCabecera, @RequestParam String cuerpo, @RequestParam String comidas) {
		List<String> comidasEventos = new ArrayList<>();
		String comidasSeparadas[] = comidas.split(",");
		for(int i=0; i<comidasSeparadas.length; i++){
			comidasEventos.add(comidasSeparadas[i]);
		}
//		List<String> cursosEventos = new ArrayList<>();
//		String cursosSeparados[] = cursos.split(",");
//		for(int i=0; i<cursosSeparados.length; i++){
//			cursosEventos.add(cursosSeparados[i]);
//		}
		Event event = eventRepository.findOne(id);
		if (!imagenCabecera.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				
				String fileName = "cabEvento" + id + ".jpg";
				String thumbnail = "../img/" + fileName;
				
				event.setTitle(titulo);
				event.setDescription(descripcion);
				event.setTypesFood(comidasEventos);
				//event.setCourses(cursosEventos);
				event.setThumbnail(thumbnail);
				event.setBody(cuerpo);
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
			event.setBody(cuerpo);
			//event.setCourses(cursosEventos);
			eventRepository.save(event);
		}
		return ("redirect:/eventos/"+id);
	}
	
	
	@RequestMapping("/privado/eventos/eliminar/{id}")
	public String eliminarEvento(Model model, @PathVariable Long id){
		Event event = eventRepository.findOne(id);
		User userLogged = userComponent.getLoggedUser();
		if (userLogged.getId() == event.getAuthor().getId() || userLogged.isAdmin()) {
			eventRepository.delete(event);
		}
		return ("redirect:/eventos/");
	}
	
	@RequestMapping("/privado/eventos/add-fav/{id}")
	public String aniadirEventoFavorito(Model model, @PathVariable Long id){
		Event event = eventRepository.findOne(id);
		User userLogged = userRepository.findOne(userComponent.getLoggedUser().getId());
		userLogged.getFavEvents().add(event);
		userRepository.saveAndFlush(userLogged);
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
	
	
	@RequestMapping(method = RequestMethod.GET, value= "/moreEvents")
	public String moreEvents(Model model, @RequestParam int page) {

		Page<Event> events = eventRepository.findAll(new PageRequest(page, 10));
		model.addAttribute("items", events);

		return "listItemsPage";
	}
	
	
	
}