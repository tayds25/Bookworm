package com.gabriel.librms;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ManageLibraryTrackerJFXApp extends Application {
@Override
	public void start(Stage stage) throws IOException {
		System.out.println("SplashApp:start ");
		FXMLLoader fxmlLoader = new FXMLLoader(ManageLibraryTrackerJFXApp.class.getResource("splash-view.fxml"));
		Parent root = (Parent)fxmlLoader.load();
		SplashViewController splashViewController= fxmlLoader.getController();
		splashViewController.setStage(stage);
		Scene scene = new Scene(root, 588, 613);
		splashViewController.setSplashScene(scene);
		stage.setTitle("Bookworm: User Login");
		stage.setScene(scene);
	stage.setResizable(false);
		stage.show();
	}
}
