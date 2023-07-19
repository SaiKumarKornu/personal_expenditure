package personal_expenditure.repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import personal_expenditure.entities.Paymentmode;
public interface PaymentmodeRepo extends JpaRepository<Paymentmode,Integer> {
	@Query("SELECT p.paymentname FROM Paymentmode p WHERE p.username = :username")
    List<String> getlistpay(@Param("username") String username);
	
}