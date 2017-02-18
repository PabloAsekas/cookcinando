package daw.cookcinando;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class recetaController {
	
	private List<Receta> recetas = new ArrayList<>();
	
	private List<String> ingredientes = new ArrayList<>();
	private List<String> comidas = new ArrayList<>();
	
	public recetaController() {
		ingredientes.add("Ingrediente1");
		ingredientes.add("Ingrediente2");
		ingredientes.add("Ingrediente3");
		ingredientes.add("Ingrediente4");
		
		comidas.add("Comida1");
		comidas.add("Comida1");
		comidas.add("Comida1");
		comidas.add("Comida1");
		
		recetas.add(new Receta("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "<h3>Ingredientes</h3><p>Para las caraotas: 1/2 Kg. de caraotas negras, 2 cebollas medianas, 1 pimentón rojo, cebollín al gusto, ají dulce, piemienta dulce, una pisca de comino, un trozo de chuleta o costillas ahumadas.</p><p>Para la carne: 1/2 kg.de falda de res, 2 cebollas medianas, 2 pimentones (uno verde y uno rojo), ají dulce al gusto, 1/2 taza de leche de coco, 3 tomates frescos o en lata (al natural), pimienta dulce, sal y pimienta para adobar.</p><p>Arroz Blanco ya preparado.</p><p>Para las tajadas: 1 o 2 plátanos (no cambur) maduros.</p><h3>Preparación</h3><img src='../img/pabellon-criollo-1.jpg' /><p>Caraotas: en una olla colocar agua suficiente para cubrir las caraotas, cuando hierva, agregar las caraotas sal al gusto y la chuleta o costilla,y dejar cocinar por 1 hora o hasta que estén blandas las caraotas, si lo desea puede agregar un cubito de caldo al agua para dar sabor o sal. Mientras haga un sofrito con la cebolla, pimentones, ají, cebollín, agregele pimienta dulce, sal y la pizca de comino. 15 minútos antes de apagar las caraotas agréguele el sofito mezcle y tape hasta que el agua se halla consumido un poco.</p><p>Carne mechada: Cocine la carne en agua hirviendo, sazonada con sal, caldo en cubito, hierbas o a su gusto. Una vez cocinada y fría, prodeda a mecharla (con los dedos separe trozos de carne que parezcan hilachas, desechando pellegos y grasa) corte el resto de los ingredientes en julianas, reogue la cebolla y el pimentón, agregue el tomate, luego la carne sazone, por último agregue la leche del coco, cocine hasta que se haya formado una salsa.Debe quedar jugosa.</p><p>Tajadas: Rebane el platano haciendo cortes transversales, las tajadas deben ser de 1/2 cm. de espesor o un poquito menos. En aceite bien caliente fríalas hasta que estén doradas.</p><p>Una vez que todo esté listo sirva una taza de arroz blanco, una taza de caraotas, una porción de carne mechada y rodee con tres o cuatro tajadas (esto en el mismo plato, para una persona con buen apetito).</p><h3>Consejos</h3><p>Las caraotas pueden hacerse con un toque de dulce, al agregar el sofrito puede agregar un tocito de papelón o azucar morena, si lo prefiere, agregue el azucar una vez que se las haya servido en el plato.</p><p>La carne adquiere muy buen sabor si se le agregan 2 cucharadas de Ketchup, junto con el tomate al natural.</p><p>Si quiere ahorrarse unas calorías puede preparar el plátano al horno, pógalo en el horno entero a temperatura media hasta que se dore por encima, engrase un poco el molde para que no se pegue.</p>", ingredientes, comidas));
		recetas.add(new Receta("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredientes, comidas));
		recetas.add(new Receta("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredientes, comidas));
		recetas.add(new Receta("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredientes, comidas));
		recetas.add(new Receta("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredientes, comidas));
		recetas.add(new Receta("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredientes, comidas));
		recetas.add(new Receta("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredientes, comidas));
		recetas.add(new Receta("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredientes, comidas));
		recetas.add(new Receta("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredientes, comidas));
		recetas.add(new Receta("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredientes, comidas));
		recetas.add(new Receta("Filete con patatas", "Un filetito con patatas bien rico", "https://media-cdn.tripadvisor.com/media/photo-s/04/4a/66/98/la-casona.jpg", "Proceso de preparacion:", ingredientes, comidas));
		recetas.add(new Receta("Hamburguesa de pollo", "Una hamburguesa de pollo que no resbala", "http://cdn.kiwilimon.com/recetaimagen/13823/6183.jpg", "Proceso de preparacion:", ingredientes, comidas));
	}
	
	@RequestMapping("/recetas") //Enlace para las recetas
	public String recetas (Model model) {
		model.addAttribute("recetas", recetas);
		return "recetas";
	}
	
	@RequestMapping("/receta/{num}")
	public String verReceta(Model model, @PathVariable String num) {
		Receta receta = recetas.get(Integer.parseInt(num)-1);
		model.addAttribute("receta", receta);
		
		List<Receta> recomendadas = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			recomendadas.add(recetas.get(i));
		}
		model.addAttribute("recomendadas", recomendadas);
		return "receta";
	}
}
