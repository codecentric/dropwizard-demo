package de.cc.dropwizard.resources;

import com.google.common.base.Optional;
import com.sun.jersey.api.NotFoundException;
import de.cc.dropwizard.dao.CustomerDAO;
import de.cc.dropwizard.pojo.Customer;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by fema on 19.09.14.
 */
@Path("/customeradmin")
public class CustomerResource {
	private final CustomerDAO customerDAO;

	public CustomerResource(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Path("/customer")
	@POST
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public Customer createCustomer(Customer customer) {
		return customerDAO.create(customer);
	}

	@Path("/customer")
	@GET
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> listCustomer() {
		return customerDAO.findAll();
	}

	@Path("/customer/{customerId}")
	@GET
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getPerson(@PathParam("customerId") LongParam customerId) {
		return findSafely(customerId.get());
	}

	private Customer findSafely(long customerId) {
		final Optional<Customer> customer = customerDAO.findById(customerId);
		if (!customer.isPresent()) {
			throw new NotFoundException("No such user.");
		}
		return customer.get();
	}

}
