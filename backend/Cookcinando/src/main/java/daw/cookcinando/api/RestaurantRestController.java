package daw.cookcinando.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import daw.cookcinando.model.Restaurant;
import daw.cookcinando.service.RestaurantService;



@RestController
@RequestMapping("/api/restaurants")
public class RestaurantRestController {

	@Autowired
	private RestaurantService restaurantservice;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<Restaurant> getRestaurants() {
		return restaurantservice.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Restaurant> getRestaurant(@PathVariable long id) {

		Restaurant restaurant = restaurantservice.findOne(id);
		if (restaurant != null) {
			return new ResponseEntity<>(restaurant, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {

		restaurantservice.save(restaurant);

		return restaurant;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable long id, @RequestBody Restaurant updatedRestaurant) {

		Restaurant restaurante = restaurantservice.findOne(id);
		if (restaurante != null) {

			updatedRestaurant.setId(id);
			restaurantservice.save(updatedRestaurant);

			return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable long id) {

		restaurantservice.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}

