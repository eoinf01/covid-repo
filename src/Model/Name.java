package Model;

import java.io.Serializable;

public class Name implements Serializable{
	private String firstName;
	private String middleName;
	private String lastName;
	
	public Name(String firstName,String lastName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
