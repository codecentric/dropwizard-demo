package de.cc.dropwizard.dao;

import com.google.common.base.Optional;
import de.cc.dropwizard.pojo.Customer;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by fema on 19.09.14.
 */
public class CustomerDAO extends AbstractDAO<Customer> {
	public CustomerDAO(SessionFactory factory) {
		super(factory);
	}

	public Optional<Customer> findById(Long id) {
		return Optional.fromNullable(get(id));
	}

	public Customer create(Customer Customer) {
		return persist(Customer);
	}

	public List<Customer> findAll() {
		return list(namedQuery("de.cc.dropwizard.pojo.Customer.findAll"));
	}
}
