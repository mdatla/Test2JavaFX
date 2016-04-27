package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import backend.Globals;
import backend.LectureClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class MainController implements Initializable{
	protected int flag = 0;
	@FXML Text DisplayText;
	
	@FXML TextField inputTextField;
	
	@FXML Button customButton;

	@FXML protected void startAttendanceButtonPressed(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("setupPage.fxml"));
		
		VBox vbox = (VBox)loader.load();
		Scene scene = new Scene(vbox);
//		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setScene(scene);
		ListView list = (ListView)stage.getScene().lookup("#classListView");
		ArrayList<LectureClass> example = Globals.classList;
		ArrayList<String> text = new ArrayList<String>();
		System.out.println(example.get(1).getName());
		for(LectureClass lecture: example){
			text.add(lecture.getName());
			System.out.println(lecture.getName());
		}
		
		ObservableList<String> classSource = FXCollections.observableArrayList(text);
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			Globals.initializeGlobals();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


