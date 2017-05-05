package daw.cookcinando.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import daw.cookcinando.UserComponent;
import daw.cookcinando.api.RecipeRestController.RecipeDetail;
import daw.cookcinando.model.Event;
import daw.cookcinando.model.Event.Users;
import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.Restaurant;
import daw.cookcinando.model.User;
import daw.cookcinando.service.EventService;



@RestController
@RequestMapping("/api/events")
public class EventRestController {

	@Autowired
	private EventService eventservice;
	
	@Autowired
	private UserComponent userComponent;
	
	@JsonView(Event.Basic.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Page<Event> getEvents(@RequestParam (required=false) String page) {
		if(page == null){
			return eventservice.findAll(new PageRequest(0, 10));
		}
		else {
			int numPage =  Integer.parseInt(page); 
			return eventservice.findAll(new PageRequest(numPage, 10));
		}
	}
	
	interface EventDetail extends Event.Basic, Event.Users, User.Basic { }
	
	@JsonView(EventDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Event> getEvent(@PathVariable long id) {
		Event event = eventservice.findOne(id);
		if (event != null) {
			return new ResponseEntity<>(event, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(EventDetail.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Event> createEvent(@RequestBody Event event) {
		if (userComponent.isLoggedUser()) {
			event.setAuthor(userComponent.getLoggedUser());
			eventservice.save(event);
			return new ResponseEntity<>(event, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@JsonView(EventDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Event> updateEvent(@PathVariable long id, @RequestBody Event updatedEvent) {
		Event evento = eventservice.findOne(id);
		if (evento != null) {
			User loggedUser = userComponent.getLoggedUser();
			if (loggedUser != null) {
				if (loggedUser.getId() == evento.getAuthor().getId() || loggedUser.isAdmin()) {
					updatedEvent.setId(id);
					updatedEvent.setAuthor(evento.getAuthor());
					eventservice.save(updatedEvent);
					return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Event> deleteEvent(@PathVariable long id) {
		Event evento = eventservice.findOne(id);
		if (evento != null) {
			User loggedUser = userComponent.getLoggedUser();
			if (loggedUser != null) {
				if (loggedUser.getId() == evento.getAuthor().getId() || loggedUser.isAdmin()) {			
					eventservice.delete(id);
					return new ResponseEntity<>(null, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(Event.Basic.class)
	@RequestMapping(value = "/recommended", method = RequestMethod.GET)
	public List<Event> getRecommended() {
		List<Event> recomendadas = new ArrayList<Event>();
		int j=0;
		for (Event eve : eventservice.findAll()) {
			j++;
			recomendadas.add(eve);
			if(j==3){
				break;
			}
		}
		return recomendadas;
	}
	
	@JsonView(Event.Basic.class)
	@RequestMapping(value = "/by-typeFood/", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> eventsByTypeFood(@RequestParam String typeFood){
		List<Event> eventsByTypeFood = eventservice.findByTypeFood(typeFood);
		if(eventsByTypeFood.size() > 0) {
			return new ResponseEntity(eventsByTypeFood, HttpStatus.OK);
		}
		else return new ResponseEntity(HttpStatus.NOT_FOUND);	
	}	
	
private static final String USER_IMAGE_FOLDER = "../../frontend/src/assets/img";
	
	@JsonView(EventDetail.class)
	@RequestMapping(value = "/uploadImage/{idEvent}", method = RequestMethod.POST)
	public ResponseEntity<Event> handleFileUpload(@RequestBody MultipartFile file, @PathVariable long idEvent) {

			Event evento = eventservice.findOne(idEvent);
			String fileName =idEvent  + "thumbnailevent.jpg";
			if (!file.isEmpty()) {
				try {
					File filesFolder = new File(USER_IMAGE_FOLDER);
					if (!filesFolder.exists()) {
						filesFolder.mkdirs();
					}
					
					File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
					file.transferTo(uploadedFile);
					
					evento.setThumbnail("assets/img/" + fileName);
					eventservice.save(evento);

					return new ResponseEntity<>(evento, HttpStatus.OK);


				} catch (Exception e) {
					return new ResponseEntity<>(evento, HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>(evento, HttpStatus.NOT_FOUND);

			}
		
			
	}

}