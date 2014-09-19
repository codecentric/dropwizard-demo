package de.cc.dropwizard.view;

import io.dropwizard.views.View;
import java.util.List;
import de.cc.dropwizard.pojo.Customer;

public class ListCustomerView extends View{
	private final List<Customer> customers;

	public enum Template {
		MUSTACHE("mustache/listCustomer.mustache");

		private String templateName;

		private Template(String templateName) {
			this.templateName = templateName;
		}

		public String getTemplateName() {
			return templateName;
		}
	}


	public ListCustomerView(ListCustomerView.Template template, List<Customer> customers) {
		super(template.getTemplateName());
		this.customers = customers;
	}

	public List<Customer> getCustomers() {
		return customers;
	}
}
