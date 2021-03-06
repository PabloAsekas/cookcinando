package daw.cookcinando.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import daw.cookcinando.model.Event;
import daw.cookcinando.model.Recipe;
import daw.cookcinando.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	public Event findOne(long id) {
		return eventRepository.findOne(id);
	}
	
	public Page<Event> findAll(Pageable pageable) {
		return eventRepository.findAll(pageable);
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
	
	/*public Event findByTitle(String title) {
		Event event = null;
		
		for(Event e : eventRepository.findAll()){
			if(e.getTitle() == title){
				event = e;
				break;
			}
		}
		return event;
	}*/
	

	
	public List<Event> findByTypeFood(String typeFood) {
		
		return eventRepository.findByTypeFood(typeFood);
		
		/*List<Event> events = new ArrayList<Event>();
		
		for(Event e : eventRepository.findAll()){
			
			for(int i=0; i<e.getTypesFood().size(); i++){
				if(e.getTypesFood().get(i).toLowerCase().equals(typeFood.toLowerCase())){
					events.add(e);
					break;
				}
			}
		}
		return events;*/
	}
	
}
