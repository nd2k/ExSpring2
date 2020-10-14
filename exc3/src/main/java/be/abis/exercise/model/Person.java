package be.abis.exercise.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class Person {

	private int personId;
	@NotBlank(message = "First name field is mandatory")
	private String firstName;
	private String lastName;
	@Digits(integer = 3, fraction = 0)
	@Range(min = 18, message = "Person should be at least 18 years old")
	private int age;
	@NotBlank(message = "Email field is mandatory")
	@Email(message = "The email address must be valid")
	private String emailAddress;
	@NotBlank(message = "Password field is mandatory")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
	private String language;
	@Valid
	private Company company;


	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Person with id " + personId + ", " + firstName + " "+ lastName + ", works for " +company.getName() + " in " + company.getAddress().getTown();
	}



}
