package Alerts;

import java.util.ArrayList;

import Controller.Controller;
import Model.Contact;
import Model.Person;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class removeAlert {
	private static Stage window;
	private static ArrayList<Person> list;
	//display method for pop up window displaying options to remove contacts
	public static void display(Controller control) {
		list = control.getDatabase().getPersonList();
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Remove Contact");
		window.setMinWidth(250);
		window.setMinHeight(50);
		
		//Label and button objects
		Label label = new Label("Select Contact to remove: ");
		Button removeBtn = new Button("Remove Selected");
		
		//List view of type string
		ListView<String> contactsList = new ListView<String>();
		for(int i=0;i<list.size();i++) {
			contactsList.getItems().add(String.valueOf(list.get(i).getId()));
		}
		removeBtn.setOnAction(e -> removeItem(contactsList,control));
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label,contactsList,removeBtn);
		layout.setAlignment(Pos.TOP_CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	//method for removing item from list takes in arraylist and listview as parameters
	public static void removeItem(ListView<String> e,Controller control) {
		//set integer to ListView selection index
		ArrayList<Person> person = control.getDatabase().getPersonList();
		int remove  = e.getSelectionModel().getSelectedIndex();
		Person person_remove = person.get(remove);
		//remove item from listview using selection index
		//remove selected item from contactList
		control.removeContact(person_remove);
		e.getItems().remove(remove);
		window.close();
	}
}
