package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
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

	@FXML protected void buttonPressed(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(UIController.class.getResource("UI2.fxml"));
		VBox login = (VBox)loader.load();
		Scene scene = new Scene(login);
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
//		if(flag==0){
//			DisplayText.setText("Toggle On");
//			DisplayText.setFill(Color.WHITE);
//			flag=1;
//		}else{
//			DisplayText.setText("Toggle Off");
//			flag = 0;
//		}
	}
}


