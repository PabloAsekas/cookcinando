package daw.cookcinando;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import daw.cookcinando.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	//User findByName(String name);
	User findByEmail(String email);
}
