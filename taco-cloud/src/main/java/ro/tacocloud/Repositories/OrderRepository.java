package ro.tacocloud.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tacocloud.Models.OrderDetails;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
	
	//Page<OrderDetails> findAllByUserID(@Param("userID") Optional<OrderDetails> order, Pageable pageable);
	
	//de facut
	//findByUserOrderByPlacedAtDesc(user));
	
}