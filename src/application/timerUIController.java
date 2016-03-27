package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class timerUIController {

	@FXML protected void stopButtonClicked(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("attendanceDoneBlueRoll.fxml"));
		
		VBox vbox = (VBox) loader.load();
		Scene scene = new Scene (vbox);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
}
