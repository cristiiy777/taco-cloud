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
import ro.tacocloud.Models.OrderDetails;
import ro.tacocloud.Repositories.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	//select all orders from DB with findAll and puts in a list
	public List<OrderDetails> getAllOrderDetailss(){
		List<OrderDetails> order = new ArrayList<OrderDetails>();
		orderRepository.findAll()
		.forEach(order::add);
		return order;
	}
	//select an order based on an ID
	public Optional<OrderDetails> getOrderDetails(long id){
		return orderRepository.findById(id);
	}
	//add order
	public void saveOrderDetails(OrderDetails order) {
		orderRepository.save(order);
	}
	//update order
	public void updateOrderDetails(OrderDetails order) {
		orderRepository.save(order);
	}
	//delete order
	public void deleteOrderDetails(long id) {
		orderRepository.deleteById(id);
	}

	public Page<OrderDetails> findPaginatedOrderDetails(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.orderRepository.findAll(pageable);
	}
	
}	
	