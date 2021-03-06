package daw.cookcinando.api;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import daw.cookcinando.UserComponent;
import daw.cookcinando.model.Event;
import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.Restaurant;
import daw.cookcinando.model.User;
import daw.cookcinando.model.UserAdmin;
import daw.cookcinando.model.UserBasic;
import daw.cookcinando.model.UserEnterprise;
import daw.cookcinando.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserComponent userComponent;
	
	@JsonView(User.Basic.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<User> getUsers() {
		return userService.findAll();
	}
	
	interface UserDetail extends User.Basic, User.AllTheInfo, Recipe.Basic, Restaurant.Basic, Event.Basic { }
	
	@JsonView(UserDetail.class)
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
	
	@JsonView(Recipe.Basic.class)
	@RequestMapping(value = "/{id}/favorite-recipes", method = RequestMethod.GET)
	public ResponseEntity<List<Recipe>> getFavoriteRecipes(@PathVariable long id) {
		User user = userService.findOne(id);
		if(user != null) {
			return new ResponseEntity<>(user.getFavRecipes(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value = "/{id}/favorite-restaurants", method = RequestMethod.GET)
	public ResponseEntity<List<Restaurant>> getFavoriteRestaurants(@PathVariable long id) {
		User user = userService.findOne(id);
		if(user != null) {
			return new ResponseEntity<>(user.getFavRestaurants(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(Event.Basic.class)
	@RequestMapping(value = "/{id}/favorite-events", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getFavoriteEvents(@PathVariable long id) {
		User user = userService.findOne(id);
		if(user != null) {
			return new ResponseEntity<>(user.getFavEvents(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/registry", method = RequestMethod.POST)
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
	
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User updatedUser) {
		
		User user = userService.findOne(id);
		
		if(user != null) {
			
			User loggedUser = userComponent.getLoggedUser();
			if (loggedUser != null) {
				if (loggedUser.getId() == user.getId() || loggedUser.isAdmin()) {
					if (updatedUser.getName() != null) {
						user.setName(updatedUser.getName());
					}
					if (updatedUser.getSurname() != null) {
						user.setSurname(updatedUser.getSurname());
					}
					if (updatedUser.getDescription() != null) {
						user.setDescription(updatedUser.getDescription());
					}
					if (updatedUser.getImage() != null) {
						user.setImage(updatedUser.getImage());
					}
					if (updatedUser.getNick() != null) {
						user.setNick(updatedUser.getNick());
					}
					if (updatedUser.getEmail() != null) {
						user.setEmail(updatedUser.getEmail());
					}
					if (updatedUser.getPasswordHash() != null) {
						String password = new BCryptPasswordEncoder().encode(updatedUser.getPasswordHash());
						user.setPasswordHash(password);
					}
					if (updatedUser.getRoles() != null) {
						user.setRoles(updatedUser.getRoles());
					}
					if (updatedUser.getFavRecipes() != null) {
						user.setFavRecipes(updatedUser.getFavRecipes());
					}
					if (updatedUser.getFavRestaurants() != null) {
						user.setFavRestaurants(updatedUser.getFavRestaurants());
					}
					if (updatedUser.getFavEvents() != null) {
						user.setFavEvents(updatedUser.getFavEvents());
					}
					userService.saveAndFlush(user);
					
					return new ResponseEntity<>(user, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable long id) {
		User usuario = userService.findOne(id);
		if (usuario != null) {
			User loggedUser = userComponent.getLoggedUser();
			if (loggedUser != null) {
				if (loggedUser.getId() == userService.findOne(id).getId() || loggedUser.isAdmin()) {
					userService.delete(id);
					return new ResponseEntity<>(null, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	private static final String USER_IMAGE_FOLDER = "../../frontend/src/assets/img";
	
	@JsonView(UserDetail.class)
	@RequestMapping(value = "/uploadImage/{idUser}", method = RequestMethod.POST)
	public ResponseEntity<User> handleFileUpload(@RequestBody MultipartFile file, @PathVariable long idUser) {

			User user = userService.findOne(idUser);

			String fileName = idUser  + "thumbnailuser.jpg";
			if (!file.isEmpty()) {
				try {
					File filesFolder = new File(USER_IMAGE_FOLDER);
					if (!filesFolder.exists()) {
						filesFolder.mkdirs();
					}
					
					File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
					file.transferTo(uploadedFile);
					
					user.setImage("assets/img/" + fileName);
					userService.save(user);

					return new ResponseEntity<>(user, HttpStatus.OK);


				} catch (Exception e) {
					return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);

			}
		
			
	}
	
}
