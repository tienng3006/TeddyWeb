package web.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Products")
public class Product {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Integer id;
	@SafeHtml(message="Không được chèn thẻ HTML")
	@NotNull(message="Không được trống tên!")
	@Column(name = "Name")
	private String name;
	//@SafeHtml(message="Không được chèn thẻ HTML")
	//@NotNull(message="Không được trống mục Price !")
	//@Min(value=1 ,message="Giá thành sai!")
	@Column(name = "Price")
	private Float price;
	@Column(name = "Photo")
	private String photo;
	@SafeHtml(message="Không được chèn thẻ HTML")
	@NotBlank(message="Không được trống phần miêu tả!")
	@Column(name = "Description")
	private String des;
	
	@Column(name = "Discount")
	//@DecimalMin(value="0.0", message="Sai định dạng số Discount")
	//@DecimalMax(value="1.0", message="Sai định dạng số Discount")
	private Float discount;
	
	@NotNull(message="Không được trống số lượng!")
	@Min(value=1, message="Nhập sai số lượng!")
	@Column(name = "Quantity")
	private Integer quantity;
	@Column(name = "Created")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date created;

	@ManyToOne
	@JoinColumn(name = "CateId")
	private Category category;
	@OneToMany(mappedBy="product",fetch=FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;
	
	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
