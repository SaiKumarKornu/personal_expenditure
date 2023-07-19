package personal_expenditure.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "user_name")
	private String username;
	@Column(name = "user_password")
	private String userpassword;
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Expenditure> userexp;
	@JsonIgnore
	@OneToMany(mappedBy = "paymodeuser")
	private List<Paymentmode> userpaymode;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public List<Expenditure> getUserexp() {
		return userexp;
	}

	public void setUserexp(List<Expenditure> userexp) {
		this.userexp = userexp;
	}

	public List<Paymentmode> getUserpaymode() {
		return userpaymode;
	}

	public void setUserpaymode(List<Paymentmode> userpaymode) {
		this.userpaymode = userpaymode;
	}
}