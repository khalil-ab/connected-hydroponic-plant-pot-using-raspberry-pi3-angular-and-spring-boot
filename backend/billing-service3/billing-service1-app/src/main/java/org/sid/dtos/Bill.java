package org.sid.dtos;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document
public class Bill 
{
	@Id
	private String id;
	private Date billingDate;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String customerID;
	@Transient//ne pas enregistrer dans la base de donnée
	private Customer customer;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@DBRef
	private Collection<ProductItem> productItems;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public Collection<ProductItem> getProductItems() {
		return productItems;
	}
	public void setProductItems(Collection<ProductItem> productItems) {
		this.productItems = productItems;
	}
	public Bill(String id, Date billingDate, String cid, Collection<ProductItem> productItems) {
		super();
		this.id = id;
		this.billingDate = billingDate;
		this.customerID = cid;
		this.productItems = productItems;
	}
	public Bill() {
		super();
	}
	
	
	
}
