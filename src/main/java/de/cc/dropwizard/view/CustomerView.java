package de.cc.dropwizard.view;

import de.cc.dropwizard.pojo.Customer;
import io.dropwizard.views.View;

public class CustomerView extends View {
	private final Customer customer;

	public enum Template {
		MUSTACHE("mustache/customer.mustache");

		private String templateName;

		private Template(String templateName) {
			this.templateName = templateName;
		}

		public String getTemplateName() {
			return templateName;
		}
	}

	public CustomerView(CustomerView.Template template, Customer customer) {
		super(template.getTemplateName());
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}
}
