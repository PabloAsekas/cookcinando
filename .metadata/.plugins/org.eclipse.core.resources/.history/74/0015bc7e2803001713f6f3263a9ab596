package daw.cookcinando;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import daw.cookcinando.model.User;
import daw.cookcinando.model.UserBasic;

@Component
public class DatabaseUsersLoader {

	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	private void initDatabase(){
		
		userRepository.save(new UserBasic("A", "A", "A", "A", "A","basic","pass","ROLE_BASIC"));
		userRepository.save(new User("B", "B", "B", "B", "B","enterprise","pass","ROLE_BASIC","ROLE_ENTERPRISE"));
		userRepository.save(new User("C", "C", "C", "C", "C","admin","adminpass","ROLE_BASIC","ROLE_ENTERPRISE", "ROLE_ADMIN"));
		
	}
}
