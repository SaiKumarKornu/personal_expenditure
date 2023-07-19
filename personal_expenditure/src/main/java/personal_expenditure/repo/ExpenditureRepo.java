package personal_expenditure.repo;
import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import personal_expenditure.entities.Expenditure;
public interface ExpenditureRepo extends JpaRepository<Expenditure, Integer> {
	@Query("select e from Expenditure e where e.username=:username")
	List<Expenditure> getExpeditureByUser(@Param("username") String username);
	

	
	@Query("SELECT e FROM Expenditure e WHERE e.username = :username AND e.catcode = :catcode")
	List<Expenditure> getExpenditureByUserAndCategorySortedById(@Param("username") String username,
			@Param("catcode") String catcode, PageRequest pageable);
	
	
	@Query("SELECT e from Expenditure e JOIN e.paymentmode p  where e.username=:username and p.paymentname=:paymentname")
	List<Expenditure> getExpenditureBypaymentname(@Param("username") String username,
			@Param("paymentname") String paymentname, PageRequest page);
	
	
	@Query("SELECT e from  Expenditure e where e.username=:username AND e.date BETWEEN :min and :max")
	List<Expenditure> getExpenditureBetweenDates(@Param("username") String username, @Param("min") Date min,
			@Param("max") Date max, PageRequest pageable);
	
	
	@Query("select e from Expenditure e where tags like %:tag% and e.username=:username")
	List<Expenditure> getExpenditureByTag(@Param("tag") String tag,@Param("username")String username);
	
	
	@Query("SELECT SUM(e.amount) AS totalAmount, e.catcode FROM Expenditure e WHERE MONTH(e.date) = :month and e.username=:username GROUP BY e.catcode")
	List<String> getAmountInMonth(@Param("month") int month,@Param("username")String username);
	@Query("select e from Expenditure e where e.username=:username")
	List<Expenditure> publicTopExp(@Param("username") String username, PageRequest pageable);
}