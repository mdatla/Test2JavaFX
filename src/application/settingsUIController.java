package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ResourceBundle;

import backend.Globals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class settingsUIController implements Initializable{

	@FXML TextField fileLocationField;

	@FXML TextField defaultMinsField;

	@FXML TextField defaultSecsField;

	@FXML protected void openFileLocationClicked(ActionEvent event) throws IOException{
		String path = System.getProperty("user.dir");
		Runtime.getRuntime().exec("explorer.exe /select," + path);
	}

	@FXML protected void saveButtonClicked(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UI2.fxml"));

		File f = new File("config.ini");
		FileOutputStream fos = new FileOutputStream(f);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.write(defaultMinsField.getText().trim() + "#"+ defaultSecsField.getText().trim() +"\n");
		bw.close();

		VBox vbox = (VBox) loader.load();
		Scene scene = new Scene (vbox);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	@FXML protected void cancelButtonClicked(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("UI2.fxml"));

		VBox vbox = (VBox) loader.load();
		Scene scene = new Scene (vbox);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		defaultMinsField.setText(Integer.toString(Globals.defaultMin));
		defaultSecsField.setText(Integer.toString(Globals.defaultSec));
	}
}
