package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class setupPageController implements Initializable{
	
	@FXML static ListView<String> classListView = new ListView<String>();
	
	@FXML protected void startButtonPressed(ActionEvent event) throws IOException{
		ObservableList<String> classSource = FXCollections.observableArrayList(
				"CSCE 330", "CSCE 372", "CSCE 451");
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		ListView list = (ListView)stage.getScene().lookup("#classListView");
		list.setItems(classSource);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
	}
}
