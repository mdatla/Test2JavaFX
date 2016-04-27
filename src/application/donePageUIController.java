package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import backend.Globals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class donePageUIController implements Initializable {
	
	@FXML Text numPres;
	
	@FXML Text numAbs;
	
	@FXML Text numTot;
	
	@FXML protected void mainButtonPressed(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UI2.fxml"));
		
		VBox vbox = (VBox) loader.load();
		Scene scene = new Scene (vbox);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML protected void closeButtonPressed(ActionEvent event) throws IOException{
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		numPres.setText(Integer.toString(Globals.presentStudents));
		numAbs.setText(Integer.toString(Globals.TotalStudents - Globals.presentStudents));
		numTot.setText(Integer.toString(Globals.TotalStudents));
		
		numPres.setVisible(true);
		numAbs.setVisible(true);
		numTot.setVisible(true);
	}
	
	
}
