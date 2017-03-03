package daw.cookcinando;

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

@Controller
public class RecipeController {
	
	@Autowired
	private RecipeRepository recipeRepository;
	private List<String> ingredients = new ArrayList<>();
	private List<String> typesFood = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		
		ingredients.add("Ingrediente1");
		ingredients.add("Ingrediente2");
		ingredients.add("Ingrediente3");
		ingredients.add("Ingrediente4");
		
		typesFood.add("Comida1");
		typesFood.add("Comida2");
		typesFood.add("Comida3");
		typesFood.add("Comida4");
		
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", 				
										 "<h3>Ingredientes</h3>"
										 + "<ul><li>Para las caraotas: 1/2 Kg. de caraotas negras, 2 cebollas medianas, 1 pimentón rojo, cebollín al gusto, ají dulce, piemienta dulce, una pisca de comino, un trozo de chuleta o costillas ahumadas.</li>"
										 + "<li>Para la carne: 1/2 kg.de falda de res, 2 cebollas medianas, 2 pimentones (uno verde y uno rojo), ají dulce al gusto, 1/2 taza de leche de coco, 3 tomates frescos o en lata (al natural), pimienta dulce, sal y pimienta para adobar. Arroz Blanco ya preparado.</li>"
										 + "<li>Para las tajadas: 1 o 2 plátanos maduros.</li></ul>"
										 + "<h3>Preparación:</h3>"
										 + "<ul><li>Caraotas: en una olla colocar agua suficiente para cubrir las caraotas, cuando hierva, agregar las caraotas sal al gusto y la chuleta o costilla,y dejar cocinar por 1 hora o hasta que estén blandas las caraotas, si lo desea puede agregar un cubito de caldo al agua para dar sabor o sal. Mientras haga un sofrito con la cebolla, pimentones, ají, cebollín, agregele pimienta dulce, sal y la pizca de comino. 15 minútos antes de apagar las caraotas agréguele el sofito mezcle y tape hasta que el agua se halla consumido un poco.</li>"
										 + "<li>Carne mechada: Cocine la carne en agua hirviendo, sazonada con sal, caldo en cubito, hierbas o a su gusto. Una vez cocinada y fría, prodeda a mecharla (con los dedos separe trozos de carne que parezcan hilachas, desechando pellegos y grasa) corte el resto de los ingredientes en julianas, reogue la cebolla y el pimentón, agregue el tomate, luego la carne sazone, por último agregue la leche del coco, cocine hasta que se haya formado una salsa.Debe quedar jugosa.</li>"
										 + "<li>Tajadas: Rebane el platano haciendo cortes transversales, las tajadas deben ser de 1/2 cm. de espesor o un poquito menos. En aceite bien caliente fríalas hasta que estén doradas.</li></ul>"
										 + "<p>Una vez que todo esté listo sirva una taza de arroz blanco, una taza de caraotas, una porción de carne mechada y rodee con tres o cuatro tajadas (esto en el mismo plato, para una persona con buen apetito).</p>"
										 + "<h3>Consejos:</h3>"
										 + "<p>Las caraotas pueden hacerse con un toque de dulce, al agregar el sofrito puede agregar un tocito de papelón o azucar morena, si lo prefiere, agregue el azucar una vez que se las haya servido en el plato.</p>"
										 + "<p>La carne adquiere muy buen sabor si se le agregan 2 cucharadas de Ketchup, junto con el tomate al natural.</p>"
										 + "<p>Si quiere ahorrarse unas calorías puede preparar el plátano al horno, pógalo en el horno entero a temperatura media hasta que se dore por encima, engrase un poco el molde para que no se pegue.</p>"
										 , ingredients, typesFood));		
		
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", 
							  "<h3>Ingredientes: </h3>"
				              + "<h3>Preparación: </h3>"
							  + "<h3>Consejos:</h3>"	  
				              , ingredients, typesFood));
		
		
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", 
							 "<h3>Ingredientes: </h3>"
					          + "<h3>Preparación: </h3>"
							  + "<h3>Consejos:</h3>"	  
					          , ingredients, typesFood));
		
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood));
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredients, typesFood));
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood));
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredients, typesFood));
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood));
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredients, typesFood));
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood));
		recipeRepository.save(new Recipe("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredients, typesFood));
		recipeRepository.save(new Recipe("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredients, typesFood));	
	}
	
	@RequestMapping("/recetas") //Enlace para las recetas
	public String recetas (Model model) {
		model.addAttribute("recetas", recipeRepository.findAll());
		return "recetas";
	}
	
	@RequestMapping("/receta/{id}")
	public String verReceta(Model model, @PathVariable long id) {
		Recipe receta = recipeRepository.findOne(id);
		model.addAttribute("receta", receta);
		
		List<Recipe> recomendadas = new ArrayList<Recipe>();
		for (long i = 1; i < 4; i++) {
			recomendadas.add(recipeRepository.getOne(i));
		}
		model.addAttribute("recomendadas", recomendadas);
		return "receta";
	}
	
	@RequestMapping("/privado/nuevareceta")
	public String nuevaReceta(Model model) {
		return "crearReceta";
	}
	
	@RequestMapping("/privado/crearreceta")
	public String crearreceta(Model model, @RequestParam String titulo, @RequestParam String cuerpo, @RequestParam String ingredientes, @RequestParam String comidas) {
		List<String> ingredientesRecetas = new ArrayList<>(Arrays.asList(ingredientes.split(" , ")));//no funciona el separar por comas
		List<String> comidasRecetas = new ArrayList<>(Arrays.asList(comidas.split(" , ")));
		Recipe recipe = new Recipe(titulo, "", "", cuerpo, ingredientesRecetas, comidasRecetas);
		model.addAttribute("receta", recipe);
		return "editarReceta";
	}
}