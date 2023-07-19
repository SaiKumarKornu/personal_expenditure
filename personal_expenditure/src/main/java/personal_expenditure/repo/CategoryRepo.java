package personal_expenditure.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import personal_expenditure.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,String> {
	
}