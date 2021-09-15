package Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import Alerts.removeAlert;
import Model.Contact;
import Model.Name;
import Model.Person;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
	Database database;
	Stage stage;

	//When creating an instance of controller a new contactlist is also amde
	public Controller(){
		database = Database.getInstance();
	}
	
	public Database getDatabase() {
		return this.database;
	}
	public Stage getStage() {
		return this.stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
    //method for adding contact to arraylist takes in TextFields as parameters
	public void addContact(TextField first,TextField last,TextField id,TextField phone) {
			if(first.getText().isBlank() || last.getText().isBlank() || id.getText().isBlank() || phone.getText().isBlank()) {
				System.out.println("One of the fields is empty.");
			}
			else {
				Name name = new Name(first.getText(),last.getText());
				Person newPerson = new Person(Integer.valueOf(id.getText()),name , Integer.valueOf(phone.getText()));
				database.addPerson(newPerson);
			}
	}
		
	public void removeContact(Person person) {
		database.removePerson(person);
	}
	
	//method returns string of all the contacts
	public String listContacts() {
		String textAreaString  ="";
		//local arraylist set to contact list for traversing
		//traverses through contact object in array list adding to text string
		ArrayList<Person> list = database.personList;
		for(int i =0;i<list.size();i++) {
			textAreaString += "\n---Contact " + (i+1) + "----" + 
			 "\nFirst Name: " + list.get(i).getName().getFirstName() +
			 "\nLast Name: " + list.get(i).getName().getLastName() +
			 "\nPhone Number: " + list.get(i).getPhone() + 
			 "\nUnique ID: " + list.get(i).getId() + "\n";
		}
		//text string returned
		return textAreaString;
	}
	//method writes object to file
	public void WriteObjectToFile() {
		try {
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
			fileChooser.getExtensionFilters().add(extFilter);
			fileChooser.setTitle("Select SER File");
			File newfile = fileChooser.showOpenDialog(stage);
			if(newfile!= null) {
				String path = newfile.getAbsolutePath();
	            FileOutputStream fileOut = new FileOutputStream(path);
	            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	            objectOut.writeObject(database.getPersonList());
	            objectOut.writeObject(database.getContactList());
	            objectOut.close();
	            System.out.println("The Object  was succesfully written to a file");
			}
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	//creates CloseContact and adds to contact arraylist for close contacts
	public void createCloseContact(ComboBox<String> index,ComboBox<String> index2,LocalDate date,Label text) {
		
		int index1 = index.getSelectionModel().getSelectedIndex();
		int index22 = index2.getSelectionModel().getSelectedIndex();
		
		ArrayList<Person> list = database.personList;
		Person contact = list.get(index1);
		Person contact2 = list.get(index22);
		
		if(index1 == -1 || index22 ==-1) {
			text.setText("Two Contacts Not Selected");
		}
		else if(contact.equals(contact2)) {
			text.setText("Can't create close contact between same contact.");
		}
		else {
			Date newDate = Date.valueOf(date);
			Contact newContact = new Contact(contact.getId(), contact2.getId(), newDate);
			database.addContact(newContact);
			text.setText("Close Contact Created");
		}
	}
	//method updates text Area with closeContacts of selected contact
	public void showCloseContacts(TextField input,TextArea text,ComboBox<String> dropdown) {
		String textArea = "";
		int selection = dropdown.getSelectionModel().getSelectedIndex();
		
		ArrayList<Person> list = database.personList;
		ArrayList<Contact> contact = database.contactList;
		boolean success = false;
		
		for(int i =0;i<list.size();i++) {
			if(selection == 0) {
				if((list.get(i).getName().getFirstName().equals(input.getText()))) {
					success = true;
					textArea+= "\nPerson: ";
					textArea+= "\nID: " +list.get(i).getId();
					textArea+= "\nFirst Name: " + list.get(i).getName().getFirstName();
					textArea+= "\nLast Name: " + list.get(i).getName().getLastName();
					textArea+= "\nPhone: " + list.get(i).getPhone() + "\n";
					
					for(int j=0;j<contact.size();j++) {
						if(contact.get(j).getPersonId1() == list.get(i).getId()) {
							textArea+= "\nContact: ";
							textArea+= "\nID 1: " + contact.get(j).getPersonId1();
							textArea+= "\nID 2: " + contact.get(j).getPersonId2();
							textArea+= "\nDate: " + contact.get(j).getDateContact().toString() + "\n";
						}
					}
				}
			}
			else if(selection == 1) {
				try {
					int number  = Integer.parseInt(input.getText());
					if(list.get(i).getId() == number) {
						success = true;
						textArea+= "\nPerson: ";
						textArea+= "\nID: " +list.get(i).getId();
						textArea+= "\nFirst Name: " + list.get(i).getName().getFirstName();
						textArea+= "\nLast Name: " + list.get(i).getName().getLastName();
						textArea+= "\nPhone: " + list.get(i).getPhone() + "\n";
						for(int j=0;j<contact.size();j++) {
							if(contact.get(j).getPersonId1() == number) {
								textArea+= "\nContact: ";
								textArea+= "\nID 1: " + contact.get(j).getPersonId1();
								textArea+= "\nID 2: " + contact.get(j).getPersonId2();
								textArea+= "\nDate: " + contact.get(j).getDateContact() + "\n";
							}
							else if(contact.get(j).getPersonId2() == number) {
								textArea+= "\nContact: ";
								textArea+= "\nID 1: " + contact.get(j).getPersonId1();
								textArea+= "\nID 2: " + contact.get(j).getPersonId2();
								textArea+= "\nDate: " + contact.get(j).getDateContact() + "\n";
							}
						}
					}
			    } catch (final NumberFormatException e) {
			    	success = false;
			    }
			}
		}
		if(success == false) {
			text.setText("No results found.");
		}
		else {
			text.setText(textArea);
		}
	}
	
	//method to refresh list 
	public void refreshList(ComboBox<String> dropdown) {
		dropdown.getItems().clear();
		ArrayList<Person> list = database.personList;
		for(int i =0; i<list.size();i++) {
			dropdown.getItems().addAll("Contact ID: " + list.get(i).getId());
		}
	}
	
	public void showAllPeople(TextArea text) {
		String textArea = "";
		ArrayList<Person> person = getDatabase().getPersonList();
		for(int i=0;i<person.size();i++) {
			textArea+= "\nPerson: ";
			textArea+= "\nID: " +person.get(i).getId();
			textArea+= "\nFirst Name: " + person.get(i).getName().getFirstName();
			textArea+= "\nLast Name: " + person.get(i).getName().getLastName();
			textArea+= "\nPhone: " + person.get(i).getPhone() + "\n";
		}
		text.setText(textArea);
	}
	public void heapDump() {
		List<Person> list = new ArrayList<Person>();
		Name name = new Name("Eoin","Fehily");
		while(1<2){
			Person new1 = new Person(1,name,1234);
			list.add(new1);
		}
	}
}
