package personal_expenditure.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import personal_expenditure.entities.Category;
import personal_expenditure.repo.CategoryRepo;
@RestController
public class Categorycontrol {
	@Autowired
	CategoryRepo cr;
	
	
	@GetMapping("/getcategories")
	@Operation(summary = "get the categories list")
	public List<Category>getcat()
	{
		var data= cr.findAll();
		if(data.isEmpty())
		{
			throw new  ResponseStatusException(HttpStatus.NOT_FOUND,"category not found");
		}
		return data;
	}
}