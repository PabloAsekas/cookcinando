package daw.cookcinando.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import daw.cookcinando.model.Recipe;
import daw.cookcinando.repository.RestaurantRepository;

@Controller
public class RestaurantController {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
}