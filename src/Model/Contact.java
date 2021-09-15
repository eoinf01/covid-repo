package Model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


public class Contact implements Serializable{
	
	private static final long serialVersionUID = -6993335302780595515L;
	private int PersonId1;
	private int PersonId2;
	private Date dateContact;
	//constructor for Contact object
	public Contact(int id, int id2, Date date) {
		// TODO Auto-generated constructor stub
		this.setPersonId1(id);
		this.setPersonId2(id2);
		this.setDateContact(date);
	}
	public int getPersonId1() {
		return PersonId1;
	}
	public void setPersonId1(int personId1) {
		PersonId1 = personId1;
	}
	public int getPersonId2() {
		return PersonId2;
	}
	public void setPersonId2(int personId2) {
		PersonId2 = personId2;
	}
	public Date getDateContact() {
		return dateContact;
	}
	public void setDateContact(Date dateContact) {
		this.dateContact = dateContact;
	}

	
}
