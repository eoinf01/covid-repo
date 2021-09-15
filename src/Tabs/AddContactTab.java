package Tabs;

import java.io.File;

import Alerts.removeAlert;
import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class AddContactTab extends Tab{
	public AddContactTab(Controller control){
		FileChooser fileChooser = new FileChooser();
		setText("Add Contact");
		
		//Reads list from file automatically
		//control.ReadObjectFromFile();
		
		//HBoxes for Contact inputs and buttons
		HBox heading = new HBox(10);
		HBox firstEntry = new HBox(10);
		HBox lastNameEntry = new HBox(10);
		HBox uniqueIDEntry = new HBox(10);
		HBox phoneNumberEntry = new HBox(10);
		HBox buttonActions = new HBox(10);
		HBox buttonActions2 = new HBox(140);	
		
		//Labels for Heading and contact inputs
		Label lastName = new Label("Last Name:");
		Label firstName = new Label("First Name:");
		Label uniqueID = new Label("Unique ID:");
		Label phoneNumber = new Label("Phone Number:");
		Label contactHeading = new Label("---Contacts---");
		
		//Buttons for the functionality of program
		Button add = new Button("ADD");
		Button remove = new Button("remove");			
		Button list = new Button("list");
		Button heap = new Button("Heap Dump");
		Button save = new Button("Save");			
		Button exit = new Button("Exit");
		
		//Text inputs for contact form and textarea to list the contacts
		TextArea textArea = new TextArea("People in the Application");
		TextField uniqueIDInput = new TextField("");
		TextField lastNameInput = new TextField("");
		TextField firstNameInput = new TextField("");
		TextField phoneNumberInput = new TextField("");
		
		//on click for load button calls control method that reads arraylist from file
		heap.setOnAction(e -> control.heapDump());
//		//saves arraylist to file
		save.setOnAction(e -> control.WriteObjectToFile());
//		//calls exit method from controller
		exit.setOnAction(e -> System.exit(0));
//		//calls display from the removeAlert scene to allow users to remove contacts
		remove.setOnAction(e -> removeAlert.display(control));
//		//onclick for list button to display contacts in textarea
		list.setOnAction(e -> textArea.setText(control.listContacts()));
		//Adds contact to arraylist using method from controller class
		add.setOnAction(e -> control.addContact(firstNameInput,lastNameInput,uniqueIDInput,phoneNumberInput));
		
		//Adding buttons objects for HBox
		buttonActions.getChildren().addAll(add,remove,list);
		//Adding button objects for HBox
		buttonActions2.getChildren().addAll(heap,save,exit);
		//Adding textfield objects for HBox for ID input
		uniqueIDEntry.getChildren().addAll(uniqueID,uniqueIDInput);
		//Adding textfield objects for HBox for last name input
		lastNameEntry.getChildren().addAll(lastName,lastNameInput);
		//Adding textfield objects for HBox for first name input
		firstEntry.getChildren().addAll(firstName,firstNameInput);
		//Adding textfield objects for HBox for phone input
		phoneNumberEntry.getChildren().addAll(phoneNumber,phoneNumberInput);
		//Adding heading object to HBox for heading
		heading.getChildren().add(contactHeading);
		heading.setAlignment(Pos.CENTER);
		buttonActions2.setAlignment(Pos.CENTER);
		//Creating VBox and adding all HBox elements to it
		VBox layout = new VBox(10);
		layout.getChildren().addAll(heading,firstEntry,lastNameEntry,uniqueIDEntry,phoneNumberEntry,buttonActions,textArea,buttonActions2);
		layout.setPadding(new Insets(20,20,20,20));
		setContent(layout);

	}
}
