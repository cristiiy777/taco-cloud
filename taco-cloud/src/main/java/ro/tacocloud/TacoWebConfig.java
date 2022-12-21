package ro.tacocloud;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import lombok.extern.slf4j.Slf4j;
import ro.tacocloud.Models.OrderDetails;
import ro.tacocloud.Models.Taco;

//web configuration class

@Configuration
@Controller
@Slf4j //simple logging facade
public class TacoWebConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("login");
	}
	
	//The session should start when the user logs in. After he logs in he will be redirected do /dashboard
	//Then new attributes (empty) will be created and added to the session
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session) {
		
		OrderDetails order = new OrderDetails();
		session.setAttribute("order", order);
		
		List<Taco> tacoList = new ArrayList<Taco>();
		session.setAttribute("tacoList", tacoList);
		
		log.info("View: /dasshboard -GET || SESSION - ADDES NEWLY CREATEd ORDER AND TACOLIST OBJECTS ");
		
		return "dashboard";
	}
}

