package daw.cookcinando.api;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import daw.cookcinando.UserComponent;
import daw.cookcinando.model.User;

@RestController
public class LoginRestController {

	private static final Logger log = LoggerFactory.getLogger(LoginRestController.class);
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping("/api/logIn")
	public ResponseEntity<User> logIn() {
		
		if(!userComponent.isLoggedUser()) {
			log.info("Not user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		else {
			User loggedUser = userComponent.getLoggedUser();
			log.info("Logged as " + loggedUser.getNick());
			return new ResponseEntity<>(loggedUser, HttpStatus.OK);
		}
	}
	
	@RequestMapping("/api/logOut")
	public ResponseEntity<Boolean> logOut(HttpSession session) {
		
		if(!userComponent.isLoggedUser()) {
			log.info("Not user logged");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		else {
			session.invalidate();
			log.info("Logged out");
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}
}