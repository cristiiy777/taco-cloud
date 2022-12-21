package ro.tacocloud.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tacocloud.Models.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}