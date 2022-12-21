package ro.tacocloud.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
public class Taco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tacoID;
	
	private Date createdAt;
	
	@NotNull
	@Size(min = 5, message="The name of the Taco needs to be atleast 5 characters long")
	private String name;
	
	//This List "Ingredients" will create a new table (columns taco and ingredients-after the list's name). the tacoID will be linked with the ingredientID  
	@NotNull
	@Size(min = 1, message="You must choose at least 1 ingredient")
	@ManyToMany(targetEntity=Ingredient.class) 
	@JsonIgnore
	List<Ingredient> Ingredients = new ArrayList<Ingredient>();;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderID")
	private OrderDetails orderID;

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}
	
}

