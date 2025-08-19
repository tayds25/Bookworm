package com.gabriel.librms;
import com.gabriel.librms.model.LibraryTracker;
import com.gabriel.librms.service.LibraryTrackerService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lombok.Setter;

import java.io.IOException;

public class CreateLibraryTrackerController extends GenericLibraryTrackerController {
	public ImageView imgLibraryTracker;
    @javafx.fxml.FXML
	private TextField txtBorrowerPhoneNumber;
	@javafx.fxml.FXML
	private DatePicker dtBorrowDate;
	@javafx.fxml.FXML
	private ComboBox<String> comboId;
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
	@FXML
	private TextField id;

	@Override
	public void init() {
		clearFields("Edit");
		enableFields(true);
		populateComboBox();

		comboId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// Check if the selected ID is "ID1"
				if ("Harry Potter and the Philosopher's Stone".equals(newValue)) {
					txtTitle.setText("Harry Potter and the Philosopher's Stone");
					txtAuthor.setText("J.K. Rowling");
					txtISBN.setText("9780747532699");
				} else if ("The Lord of the Rings".equals(newValue)) {
					txtTitle.setText("The Lord of the Rings");
					txtAuthor.setText("J.R.R. Tolkien");
					txtISBN.setText("9780261102385");
				} else if ("A Game of Thrones".equals(newValue)) {
					txtTitle.setText("A Game of Thrones");
					txtAuthor.setText("George R.R. Martin");
					txtISBN.setText("9780553573404");
				}
			}
		});
	}

	private void populateComboBox() {
		comboId.setItems(FXCollections.observableArrayList("Harry Potter and the Philosopher's Stone", "The Lord of the Rings", "A Game of Thrones"));
	}

	@javafx.fxml.FXML
	public void onSubmit(MouseEvent actionEvent) {
		try {
			LibraryTracker libraryTracker = toObject(false);
			LibraryTracker newLibraryTracker = LibraryTrackerService.getService().create(libraryTracker);
			manageLibraryTrackerController.refresh();
			Node node = ((Node) (actionEvent.getSource()));
			Window window = node.getScene().getWindow();
			window.hide();
			stage.setTitle("Bookworm: Library Tracker");
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
