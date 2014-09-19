package de.cc.dropwizard;

import de.cc.dropwizard.dao.CustomerDAO;
import de.cc.dropwizard.pojo.Customer;
import de.cc.dropwizard.resources.CustomerResource;
import de.cc.dropwizard.resources.ViewResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

/**
 * Hello world!
 *
 */
public class CustomerAdminApplication extends Application<CustomerAdminConfiguration>
{
    public static void main( String[] args ) throws Exception {
	    new CustomerAdminApplication().run(args);
    }

	private final HibernateBundle<CustomerAdminConfiguration> hibernateBundle = new HibernateBundle<CustomerAdminConfiguration>(
			Customer.class) {
		@Override
		public DataSourceFactory getDataSourceFactory(
				CustomerAdminConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};

	@Override
	public void initialize(Bootstrap<CustomerAdminConfiguration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
		bootstrap.addBundle(new MigrationsBundle<CustomerAdminConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(
					CustomerAdminConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
		bootstrap.addBundle(hibernateBundle);
		bootstrap.addBundle(new ViewBundle());
	}

	@Override
	public void run(CustomerAdminConfiguration customerAdminConfiguration, Environment environment) throws Exception {
		final CustomerDAO customerDAO = new CustomerDAO(
				hibernateBundle.getSessionFactory());
		environment.jersey().setUrlPattern("/service/*");
//		environment.healthChecks().register("template",
//				new TemplateHealthCheck(template));
//		environment.healthChecks().register("database",
//				new DatabaseHealthCheck(configuration.getDataSourceFactory()));

		environment.jersey().register(new ViewResource(customerDAO));
		environment.jersey().register(new CustomerResource(customerDAO));
	}
}
