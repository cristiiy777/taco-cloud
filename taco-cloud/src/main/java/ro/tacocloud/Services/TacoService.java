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
import ro.tacocloud.Models.Taco;
import ro.tacocloud.Repositories.TacoRepository;


@Service
public class TacoService {
	
	@Autowired
	private TacoRepository tacoRepository;
	
	//select all tacos from DB with findAll and puts in a list
	public List<Taco> getAllTacos(){
		List<Taco> taco = new ArrayList<Taco>();
		tacoRepository.findAll()
		.forEach(taco::add);
		return taco;
	}
	//select an taco based on an ID
	public Optional<Taco> getTaco(long id){
		return tacoRepository.findById(id);
	}
	//add taco
	public void saveTaco(Taco taco) {
		tacoRepository.save(taco);
	}
	//update taco
	public void updateTaco(Taco taco) {
		tacoRepository.save(taco);
	}
	//delete taco
	public void deleteTaco(long id) {
		tacoRepository.deleteById(id);
	}
	
	public Page<Taco> findPaginatedTaco(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.tacoRepository.findAll(pageable);
	}
	
}	
	