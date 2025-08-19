package com.gabriel.librms;
import com.gabriel.librms.model.LibraryTracker;
import com.gabriel.librms.service.LibraryTrackerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class DeleteLibraryTrackerController extends GenericLibraryTrackerController {
	public ImageView imgLibraryTracker;
	@javafx.fxml.FXML
	private TextField txtBorrowerPhoneNumber;
	@javafx.fxml.FXML
	private DatePicker dtBorrowDate;
	@javafx.fxml.FXML
	private ComboBox comboId;
	@javafx.fxml.FXML
	private TextField id;
	@javafx.fxml.FXML
	private TextField txtISBN;
	@javafx.fxml.FXML
	private TextField txtBorrowerEmail;
	@javafx.fxml.FXML
	private DatePicker dtReturnDate;
	@javafx.fxml.FXML
	private TextField txtTitle;
	@javafx.fxml.FXML
	private TextField txtAuthor;
	@javafx.fxml.FXML
	private TextField txtBorrowerName;

	@Override
	public void init() {
		setFields("Delete");
		enableFields(false);
	}
	@javafx.fxml.FXML
    public void onSubmit(ActionEvent actionEvent) {
		try {
			LibraryTracker libraryTracker = toObject(true);
			LibraryTrackerService.getService().delete(Integer.parseInt(libraryTracker.getId()));
			Node node = ((Node) (actionEvent.getSource()));
			Window window = node.getScene().getWindow();
			window.hide();
			stage.setTitle("Manage LibraryTracker");
			stage.setScene(manageScene);
			stage.show();
		}
		catch (Exception e){
			showErrorDialog("Error encountered creating libraryTracker", e.getMessage());
		}
	}
	@javafx.fxml.FXML
	public void onClose(MouseEvent actionEvent) {
		super.onClose(actionEvent);
	}

	@javafx.fxml.FXML
	public void onBack(MouseEvent actionEvent) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gabriel/librms/manage-libr-view.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		stage.hide();

		ManageLibraryTrackerController controller = fxmlLoader.getController();
		controller.setStage(stage);
		manageScene = new Scene(root, 850, 584);
		controller.setManageScene(manageScene);
		stage.setTitle("Bookworm: Library Tracker");
		stage.setScene(manageScene);
		stage.show();
	}
}
