package daw.cookcinando.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import daw.cookcinando.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//User findByName(String name);
	User findByEmail(String email);
}
