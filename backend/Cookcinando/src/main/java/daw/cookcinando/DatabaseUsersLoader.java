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
		
		userRepository.save(new UserBasic("basic", "A", "A", "A", "A","A","pass","ROLE_BASIC"));
		userRepository.save(new User("enterprise", "B", "B", "B", "B","B","pass","ROLE_BASIC","ROLE_ENTERPRISE"));
		userRepository.save(new User("admin", "C", "C", "C", "C","C","adminpass","ROLE_BASIC","ROLE_ENTERPRISE", "ROLE_ADMIN"));
		
	}
}
