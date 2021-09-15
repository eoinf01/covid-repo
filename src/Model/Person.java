package Model;

import java.io.Serializable;

public class Person implements Serializable{
	private Name name;
	private int id;
	private int phone;
	private String email;
	
	public Person(int id,Name name,int phone) {
		this.setName(name);
		this.setId(id);
		this.setPhone(phone);
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
