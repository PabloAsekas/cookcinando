package daw.cookcinando.api;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import daw.cookcinando.model.User;
import daw.cookcinando.model.UserAdmin;
import daw.cookcinando.model.UserBasic;
import daw.cookcinando.model.UserEnterprise;
import daw.cookcinando.service.UserService;

@RestController
@RequestMapping("/api/usuarios")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<User> getUsers() {
		return userService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable long id) {
		
		User user = userService.findOne(id);
		if(user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		
		if(user.getRoles().size() == 1){
			UserBasic basic = new UserBasic(user.getName(),user.getSurname(),user.getDescription(), 
											user.getImage(), user.getNick(),user.getEmail(), 
											user.getPasswordHash());
			basic.setRoles(user.getRoles());
			userService.save(basic);
			return basic;
		}
		
		else if(user.getRoles().size() == 2){
			UserEnterprise enterprise = new UserEnterprise(user.getName(),user.getSurname(),user.getDescription(), 
											user.getImage(), user.getNick(),user.getEmail(), 
											user.getPasswordHash());
			enterprise.setRoles(user.getRoles());
			userService.save(enterprise);
			return enterprise;
		}
		
		else if(user.getRoles().size() == 3){
			UserAdmin admin = new UserAdmin(user.getName(),user.getSurname(),user.getDescription(), 
											user.getImage(), user.getNick(),user.getEmail(), 
											user.getPasswordHash());
			admin.setRoles(user.getRoles());
			userService.save(admin);
			return admin;
		}
		
		else return null;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User updatedUser) {
		
		User user = userService.findOne(id);
		
		if(user != null) {
			updatedUser.setId(id);
			userService.save(updatedUser);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable long id) {
		
		userService.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
}
