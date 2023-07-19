package personal_expenditure.entities;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "expenditure")
public class Expenditure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exp_id")
	private int eid;
	@Column(name = "amount")
	private double amount;
	@Column(name = "spent_on")
	private Date date;
	@Column(name = "exp_description")
	private String expdesc;
	@Column(name = "exp_remarks")
	private String expremarks;
	@Column(name = "tags")
	private String tags;
	@Column(name = "user_name")
	private String username;
	@Column(name = "category_code")
	private String catcode;
	@Column(name = "payment_code")
	private String paymentcode;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_name", referencedColumnName = "user_name", updatable = false, insertable = false)
	private User user;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "category_code", referencedColumnName = "category_code", updatable = false, insertable = false)
	private Category category;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "payment_code", referencedColumnName = "payment_code", updatable = false, insertable = false)
	private Paymentmode paymentmode;
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getExpdesc() {
		return expdesc;
	}
	public void setExpdesc(String expdesc) {
		this.expdesc = expdesc;
	}
	public String getExpremarks() {
		return expremarks;
	}
	public void setExpremarks(String expremarks) {
		this.expremarks = expremarks;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCatcode() {
		return catcode;
	}
	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}
	public String getPaymentcode() {
		return paymentcode;
	}
	public void setPaymentcode(String paymentcode) {
		this.paymentcode = paymentcode;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Paymentmode getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(Paymentmode paymentmode) {
		this.paymentmode = paymentmode;
	}
	
}