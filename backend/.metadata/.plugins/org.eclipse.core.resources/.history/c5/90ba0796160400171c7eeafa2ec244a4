package daw.cookcinando.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import daw.cookcinando.UserComponent;
import daw.cookcinando.model.Recipe;
import daw.cookcinando.model.User;
import daw.cookcinando.repository.RecipeRepository;

@Controller
public class WebController {

	@Autowired
	private RecipeRepository recipeRepository;
	private List<String> ingredients = new ArrayList<>();
	private List<String> typesFood = new ArrayList<>();
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping("/")
    public String index(Model model) {
		User userLogged = userComponent.getLoggedUser();
		//if (user2 != null) {
			//model.addAttribute("usuario", user2);
		//}
        return "index";
    }
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
	
	/*@RequestMapping("/crearreceta")
	public String receta(Model model, @RequestParam String titulo, @RequestParam String cuerpo, @RequestParam String ingredientes, @RequestParam String comidas) {
		List<String> ingredientesRecetas = new ArrayList<>();
		String ingredientesSeparados[] = ingredientes.split(",");
		for(int i=0; i<ingredientesSeparados.length; i++){
			ingredientesRecetas.add(ingredientesSeparados[i]);
		}
		List<String> comidasRecetas = new ArrayList<>();
		String comidasSeparadas[] = comidas.split(",");
		for(int i=0; i<comidasSeparadas.length; i++){
			comidasRecetas.add(comidasSeparadas[i]);
		}
		Recipe recipe = new Recipe(titulo, "", "", cuerpo, ingredientesRecetas, comidasRecetas,null);
		recipeRepository.save(recipe);
		model.addAttribute("receta", recipe);
		List<Recipe> recomendadas = new ArrayList<Recipe>();
		for (long i = 1; i < 4; i++) {
			recomendadas.add(recipeRepository.getOne(i));
		}
		model.addAttribute("recomendadas", recomendadas);
		return "receta";
    }*/
	
}
