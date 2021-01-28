package org.sid.projection;

import org.sid.dtos.Customer;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "c1", types = Customer.class)
public interface CustomerProjection 
{
	public Long getId();
	public String getName();
}
