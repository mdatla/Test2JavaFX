package application;
	
import java.io.FileNotFoundException;
import java.io.IOException;

import backend.Globals;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.event.*;
import javafx.fxml.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root  = FXMLLoader.load(getClass().getResource("UI2.fxml"));
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			primaryStage.setTitle("Test App");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
