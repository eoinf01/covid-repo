package Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.Contact;
import Model.Name;
import Model.Person;
import javafx.scene.control.TextField;

public class Database {
	
	private static Database instance = new Database();
	final String DATABASEURL = "jdbc:derby:C:\\scratch\\testDB";
	private Connection connection = null;
	private ResultSet resultSet = null;
	private Statement statement = null;
	private PreparedStatement prepstmt;
	public ArrayList<Person> personList;
	public ArrayList<Contact> contactList;
	
	private Database(){}
	
	public static Database getInstance() {
		return instance;
	}
	public ArrayList<Person> getPersonList(){
		return this.personList;
	}
	
	public void setPersonList(ArrayList<Person> person){
		this.personList = person;
	}
	
	public ArrayList<Contact> getContactList(){
		return this.contactList;
	}
	
	public void setContactList(ArrayList<Contact> contact){
		this.contactList = contact;
	}
	
	public void startDatabase() {
    	try {
    		connection = DriverManager.getConnection(DATABASEURL,"","");
        	statement  = connection.createStatement();
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
	
	public void InitArray() {
		try {
			ArrayList<Person> newList = new ArrayList<Person>();
			ResultSet rs = statement.executeQuery("SELECT * FROM Person");
	        while(rs.next()){
	            int ID = rs.getInt("ID");
	            String pName = rs.getString("FIRSTNAME");
	            String pLName = rs.getString("LASTNAME");
	            Name name = new Name(pName,pLName);
	            int pPhoneNo = rs.getInt("PHONE");
	            newList.add(new Person(ID,name,pPhoneNo));
	        }
	        setPersonList(newList);
	        
	        ResultSet resultset = statement.executeQuery("SELECT * FROM Contact");
	        ArrayList<Contact> newContatList = new ArrayList<Contact>();
	        while(resultset.next()) {
	        	int ID1 = resultset.getInt("ID1");
	        	int ID2 = resultset.getInt("ID2");
	        	Date date = resultset.getDate("DATE");
	            newContatList.add(new Contact(ID1,ID2,date));
	        }
	        setContactList(newContatList);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeContact(Person person) {
		try {
			statement.executeUpdate("DELETE FROM Person " + "WHERE ID = " + person.getId());
			statement.executeUpdate("DELETE FROM Contact " + "WHERE ID = " + person.getId());
			System.out.println("Delete successful");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addPerson(Person person) {
		try {
				prepstmt = connection.prepareStatement("INSERT INTO Person(ID,FIRSTNAME,LASTNAME,PHONE) VALUES(?,?,?,?)");
				prepstmt.setString(1,String.valueOf(person.getId()));
				prepstmt.setString(2,person.getName().getFirstName());
				prepstmt.setString(3,person.getName().getLastName());
				prepstmt.setString(4,String.valueOf(person.getPhone()));
				prepstmt.executeUpdate();
				System.out.println("New Person registered!");
				InitArray();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	

	public void addContact(Contact contact) {
		try {
			prepstmt = connection.prepareStatement("INSERT INTO Contact(ID1,ID2,DATE) VALUES(?,?,?)");
			prepstmt.setInt(1,contact.getPersonId1());
			prepstmt.setInt(2,contact.getPersonId2());
			prepstmt.setDate(3,contact.getDateContact());
			prepstmt.executeUpdate();
			System.out.println("New contact registered!");
			InitArray();
		} catch (SQLException e) {
			try {
				prepstmt = connection.prepareStatement("UPDATE Contact SET ID2 = ?,DATE = ? WHERE ID1 = ?");
				prepstmt.setInt(1,contact.getPersonId1());
				prepstmt.setDate(2,contact.getDateContact());
				prepstmt.setInt(3,contact.getPersonId2());
				prepstmt.executeUpdate();
				System.out.println("New contact registered!");
				InitArray();
			}
			catch(SQLException g) {
				g.printStackTrace();
			}
		}
	}
	
	public void removePerson(Person person) {
		try {
			statement.executeUpdate("DELETE FROM Person " + "WHERE ID = " + person.getId());
			System.out.println("Delete successful");
			InitArray();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
