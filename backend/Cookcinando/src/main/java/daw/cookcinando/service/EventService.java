package daw.cookcinando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daw.cookcinando.model.Event;
import daw.cookcinando.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	public Event findOne(long id) {
		return eventRepository.findOne(id);
	}
	
	public List<Event> findAll() {
		return eventRepository.findAll();
	}
	
	public void save(Event event) {
		eventRepository.save(event);
	}
	
	public void delete(long id) {
		eventRepository.delete(id);
	}
	
	public Event findByTitle(String title) {
		Event event = null;
		
		for(Event e : eventRepository.findAll()){
			if(e.getTitle() == title){
				event = e;
				break;
			}
		}
		return event;
	}
	
	public Event findByTypesFood(List<String> typesFood) {
		Event event = null;
		
		for(Event e : eventRepository.findAll()){
			if(e.getTypesFood() == typesFood){
				event = e;
				break;
			}
		}
		return event;
	}
	
}
