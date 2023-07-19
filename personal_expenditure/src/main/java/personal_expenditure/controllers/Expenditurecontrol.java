package personal_expenditure.controllers;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import personal_expenditure.entities.Expenditure;
import personal_expenditure.repo.ExpenditureRepo;


@RestController
public class Expenditurecontrol {
	@Autowired
	ExpenditureRepo expendaturerepo;
	
	@GetMapping("/getexpenditurebyuser")
	@Operation(summary = "get the expenditure list by user", description = "the expenditure list by user")
	public List<Expenditure> getuserbylist() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var count = expendaturerepo.getExpeditureByUser(username);
		if (count.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "this user has no expenditures");
		} else {
			return count;
		}
	}

	
	@GetMapping("/getexpensepage/{catcode}")
	@Operation(summary = "get the expenditure list by page", description = "give the category code and get the expenditure list ")
	public List<Expenditure> getlistexpense(@PathVariable("catcode") String catcode) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var data = expendaturerepo.getExpenditureByUserAndCategorySortedById(username, catcode,
				PageRequest.of(0, 2, Sort.by("eid").descending()));
		if (data.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found expensitures ");
		}
		return data;
	}

	@GetMapping("/getexpenditurebypayment/{paymentname}")
	@Operation(summary = "get the expenditure list by page", description = "give the payment name and get the expenditure list ")
	public List<Expenditure> getmethod(@PathVariable("paymentname") String paymentname) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var data = expendaturerepo.getExpenditureBypaymentname(username, paymentname, PageRequest.of(0, 2, Sort.by("eid")));
		if (data.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"the given payment name for this user has no paymentmodes and no expenditures");
		}
		return data;
	}

	@GetMapping("/getexpenditurebydates/{mindate}/{maxdate}")
	@Operation(summary = "get the expenditure list by page between two dates", description = "give the min,max dates and date format is (yyyy-mm-dd) to get the expenditure list ")
	public List<Expenditure> getdates(@PathVariable("mindate") Date min, @PathVariable("maxdate") Date max) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var data = expendaturerepo.getExpenditureBetweenDates(username, min, max,
				PageRequest.of(0, 2, Sort.by("date").descending()));
		if (data.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "between these dates has no expenditures");
		} else {
			return data;
		}
	}

	@GetMapping("getexpenditurebytag/{tag}")
	@Operation(summary = "get the expenditure list by tags", description = "give the tag name and get the expenditure list ")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "invalid data passed"),
			@ApiResponse(responseCode = "200", description = "okay tag found"),
			@ApiResponse(responseCode = "404", description = "tag not found") })
	public List<Expenditure> gettag(@PathVariable("tag") String tags) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var data = expendaturerepo.getExpenditureByTag(tags, username);
		if (data.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "this user has no expenditures with this tags");
		} else {
			return data;
		}
	}

	@GetMapping("/gettotalexpensesinamonth/{month}")
	@Operation(summary = "get total expenditute in a month for each category", description = "give month in integers and get the total in that month for each category")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "invalid data passed"),
			@ApiResponse(responseCode = "200", description = "okay month found"),
			@ApiResponse(responseCode = "404", description = "month not found") })
	public List<String> getamount(@PathVariable("month") int month) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var data = expendaturerepo.getAmountInMonth(month, username);
		if (data.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "this month has no expenditures with this user");
		} else {
			return data;
		}
	}
	
	@DeleteMapping("delexpenditure/{id}")
	@Operation(summary = "delete  expenditure by expenditure id", description = "give the  expenditure id and delete the expenditure row")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "invalid data passed"),
			@ApiResponse(responseCode = "200", description = "okay id found deleted the row in database"),
			@ApiResponse(responseCode = "404", description = "id  not found") })
	public void delexp(@PathVariable("id") int id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var code = expendaturerepo.findById(id);
		if (code != null) {
			var data = code.get();
			if (data.getUsername().equals(username)) {
				expendaturerepo.deleteById(id);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username mismatch");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found");
		}
	}

	@PutMapping("updateexpenditure/{amount}/{id}")
	@Operation(summary = "update the expedniture", description = "takes amount and expenditure id and update the expediture in database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Updated expenditure successfully"),
			@ApiResponse(responseCode = "404", description = "id not found") })
	public void updexp(@PathVariable("amount") double amount, @PathVariable("id") int id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var code = expendaturerepo.findById(id);
		if (code.isPresent()) {
			var data = code.get();
			if (data.getUsername().equals(username)) {
				data.setAmount(amount);
				expendaturerepo.save(data);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username mismatch");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id not found");
		}
	}

	@PostMapping("/addnewexepnditure")
	@Operation(summary = "insert new expenditure")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "invalid data passed"),
			@ApiResponse(responseCode = "200", description = "inserted successfully"), })
	public Expenditure addnewexp(@RequestBody Expenditure exp) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var id = expendaturerepo.findById(exp.getEid());
		if (id.isPresent()) {
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "id is already presented");
		} else {
			if (exp.getUsername().equals(username)) {
				expendaturerepo.save(exp);
				return exp;
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username mismatch");
			}
		}
	}
	
	@GetMapping("/topfiveexpenses")
	@Operation(summary = "get top 5 expenses by user")
	public List<Expenditure> gettop() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var data = expendaturerepo.publicTopExp(username, PageRequest.of(0, 5, Sort.by("eid").descending()));
		if (data.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "for this user has no expenditures");
		} else {
			return data;
		}
	}
}