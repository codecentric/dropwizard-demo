package de.cc.dropwizard.resources;

import com.google.common.base.Optional;
import com.sun.jersey.api.NotFoundException;
import de.cc.dropwizard.dao.CustomerDAO;
import de.cc.dropwizard.pojo.Customer;
import de.cc.dropwizard.view.CustomerView;
import de.cc.dropwizard.view.ListCustomerView;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import io.dropwizard.views.View;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/views")
public class ViewResource {
	
	private final CustomerDAO customerDAO;
	
	public ViewResource(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}


	@GET
	@Path("/customer/{customerId}/view_mustache")
	@UnitOfWork
	@Produces(MediaType.TEXT_HTML)
	public View getCustomerViewMustache(
			@PathParam("customerId") LongParam customerId) {
		return new CustomerView(CustomerView.Template.MUSTACHE,
				findSafely(customerId.get()));
	}

	@GET
	@Path("/customer/view_mustache")
	@UnitOfWork
	@Produces(MediaType.TEXT_HTML)
	public View getCustomerView() {
		return new ListCustomerView(ListCustomerView.Template.MUSTACHE,
				findAllSafely());
	}

	private List<Customer> findAllSafely() {
		final List<Customer> customers = customerDAO.findAll();
		if(customers.isEmpty() || customers == null) {
			throw new NotFoundException("No users found.");
		}
		return customers;
	}

	private Customer findSafely(long customerId) {
		final Optional<Customer> customer = customerDAO.findById(customerId);
		if (!customer.isPresent()) {
			throw new NotFoundException("No such user.");
		}
		return customer.get();
	}

}
