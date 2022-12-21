package ro.tacocloud.Controllers;

import java.util.ArrayList;

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
import ro.tacocloud.Models.OrderDetails;
import ro.tacocloud.Models.Taco;
import ro.tacocloud.Services.OrderService;
import ro.tacocloud.Services.TacoService;

@Slf4j //simple logging facade
@Controller
@RequestMapping("/orders") //any path that starts with /orders, will be redirected to this controller class
public class OrderController {
	
	@Autowired
	public OrderService orderService;
	
	@Autowired
	public TacoService tacoService;
	
	@ModelAttribute(name = "order")
	public OrderDetails addOrderToModel(HttpSession session){
		//return new OrderDetails(); 
		//--- we wont create a new order. that would defeat the purpose of storing it in the session
		//instead we get it from the session and add it to the Model
		OrderDetails order  = (OrderDetails) session.getAttribute("order");
		return order;
	}

	@GetMapping("/current")
	public String orderForm(Model model, HttpSession session) {
		log.info("View: /orders/current ||order-form|| -GET");
		return "order-form";
	}
	
	@PostMapping
	public String processOrder(@Valid @ModelAttribute OrderDetails order, Errors errors, HttpSession session) {
		if(errors.hasErrors()) {
			log.warn("Order has errors: " + errors.toString());
			log.info("View: /design ||order-form|| -POST");
			return "order-form";
		}
		//saving order in database
		orderService.saveOrderDetails(order);
		//get the session attribute tacoList
		@SuppressWarnings("unchecked")
		ArrayList<Taco> tacoList = (ArrayList<Taco>) session.getAttribute("tacoList");
		//setting orderID for each taco in tacoList and saving them in the DB 
		for (int i=0; i < tacoList.size(); i++) {
			tacoList.get(i).setOrderID(order);
			tacoService.saveTaco(tacoList.get(i));
		}
		//removing the current Order and tacoList from the session after the final sucessful submit so that new orders can be submitted
		session.removeAttribute("order");
		session.removeAttribute("tacoList");
		return "redirect:/dashboard";
	}
}
