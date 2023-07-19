package personal_expenditure.entities;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "paymentmode")
public class Paymentmode {
	@Id
	@Column(name = "payment_code")
	private int paymentcode;
	@Column(name = "payment_name")
	private String paymentname;
	@Column(name = "remarks")
	private String payremarks;
	@Column(name="user_name")
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_name",insertable = false,updatable = false)
	private User paymodeuser;
	@JsonIgnore
	@OneToMany(mappedBy = "paymentmode")
	private List<Expenditure> exppaymode;
	
	
	public int getPaymentcode() {
		return paymentcode;
	}
	public void setPaymentcode(int paymentcode) {
		this.paymentcode = paymentcode;
	}
	public String getPaymentname() {
		return paymentname;
	}
	public void setPaymentname(String paymentname) {
		this.paymentname = paymentname;
	}
	public String getPayremarks() {
		return payremarks;
	}
	public void setPayremarks(String payremarks) {
		this.payremarks = payremarks;
	}
	public User getPaymodeuser() {
		return paymodeuser;
	}
	public void setPaymodeuser(User paymodeuser) {
		this.paymodeuser = paymodeuser;
	}
	public List<Expenditure> getExppaymode() {
		return exppaymode;
	}
	public void setExppaymode(List<Expenditure> exppaymode) {
		this.exppaymode = exppaymode;
	}
	
}