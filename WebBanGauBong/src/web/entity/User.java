package web.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Integer id;
	@Column(name = "Name")
	@NotBlank(message="Không được để trống tên!")
	private String name;
	
	@Column(name = "Gender")
	private Boolean gender;
	
	@Column(name = "Username")
	@NotNull(message="Không được trống Username!")
	private String username;
	
	
	@Column(name = "Password")
	@NotBlank(message="Không được trống Password!")
	@NotNull(message="Không được trống Password!")
	private String password;

	
	@Column(name = "Email")
	@Email(message="Sai định dạng email!")
	private String email;
	
	@Pattern(regexp="[0-9]{10,11}", message="Sai định dạng số điện thoại!")
	@Column(name = "Phone")
	private String phone;
	
	
	@Column(name = "Address")
	@NotBlank(message="Không được để trống địa chỉ!")
	private String address;
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	private Collection<Order> orders;
	public Collection<Order> getOrders() {
		return orders;
	}
	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Column(name = "Created")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date created;
	@Column(name = "UserRole")
	private String userRole;

}
