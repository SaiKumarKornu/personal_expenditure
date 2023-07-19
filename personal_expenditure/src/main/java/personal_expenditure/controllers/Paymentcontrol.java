package personal_expenditure.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import personal_expenditure.entities.Paymentmode;
import personal_expenditure.repo.PaymentmodeRepo;
@RestController
public class Paymentcontrol {
	@Autowired
	PaymentmodeRepo pr;
	@PostMapping("/addnewpaymentmode")
	@Operation(summary = "insert new payment mode")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "invalid data passed"),
			@ApiResponse(responseCode = "200", description = "inserted new paymentmode"), })
	public Paymentmode addNewPaymentmode(@RequestBody Paymentmode pm) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var paycode = pr.findById(pm.getPaymentcode());
		if (paycode.isPresent()) {
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "already code is present");
		} else {
			if (pm.getUsername().equals(username)) {
				pr.save(pm);
				return pm;
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username mismatch");
			}
		}
	}
	@DeleteMapping("/delpaymentmode/{code}")
	@Operation(summary = "Delete payment mode by payment code", description = "takes paymentcode and deletes that data in database")
	@ApiResponses(value = { @ApiResponse(responseCode = "400", description = "invalid data passed"),
			@ApiResponse(responseCode = "200", description = "okay code found deleted the row in database"),
			 })
	public void delpay(@PathVariable("code") int code) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var id = pr.findById(code);
		if (id != null) {
			var data = id.get();
			if (data.getUsername().equals(username)) {
				pr.delete(data);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username mismatch");
			}
		}
		
	}
	@PutMapping("/updatepaymentmode/{code}/{name}")
	@Operation(summary = "update the paymentmode", description = "takes paymentcode and paymentname and update the paymentmode in database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update paymentmode  successfully"),
			@ApiResponse(responseCode = "404", description = "payment code not found") })
	public void updatepaymentmode(@PathVariable("code") int code, @PathVariable("name") String name)
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var id = pr.findById(code);
		if (id.isPresent()) {
			var data = id.get();
			if (data.getUsername().equals(username)) {
				data.setPaymentname(name);
				pr.save(data);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username mismatch");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "payment code not found");
		}
	}
	@GetMapping("/getlistofpaymentmodes")
	@Operation(summary = "get the list of all payment modes", description = "the payment mode list by user")
	public List<String> getlistpay() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var data = pr.getlistpay(username);
		if (data.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "for this user has no payment modes");
		} else {
			return data;
		}
	}
}
