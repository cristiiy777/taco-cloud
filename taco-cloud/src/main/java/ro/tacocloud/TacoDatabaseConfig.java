package ro.tacocloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import ro.tacocloud.Models.Ingredient;
import ro.tacocloud.Services.IngredientService;

//class to set up the initial information in the database
//they get set up when we access (get mapping) /start

@Controller
@Slf4j //simple logging facade
@RequestMapping("/start")
public class TacoDatabaseConfig {

	@Autowired
	public IngredientService ingredientService;
	
	@GetMapping
	public String storeIngredientsToDatabase() {	
		 ingredientService.saveIngredient( new Ingredient("Flour Tortilla",  "WRAP"));  
		 ingredientService.saveIngredient( new Ingredient("Corn Tortilla", 	"WRAP"));
		 ingredientService.saveIngredient( new Ingredient("Ground Beef", 	"PROTEIN"));
		 ingredientService.saveIngredient( new Ingredient("Carnitas", 		"PROTEIN"));
		 ingredientService.saveIngredient( new Ingredient("Diced Tomatoes", 	"VEGGIES"));
		 ingredientService.saveIngredient( new Ingredient("Lettuce", 		"VEGGIES"));
		 ingredientService.saveIngredient( new Ingredient("Cheddar", 		"CHEESE"));
		 ingredientService.saveIngredient( new Ingredient("Monterrey Jack", 	"CHEESE"));
		 ingredientService.saveIngredient( new Ingredient("Salsa", 			"SAUCE"));
		 ingredientService.saveIngredient( new Ingredient("Chicken breast",  "PROTEIN"));
		 ingredientService.saveIngredient( new Ingredient("Sour Cream", 		"SAUCE")); 	  
		 log.info("Ingredients saved.");
		 return "redirect:/";
	}
	
}
