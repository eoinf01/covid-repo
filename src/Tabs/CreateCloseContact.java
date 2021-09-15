package Tabs;

import java.util.ArrayList;

import Controller.Controller;
import Model.Contact;
import Model.Person;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateCloseContact extends Tab{
	public CreateCloseContact(Controller control){
		setText("Close Contact");
		
		HBox dropdownLayout = new HBox(10);
		HBox headings = new HBox(20);
		HBox inputs = new HBox(10);
		VBox layout = new VBox(10);
		
		Label date = new Label("Date: ");
		Label timeLabel = new Label("Time: ");
		Label contactLabel1 = new Label("Contact 1: ");
		Label contactLabel2 = new Label("Contact 2: ");
		DatePicker datePicker = new DatePicker();
		TextField time = new TextField();
		ComboBox<String> contact1 = new ComboBox<String>();
		ComboBox<String> contact2 = new ComboBox<String>();
		Button createCloseContact = new Button("Create Close Contact");
		Label created = new Label("");
		
		ArrayList<Person> list = control.getDatabase().getPersonList();
		for(int i =0; i<list.size();i++) {
			contact1.getItems().addAll("Contact ID:" + String.valueOf(list.get(i).getId()));
			contact2.getItems().addAll("Contact ID:" + String.valueOf(list.get(i).getId()));
		}
		
		headings.getChildren().addAll(contactLabel1,contactLabel2);
		dropdownLayout.getChildren().addAll(contact1,contact2);
		inputs.getChildren().addAll(date, datePicker);
		
		headings.setAlignment(Pos.CENTER);
		inputs.setAlignment(Pos.CENTER);
		dropdownLayout.setAlignment(Pos.CENTER);
		
		createCloseContact.setOnAction(e-> control.createCloseContact(contact1,contact2,datePicker.getValue(),created));
		contact1.setOnMouseClicked(e -> control.refreshList(contact1));
		contact2.setOnMouseClicked(e -> control.refreshList(contact2));
		
		layout.getChildren().addAll(headings,dropdownLayout,inputs,createCloseContact,created);
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setPadding(new Insets(10,10,10,10));
		setContent(layout);
	}
}
