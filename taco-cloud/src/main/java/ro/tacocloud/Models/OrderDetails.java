package ro.tacocloud.Models;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORDER - reserved name for SQL, this is why the class is called OrderDetails

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long orderID;
  
  @NotBlank(message="Name is required")
  private String name;
  
  @NotBlank(message="Street is required")
  private String street;

  @NotBlank(message="City is required")
  private String city;


  @NotBlank(message="State is required")
  private String state;

  @NotBlank(message="Zip code is required")
  private String zip;

  @CreditCardNumber(message="Not a valid credit card number")
  private String ccNumber;

  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer=3, fraction=0, message="Invalid CVV")
  private String ccCVV;
  
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderID")
  @JsonIgnore
  private List<Taco> Tacos;
  
  private Date placedAt;
  
  @PrePersist
  void placedAt() {
	  this.placedAt = new Date();
  }
  
  //private User userID;
  
 
  
}