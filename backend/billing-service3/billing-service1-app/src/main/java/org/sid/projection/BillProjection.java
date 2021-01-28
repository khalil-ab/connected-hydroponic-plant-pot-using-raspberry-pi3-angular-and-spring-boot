package org.sid.projection;

import java.util.Collection;
import java.util.Date;

import org.sid.dtos.Bill;
import org.sid.dtos.ProductItem;
import org.springframework.data.rest.core.config.Projection;
 
//http://localhost:8083/bills/1?projection=fullBill
@Projection(name = "fullBill", types = Bill.class)
public interface BillProjection 
{
	public String getId();
	public Date getBillingDate();
	public String getCustomerID();
	public Collection<ProductItem> getProductItems();
}