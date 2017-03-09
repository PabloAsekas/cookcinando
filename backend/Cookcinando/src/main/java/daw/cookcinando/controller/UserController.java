package daw.cookcinando.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import daw.cookcinando.model.User;
import daw.cookcinando.model.UserBasic;
import daw.cookcinando.model.UserEnterprise;
import daw.cookcinando.repository.UserRepository;
import daw.cookcinando.UserComponent;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping("/registro")
	public String registry(Model model, @RequestParam String nick, @RequestParam String email, 
						   @RequestParam String password, @RequestParam String tipoUsuario){
		if(tipoUsuario.equals("individual")){
			User user = new UserBasic("", "", "", "", nick,email,password,"ROLE_BASIC");
			userRepository.save(user);
			userComponent.setLoggedUser(user);
		}
		else if(tipoUsuario.equals("empresa")){
			User user = new UserEnterprise("", "", "", "", nick,email,password,"ROLE_BASIC","ROLE_ENTERPRISE");
			userRepository.save(user);
			userComponent.setLoggedUser(user);
		}
		return "login";
	}

	@RequestMapping("/privado/todos-usuarios")
	public String allUsers(Model model, HttpServletRequest request){
		
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		
		List<User> users = userRepository.findAll();
		List<UserBasic> users_basic = new ArrayList<UserBasic>();
		List<UserEnterprise> users_enterprise = new ArrayList<UserEnterprise>();
		
		for(User user: users){
			
			if(user instanceof UserBasic){
				users_basic.add((UserBasic) user);
			}
			else if(user instanceof UserEnterprise){
				users_enterprise.add((UserEnterprise) user);
			}
		}
		
		model.addAttribute("users_basic", users_basic);
		model.addAttribute("users_enterprise", users_enterprise);
		
		return "usuarios";
	}
}
