package ro.tacocloud.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tacocloud.Models.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {

}
