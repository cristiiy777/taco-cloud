package ro.tacocloud.Controllers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import ro.tacocloud.Models.Ingredient;
import ro.tacocloud.Models.Taco;
import ro.tacocloud.Services.IngredientService;
import ro.tacocloud.Services.TacoService;

@Slf4j //simple logging facade
@Controller
@RequestMapping("/design")     
public class DesignTacoController {

	@Autowired
	public TacoService tacoService;
	
	@Autowired
	public IngredientService ingredientService;
	
	@ModelAttribute
	public void addIngredientsToModel(Model model){
		
		//list of ingredients taken from database using the service
		List<Ingredient> ingredients =  ingredientService.getAllIngredients();
		
		List<String> types = Arrays.asList("WRAP", "PROTEIN", "VEGGIES", "CHEESE", "SAUCE");
		for (String type : types) {
		  model.addAttribute(type.toString().toLowerCase(),
		      filterByType(ingredients, type));
		}
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, String type) {
		    return ingredients.stream()
		              .filter(x -> x.getType().equals(type))
		              .collect(Collectors.toList());
		  }
	
	@ModelAttribute(name = "taco")
	public Taco addTacoToModel() {
		return new Taco(); 
	}
	
	
	@GetMapping
	public String showDesignForm(Model model, HttpSession session) {
		log.info("View: /design || design || -GET");
		return "design";
	}
	

	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors, HttpSession session) {
		if(errors.hasErrors()) {
			log.warn("Taco design has errors: " + errors.toString());
			return "design"; 
			//Model of the Ingredients won't be empty and thus not populating the checkboxes with the ingredients when we return the design again,  
			//it wont be wmpty because we are setting it up using @ModelAttribute
		}
		
		//Saving the Taco inside de Session attribute tacoList
		//the tacoList will be used to temporally to store the tacos - this is because we can have multiple tacos on an Order  
		//the order is saved, its ID is generated, and all tacos inside tacoList will be setted with the current orderID
		//then we traverse the tacoList and save each taco using the Taco Service save()
		//if we dont firstly create the Order then set the orderID for each Taco, the orderID for the tacos will be empty 
		//and the order and the tacos will not be bound in the database
		
		//The Taco Ingredients
		//the Taco has a List of Ingredients. Each checkbox will be bound to the list field using the th:field attribute,
		//and each element (ingredientID, set using the th:value attribute) will be stored if it is selected.
		//<input name="ingredients" type="checkbox" th:value="${ingredient.ingredientID}" th:field="${taco.ingredients}"/>
		
		log.info("Processing taco: " + taco);
		
		//get the session attribute tacoList
		@SuppressWarnings("unchecked")
		ArrayList<Taco> tacoList = (ArrayList<Taco>) session.getAttribute("tacoList");
		
		//Save the created taco inside the tacos list (session attribute tacoList), but not in the DB
		tacoList.add(taco);
		
		log.info("View: /design || design || -POST");
		//The newly created Order that is now containing a Taco is going to be part of the Model for the order form View
		
		return "redirect:/orders/current"; 	}
}
