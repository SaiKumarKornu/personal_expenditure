package personal_expenditure.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import personal_expenditure.entities.User;
public interface UserRepo extends JpaRepository<User,String>{
	
	@Query("select u.username from User u where u.username=:name")
	List<String> getuserdetails(@Param("name")String name);
	
}