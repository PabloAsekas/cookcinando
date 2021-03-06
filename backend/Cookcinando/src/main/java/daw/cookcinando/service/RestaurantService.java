package daw.cookcinando.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.Restaurant;
import daw.cookcinando.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public Restaurant findOne(long id) {
		return restaurantRepository.findOne(id);
	}
	
	public Page<Restaurant> findAll(Pageable pageable) {
		return restaurantRepository.findAll(pageable);
	}
	
	public List<Restaurant> findAll() {
		return restaurantRepository.findAll();
	}
	
	public void save(Restaurant restaurant) {
		restaurantRepository.save(restaurant);
	}
	
	public void delete(long id) {
		restaurantRepository.delete(id);
	}
	
	/*public Restaurant findByTitle(String title) {
		Restaurant restaurant = null;
		
		for(Restaurant re : restaurantRepository.findAll()){
			if(re.getTitle() == title){
				restaurant = re;
				break;
			}
		}
		return restaurant;
	}*/
	
	
	public List<Restaurant> findByTypeFood(String typeFood) {
		
		 return restaurantRepository.findByTypeFood(typeFood);
		
		/*List<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		for(Restaurant re : restaurantRepository.findAll()){
			
			for(int i=0; i<re.getTypesFood().size(); i++){
				if(re.getTypesFood().get(i).toLowerCase().equals(typeFood.toLowerCase())){
					restaurants.add(re);
					break;
				}
			}
		}
		return restaurants;*/
	}
}
