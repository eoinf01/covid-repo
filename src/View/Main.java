package View;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import Controller.Controller;
import Tabs.AddContactTab;
import Tabs.CreateCloseContact;
import Tabs.SearchPerson;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;


//Student: Eoin Fehily
//Group: SDH2-A

public class Main extends Application {
	String textAreaString;
	
	@Override
	public void start(Stage primaryStage) {
			Controller control = new Controller();
			control.getDatabase().startDatabase();
			control.getDatabase().InitArray();
			//control.ReadObjectFromFile();
			BorderPane mainPane = new BorderPane();
			Group root = new Group();
			Scene scene = new Scene(root,400,400);
			
		      TabPane tp = new TabPane();
		      tp.getTabs().add (new AddContactTab(control));
		      tp.getTabs().add (new CreateCloseContact(control));
		      tp.getTabs().add (new SearchPerson(control));
			  mainPane.setCenter(tp);
			  
		      mainPane.prefHeightProperty().bind(scene.heightProperty());
		      mainPane.prefWidthProperty().bind(scene.widthProperty());
		      
		      root.getChildren().add(mainPane);
		      primaryStage.setScene(scene);
		      primaryStage.setMinHeight(500);
		      primaryStage.setMinWidth(500);		
		     control.setStage(primaryStage);
			primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
