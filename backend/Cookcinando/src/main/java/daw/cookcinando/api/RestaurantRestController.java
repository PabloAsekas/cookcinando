package daw.cookcinando.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import daw.cookcinando.UserComponent;
import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.Restaurant;
import daw.cookcinando.model.User;
import daw.cookcinando.service.RestaurantService;



@RestController
@RequestMapping("/api/restaurants")
public class RestaurantRestController {

	@Autowired
	private RestaurantService restaurantservice;
	
	@Autowired
	private UserComponent userComponent;

	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Page<Restaurant> getRestaurants(@RequestParam (required=false) String page) {
		if(page == null){
			return restaurantservice.findAll(new PageRequest(0, 9));
		}
		else {
			int numPage =  Integer.parseInt(page); 
			return restaurantservice.findAll(new PageRequest(numPage, 9));
		}
	}
	
	interface RestaurantDetail extends Restaurant.Basic, Restaurant.Users, User.Basic { }

	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Restaurant> getRestaurant(@PathVariable long id) {
		Restaurant restaurant = restaurantservice.findOne(id);
		if (restaurant != null) {
			return new ResponseEntity<>(restaurant, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
		if (userComponent.isLoggedUser()) {
			restaurant.setAuthor(userComponent.getLoggedUser());
			restaurantservice.save(restaurant);
			return new ResponseEntity<>(restaurant, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@JsonView(RestaurantDetail.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable long id, @RequestBody Restaurant updatedRestaurant) {
		Restaurant restaurante = restaurantservice.findOne(id);
		if (restaurante != null) {
			User loggedUser = userComponent.getLoggedUser();
			if (loggedUser != null) {
				if (loggedUser.getId() == restaurante.getAuthor().getId() || loggedUser.isAdmin()) {
					updatedRestaurant.setId(id);
					updatedRestaurant.setAuthor(restaurante.getAuthor());
					restaurantservice.save(updatedRestaurant);
					return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
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

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable long id) {
		Restaurant restaurant = restaurantservice.findOne(id);
		if (restaurant != null) {
			User loggedUser = userComponent.getLoggedUser();
			if (loggedUser != null) {
				if (loggedUser.getId() == restaurantservice.findOne(id).getAuthor().getId() || loggedUser.isAdmin()) {
					restaurantservice.delete(id);
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
	
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value = "/recommended", method = RequestMethod.GET)
	public List<Restaurant> getRecommended() {
		List<Restaurant> recomendadas = new ArrayList<Restaurant>();
		int j=0;
		for (Restaurant res : restaurantservice.findAll()) {
			j++;
			recomendadas.add(res);
			if(j==3){
				break;
			}
		}
		return recomendadas;
	}
	
	@JsonView(Restaurant.Basic.class)
	@RequestMapping(value = "/by-typeFood/", method = RequestMethod.GET)
	public ResponseEntity<List<Restaurant>> restaurantsByTypeFood(@RequestParam String typeFood){
		List<Restaurant> restaurantsByTypeFood = restaurantservice.findByTypeFood(typeFood);
		if(restaurantsByTypeFood.size() > 0) {
			return new ResponseEntity(restaurantsByTypeFood, HttpStatus.OK);
		}
		else return new ResponseEntity(HttpStatus.NOT_FOUND);	
	}	
}

