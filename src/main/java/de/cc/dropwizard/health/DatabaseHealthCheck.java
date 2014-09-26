package de.cc.dropwizard.health;


import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.hibernate.HibernateBundle;


/**
 * Created by fema on 26.09.14.
 */
public class DatabaseHealthCheck extends HealthCheck {
	private final HibernateBundle hibernateBundle;

	public DatabaseHealthCheck(HibernateBundle hibernateBundle) {
		this.hibernateBundle = hibernateBundle;
	}

	@Override
	protected Result check() throws Exception {
		if(hibernateBundle.getSessionFactory().isClosed()) {
			return Result.unhealthy("Database Session is closed");
		} else {
			return Result.healthy("Database Session is open for requests");
		}
	}
}
