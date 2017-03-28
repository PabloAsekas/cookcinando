package daw.cookcinando.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import daw.cookcinando.model.Event;
import daw.cookcinando.service.EventService;



@RestController
@RequestMapping("/api/events")
public class EventRestController {

	@Autowired
	private EventService eventservice;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<Event> getEvent() {
		return eventservice.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Event> getEvent(@PathVariable long id) {

		Event event = eventservice.findOne(id);
		if (event != null) {
			return new ResponseEntity<>(event, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Event createEvent(@RequestBody Event event) {

		eventservice.save(event);

		return event;
	}

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

}

