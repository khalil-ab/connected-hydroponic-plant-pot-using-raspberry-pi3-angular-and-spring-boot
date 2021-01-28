package org.sid.controler;

import org.sid.dao.BillRepository;
import org.sid.dao.ProductItemRepository;
import org.sid.dtos.Bill;
import org.sid.service.CustomerJointService;
import org.sid.service.ProductJointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
	@Autowired
	private BillRepository billRepository;
	@Autowired
	private ProductItemRepository productItemRepository;
	@Autowired
	private CustomerJointService customerServiceClient;
	@Autowired
	private ProductJointService inventoryServiceClient;

	//cette fct retourne la facture apres avoir donnée l id de la facture comme parametre
	@GetMapping("/fullBill/{id}")
	Bill getBill(@PathVariable(name = "id") String id) 
	{
		Bill bill = billRepository.findById(id).get();
		bill.setCustomer(customerServiceClient.find_it_by_id(bill.getCustomerID()));
		bill.setProductItems(productItemRepository.findByBillId(id));
		bill.getProductItems().forEach(pi -> {
			pi.setProduct(inventoryServiceClient.find_it_by_id(pi.getProductID()));
		});
		return bill;
	}
}