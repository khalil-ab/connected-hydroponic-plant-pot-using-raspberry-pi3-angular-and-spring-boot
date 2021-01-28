package org.sid.dtos;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class ProductItem {

	@Id
	private String id;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//ne pas deserialisre c a d ne pas afficher sur le resultat Json
	private String productID;
	@Transient
	private Product product;
	private double price;
	private double quantity;
 	@DBRef
 	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//ne pas deserialiser l objet bill en format Json pour eviter la boucle infinie
 	private Bill bill;
 	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
		
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductItem(String id, String productID, double price, double quantity, Bill bill) {
		super();
		this.id = id;
		this.productID = productID;
		this.price = price;
		this.quantity = quantity;
		this.bill = bill;
	}
	public ProductItem() {
		super();
	}
	@Override
	public String toString() {
		return "ProductItem [id=" + id + ", productID=" + productID + ", price=" + price + ", quantity=" + quantity
				+ ", bill=" + bill + "]";
	}
	
	
	
}
