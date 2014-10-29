package de.cc.dropwizard.view;

import de.cc.dropwizard.pojo.Customer;
import io.dropwizard.views.View;

/**
 * Created by fema on 17.10.14.
 */
public class CreateCustomerView extends View {
	private final Customer customer;

	public CreateCustomerView(Template template, Customer customer) {
		super(template.getTemplateName());
		this.customer = customer;
	}

	public enum Template {
		MUSTACHE("mustache/createCustomer.mustache");

		private String templateName;

		private Template(String templateName) {
			this.templateName = templateName;
		}

		public String getTemplateName() {
			return templateName;
		}
	}

	public void createCustomer(Customer customer) {
		return;
	}
}
