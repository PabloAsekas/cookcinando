package daw.cookcinando.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import daw.cookcinando.UserComponent;
import daw.cookcinando.model.User;

@Controller
public class LoginWebController {
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping("/")
    public String index(Model model) {
		User userLogged = userComponent.getLoggedUser();
		if (userLogged != null) {
			model.addAttribute("userlogged", true);
		} else {
			model.addAttribute("usernotlogged", true);
		}
        return "index";
    }
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
	@RequestMapping("/logout")
    public String logout() {
        userComponent.setLogout();
        System.out.println(userComponent.getLoggedUser().getEmail());
        return "index";
		
    }
	
	@RequestMapping("/login-error")
    public String loginError() {
        return "login-error";
    }
}
