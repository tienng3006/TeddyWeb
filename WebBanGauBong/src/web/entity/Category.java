package web.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="Category")
public class Category {
@Id
@Column(name="Id")
@NotBlank(message="Không được trống id!")
private String id;
@NotBlank(message="Không được trống tên!")
@Column(name="Name")
private String name;

@OneToMany(mappedBy="category",fetch=FetchType.EAGER)
private Collection<Product> products;
public Collection<Product> getProducts() {
	return products;
}
public void setProducts(Collection<Product> products) {
	this.products = products;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
