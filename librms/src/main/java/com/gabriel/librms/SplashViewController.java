package com.gabriel.librms;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Setter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashViewController implements Initializable {

	@Setter
	Stage stage;
	@Setter
	Scene manageScene = null;
	@Setter
	Scene splashScene = null;
	public ImageView imgHug;
	@javafx.fxml.FXML
	private ImageView splashLogo;
	@javafx.fxml.FXML
	private TextField userText;
	@javafx.fxml.FXML
	private TextField passText;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if(stage != null) {
			stage.setResizable(false);
			stage.setWidth(588);
			stage.setHeight(517);
		}
	}

	@javafx.fxml.FXML
	public void onClicked(ActionEvent actionEvent) throws IOException {
		System.out.println("SplashController :onClicked ");
		String username = userText.getText();
		String password = passText.getText();

		// "user" and "password" are the correct credentials
		if ("user".equals(username) && "password".equals(password)) {
			Node node = ((Node) (actionEvent.getSource()));
			Scene currentScene = node.getScene();
			Window window = currentScene.getWindow();
			window.hide();
			if (manageScene == null) {
				FXMLLoader fxmlLoader = new FXMLLoader(ManageLibraryTrackerJFXApp.class.getResource("manage-libr-view.fxml"));
				Parent root = fxmlLoader.load();
				ManageLibraryTrackerController controller = fxmlLoader.getController();
				controller.setStage(stage);
				manageScene = new Scene(root, 850, 584);
				controller.setSplashScene(splashScene);
				controller.setManageScene(manageScene);
			}
			stage.setTitle("Bookworm: Library Tracker");
			stage.setScene(manageScene);
			stage.show();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Login Error");
			alert.setHeaderText(null);
			alert.setContentText("Invalid username or password. Please try again.");
			alert.showAndWait();
		}
	}
}
