package daw.cookcinando.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import daw.cookcinando.model.Event;
import daw.cookcinando.model.Event.Users;
import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.User;
import daw.cookcinando.service.EventService;



@RestController
@RequestMapping("/api/events")
public class EventRestController {

	@Autowired
	private EventService eventservice;
	
	@JsonView(Event.Basic.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Page<Event> getEvents(Pageable pageable) {
		return eventservice.findAll(pageable);
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
	public Event createEvent(@RequestBody Event event) {

		eventservice.save(event);

		return event;
	}
	
	@JsonView(EventDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Event> updateEvent(@PathVariable long id, @RequestBody Event updatedEvent) {

		Event evento = eventservice.findOne(id);
		if (evento != null) {

			updatedEvent.setId(id);
			eventservice.save(updatedEvent);

			return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Event> deleteEvent(@PathVariable long id) {

		eventservice.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
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

}

