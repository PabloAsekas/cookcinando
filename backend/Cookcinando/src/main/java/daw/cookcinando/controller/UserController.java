package daw.cookcinando.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import daw.cookcinando.model.User;
import daw.cookcinando.model.UserBasic;
import daw.cookcinando.model.UserEnterprise;
import daw.cookcinando.repository.UserRepository;
import daw.cookcinando.UserComponent;

@Controller
public class UserController {
	
	private static final String FILES_FOLDER = "target/classes/static/img";

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
	
	@RequestMapping("/privado/ajustes")
	public String ajustes(Model model, HttpServletRequest request) {
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("user", userComponent.getLoggedUser());
		return("ajustes");
	}
	
	@RequestMapping("/privado/ajustar-cuenta/{id}")
	public String ajustProfile(Model model, @PathVariable Long id, @RequestParam String nick, @RequestParam String email, @RequestParam String pass1, @RequestParam String pass2) {
		User userLogged = userComponent.getLoggedUser();
		if ( ((pass1!="") && (pass2!="")) && pass1==pass2) {
			userLogged.setNick("funsiona el cambio wey");
			userLogged.setEmail(email);
			userRepository.save(userLogged);
		
		} else {
			userLogged.setNick(nick);
			userLogged.setEmail(email);
			userRepository.save(userLogged);
		}
		return ("redirect:/privado/ajustes");
	}
	
	@RequestMapping("/privado/mi-cuenta")
	public String myprofile(Model model, HttpServletRequest request) {
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("user", userComponent.getLoggedUser());
		return("miCuenta");
	}
	
	@RequestMapping("/privado/modificar-cuenta/{id}")
	public String editProfile(Model model, @PathVariable Long id, @RequestParam MultipartFile imagen, @RequestParam String name, @RequestParam String surname, @RequestParam String description) {
		User userLogged = userComponent.getLoggedUser();
		if (!imagen.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}
				
				String fileName = "imgUser" + id + ".jpg";
				String userImg = "../img/" + fileName;
				
				userLogged.setName(name);
				userLogged.setSurname(surname);
				userLogged.setDescription(description);
				userLogged.setImage(userImg);
				userRepository.save(userLogged);
				
				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				imagen.transferTo(uploadedFile);
				return ("redirect:/privado/mi-cuenta");
			} catch (Exception e) {
				model.addAttribute("error",e.getClass().getName() + ":" + e.getMessage());
			}
		} else {
			userLogged.setName(name);
			userLogged.setSurname(surname);
			userLogged.setDescription(description);
			userRepository.save(userLogged);
			return ("redirect:/privado/mi-cuenta");
		}
		return ("redirect:/privado/mi-cuenta");
	}

	@RequestMapping("/privado/mis-favoritos")
	public String myfavourites(Model model, HttpServletRequest request) {
		
		User user = userRepository.findOne(userComponent.getLoggedUser().getId());
		
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("recipes", user.getFavRecipes());
		
		return("misFavoritos");
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
