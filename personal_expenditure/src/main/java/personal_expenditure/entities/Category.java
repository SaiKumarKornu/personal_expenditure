package personal_expenditure.entities;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "category")
public class Category {
	@Id
	@Column(name = "category_code")
	private String catcode;
	@Column(name = "category_name")
	private String catname;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<Expenditure> categoryexp;
	public String getCatcode() {
		return catcode;
	}
	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
	}
	public List<Expenditure> getCategoryexp() {
		return categoryexp;
	}
	public void setCategoryexp(List<Expenditure> categoryexp) {
		this.categoryexp = categoryexp;
	}
	
	
}