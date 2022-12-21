package ro.tacocloud.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ro.tacocloud.Models.Ingredient;
import ro.tacocloud.Repositories.IngredientRepository;


@Service
public class IngredientService {
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	//select all ingredients from DB with findAll and puts in a list
	public List<Ingredient> getAllIngredients(){
		List<Ingredient> ingrediente = new ArrayList<Ingredient>();
		ingredientRepository.findAll()
		.forEach(ingrediente::add);
		return ingrediente;
	}
	//select an ingredient based on an ID
	public Optional<Ingredient> getIngredient(long id){
		return ingredientRepository.findById(id);
	}
	//add ingredient
	public void saveIngredient(Ingredient ingredient) {
		ingredientRepository.save(ingredient);
	}
	//update ingredient
	public void updateIngredient(Ingredient ingredient) {
		ingredientRepository.save(ingredient);
	}
	//delete ingredient
	public void deleteIngredient(long id) {
		ingredientRepository.deleteById(id);
	}
	
	public Page<Ingredient> findPaginatedIngredient(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.ingredientRepository.findAll(pageable);
	}
	
}	
	