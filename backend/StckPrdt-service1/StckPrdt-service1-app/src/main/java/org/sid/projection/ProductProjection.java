package org.sid.projection;


import org.sid.dtos.Product;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="p1", types = Product.class)
public interface ProductProjection
{
	public String getId();
	public String getName();	
}
