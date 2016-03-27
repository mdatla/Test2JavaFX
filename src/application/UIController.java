package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class UIController {
	protected int flag = 0;
	@FXML Text DisplayText;
	
	@FXML TextField inputTextField;
	
	@FXML Button customButton;

	@FXML protected void startAttendanceButtonPressed(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(UIController.class.getResource("setupPage.fxml"));
		
		VBox vbox = (VBox)loader.load();
		Scene scene = new Scene(vbox);
//		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setScene(scene);
		ListView list = (ListView)stage.getScene().lookup("#classListView");
		ObservableList<String> classSource = FXCollections.observableArrayList(
				"CSCE 325", "CSCE 378", "CSCE 451");
		list.setItems(classSource);
		stage.show();
		
	}
	
	@FXML protected void settingsButtonClicked(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("settingBlueRoll.fxml"));
		
		VBox vbox = (VBox) loader.load();
		Scene scene = new Scene (vbox);
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML protected void imageClicked(ActionEvent event){
		System.out.println("Hello. The image was pressed.");
	}
}


