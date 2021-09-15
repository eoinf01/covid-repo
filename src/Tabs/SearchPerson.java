package Tabs;

import java.util.ArrayList;

import Controller.Controller;
import Model.Contact;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchPerson extends Tab{
	public SearchPerson(Controller control) {
		setText("Search Person and Contacts");
		
		VBox vertical = new VBox(10);
		HBox labels = new HBox(20);
		HBox dropdowns = new HBox(10);
		
		Label label = new Label("Person:");
		TextArea text = new TextArea();
		Button button = new Button("Search People");
		Button buttonPeople = new Button("Show All People");
		TextField name = new TextField("Enter First Name or ID");
		ComboBox<String> sorting = new ComboBox<String>();
		Label sortby = new Label("Search By:");
		sorting.getItems().add("Name");
		sorting.getItems().add("ID");
		
		button.setOnAction(e -> control.showCloseContacts(name,text,sorting));
		buttonPeople.setOnAction(e -> control.showAllPeople(text));
		labels.getChildren().addAll(label,name);
		labels.setAlignment(Pos.CENTER);
		dropdowns.getChildren().addAll(sortby,sorting);
		dropdowns.setAlignment(Pos.CENTER);
		vertical.getChildren().addAll(labels,dropdowns,text,button,buttonPeople);
		vertical.setAlignment(Pos.CENTER);
		vertical.setPadding(new Insets(15,15,15,15));
		
		setContent(vertical);

	}
}
