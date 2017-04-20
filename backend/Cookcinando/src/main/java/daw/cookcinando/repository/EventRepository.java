package daw.cookcinando.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import daw.cookcinando.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	@Query(value = "SELECT * FROM event r, event_types_food t where r.id = t.event_id and t.types_food = ?1", nativeQuery = true)
	List<Event> findByTypeFood(String type);
	
}
