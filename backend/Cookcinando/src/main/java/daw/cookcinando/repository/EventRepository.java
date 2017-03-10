package daw.cookcinando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import daw.cookcinando.model.Event;
import daw.cookcinando.model.Recipe;

public interface EventRepository extends JpaRepository<Event, Long> {

}
