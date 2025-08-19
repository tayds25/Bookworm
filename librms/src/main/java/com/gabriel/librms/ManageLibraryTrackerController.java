package com.gabriel.librms;
import com.gabriel.librms.model.LibraryTracker;
import com.gabriel.librms.service.LibraryTrackerService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Setter;
import java.awt.Desktop;
import java.io.File;
import java.net.URI;

public class ManageLibraryTrackerController extends GenericLibraryTrackerController{
	@Setter
	Stage stage;

	@Setter
	Scene createViewScene;

	@Setter
	Scene editViewScene;

	@Setter
	Scene deleteViewScene;

	@javafx.fxml.FXML
	private TextField txtTitle;

	public ImageView libraryTrackerImage;

	LibraryTracker selectedItem;

	@FXML
	private ListView<LibraryTracker> lvLibraryTrackers;
	@FXML
	private TextField id;
	@FXML
	private TextField txtBorrowerPhoneNumber;
	@FXML
	private DatePicker dtBorrowDate;
	@FXML
	private ComboBox<String> comboId;
	@FXML
	private TextField txtISBN;
	@FXML
	private TextField txtBorrowerEmail;
	@FXML
	private DatePicker dtReturnDate;
	@FXML
	private TextField txtAuthor;
	@FXML
	private TextField txtBorrowerName;

	public void refresh() {
			LibraryTracker[] libraryTrackers = LibraryTrackerService.getService().getAll();
			lvLibraryTrackers.getItems().clear();
			lvLibraryTrackers.getItems().addAll(libraryTrackers);
			enableFields(false);
		}

	@Override
	public void init() {
		try {
			refresh();
		}
		catch (Exception e){
			showErrorDialog("Message: ", e.getMessage());
		}
	}

	@FXML
	public void onAction(MouseEvent mouseEvent) {
		GenericLibraryTrackerController.selectedItem = lvLibraryTrackers.getSelectionModel().getSelectedItem();
		if(GenericLibraryTrackerController.selectedItem == null) {
			return;
		}
		setFields("Manage");
	}
	@FXML
	public void onCreate(MouseEvent actionEvent)  throws Exception {
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(createViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManageLibraryTrackerJFXApp.class.getResource("create-libr-view.fxml"));
			Parent root = fxmlLoader.load();
			CreateLibraryTrackerController controller = fxmlLoader.getController();
			controller.setStage(stage);
			createViewScene = new Scene(root, 452, 549);
			controller.setManageLibraryTrackerController(this);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
		}
		stage.setTitle("Bookworm: Create Records");
		stage.setScene(createViewScene);
		stage.show();
	}
	@FXML
	public void onEdit(MouseEvent actionEvent)  throws Exception {
		if(GenericLibraryTrackerController.selectedItem == null){
			showErrorDialog("Please select an libraryTracker from the list", "Cannot edit");
		return;
		}
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(editViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManageLibraryTrackerJFXApp.class.getResource("edit-libr-view.fxml"));
			Parent root = fxmlLoader.load();
			EditLibraryTrackerController controller = fxmlLoader.getController();
			controller.setStage(stage);
			editViewScene = new Scene(root, 452, 549);
			controller.setManageLibraryTrackerController(this);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
		}
		stage.setTitle("Bookworm: Edit Records");
		stage.setScene(editViewScene);
		stage.show();
	}
	@FXML
	public void onDelete(MouseEvent actionEvent)  throws Exception {
		if(GenericLibraryTrackerController.selectedItem == null){
			showErrorDialog("Please select an libraryTracker from the list", "Cannot delete");
		return;
		}
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(deleteViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManageLibraryTrackerJFXApp.class.getResource("delete-libr-view.fxml"));
			Parent root = fxmlLoader.load();
			DeleteLibraryTrackerController controller = fxmlLoader.getController();
			controller.setStage(stage);
			deleteViewScene = new Scene(root, 452, 549);
			controller.setManageLibraryTrackerController(this);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
		}
		stage.setTitle("Bookworm: Delete Records");
		stage.setScene(deleteViewScene);
		stage.show();
	}
	@FXML
	public void onClose(MouseEvent actionEvent) {
		super.onClose(actionEvent);
	}

	@FXML
	public void clickAbout(MouseEvent event) {
		try {
			// Get the current working directory
			String currentDir = System.getProperty("user.dir");
			// Construct the path to the PDF file relative to the current working directory
			File pdfFile = new File(currentDir, "src/main/resources/About.pdf");
			URI pdfUri = pdfFile.toURI();

			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(pdfUri);
			} else {
				System.out.println("Desktop is not supported");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
