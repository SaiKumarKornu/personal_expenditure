package personal_expenditure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import personal_expenditure.repo.UserRepo;
@RestController
public class Usercontrol {
    @Autowired
     UserRepo ur;
    @GetMapping("/getuser")
    @Operation(summary="to get the user details")
    @ApiResponse(responseCode = "200",description = "okay you got the user details")
    public List<String> getuser()
    {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ur.getuserdetails(username);
    }
}
                              