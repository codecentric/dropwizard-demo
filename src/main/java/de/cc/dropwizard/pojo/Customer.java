package de.cc.dropwizard.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by fema on 19.09.14.
 */
@Entity
@Table(name = "customer")
@NamedQueries({ @NamedQuery(name = "de.cc.dropwizard.pojo.Customer.findAll", query = "SELECT c FROM Customer c") })
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "fullName", nullable = false)
	private String fullName;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "jobTitle", nullable = false)
	private String jobTitle;

	@JsonProperty
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@JsonProperty
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@JsonProperty
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
