package daw.cookcinando;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.User;
import daw.cookcinando.model.UserBasic;
import daw.cookcinando.repository.RecipeRepository;
import daw.cookcinando.repository.UserRepository;

@Component
public class DatabaseUsersLoader{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	//@PostConstruct
	private void initDatabase(){
		System.out.println(recipeRepository.findAll());
//		UserBasic user_basic = new UserBasic("A", "A", "A", "A", "A","basic","pass","ROLE_BASIC");
//		//user_basic.setRecipe(recipe);
//		
//		userRepository.save(user_basic);
//		userRepository.save(new User("B", "B", "B", "B", "B","enterprise","pass","ROLE_BASIC","ROLE_ENTERPRISE"));
//		userRepository.save(new User("C", "C", "C", "C", "C","admin","adminpass","ROLE_BASIC","ROLE_ENTERPRISE", "ROLE_ADMIN"));
		
	}

	
}