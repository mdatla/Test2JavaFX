package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import backend.AttendanceHandler;
import backend.Globals;
import backend.LectureClass;
import backend.SetupHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class setupPageController implements Initializable{

	@FXML ListView<String> classListView = new ListView<String>();
	@FXML TextField timerMin = new TextField();
	@FXML TextField timerSec = new TextField();

	@FXML protected void startButtonPressed(ActionEvent event) throws IOException, InterruptedException{
		LectureClass newClass = new LectureClass();

			for(LectureClass lecture: Globals.classList){
				if(lecture.getName().equals(classListView.getSelectionModel().getSelectedItem())){
					Globals.currentClass = lecture;
				}
			}
			
		int timerLength =0;

		
		if(timerSec.getText()!=null){
			timerLength+=Integer.parseInt(timerSec.getText());
		}

		timerMin.setText("0");
		if(timerMin.getText()!=null){
			int tempMin = Integer.parseInt(timerMin.getText());
			tempMin = tempMin*60;

			timerLength+=tempMin;
		}

		Globals.rollCallLength = timerLength;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/timerBlueRoll.fxml"));
		VBox vbox = (VBox) loader.load();
		Scene scene = new Scene (vbox);
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
		
		AttendanceHandler.locateDevices(Globals.rollCallLength);
		AttendanceHandler.setAttend();
		
		Thread.sleep(12*1000);
		
		loader = new FXMLLoader(getClass().getResource("/application/attendanceDoneBlueRoll.fxml"));
		vbox = (VBox) loader.load();
		scene = new Scene (vbox);
		stage.setScene(scene);
		stage.show();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(Globals.defaultMin != 0){
			timerMin.setText(Integer.toString(Globals.defaultMin));
		}
		if(Globals.defaultSec !=0){
			timerSec.setText(Integer.toString(Globals.defaultSec));
		}
	}
}
