package daw.cookcinando.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping("/")
    public String index() {
        return "index";
    }
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
	@RequestMapping("/nuevareceta")
    public String nuevaReceta(Model model, HttpServletRequest request) {
		
		model.addAttribute("enterprise",request.isUserInRole("ENTERPRISE"));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
        return "crearReceta";
    }
}