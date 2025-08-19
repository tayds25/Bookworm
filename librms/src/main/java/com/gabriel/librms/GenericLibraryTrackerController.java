package com.gabriel.librms;
import com.gabriel.librms.model.LibraryTracker;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Setter;

import java.net.URL;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Locale;

public class GenericLibraryTrackerController implements Initializable{
	@Setter
	CreateLibraryTrackerController createLibraryTrackerController;

	@Setter
	DeleteLibraryTrackerController deleteLibraryTrackerController ;

	@Setter
	EditLibraryTrackerController editLibraryTrackerController;

	@Setter
	ManageLibraryTrackerController manageLibraryTrackerController;

	@Setter
	Stage stage;

	@Setter
	Scene splashScene;

	@Setter
	Scene manageScene;

	@Setter
	public ListView<LibraryTracker> lvLibraryTrackers;

	@Setter
	public static LibraryTracker selectedItem;
	public TextField id;
	public ComboBox<String> comboId;;
	public TextField txtTitle;
	public TextField txtAuthor;
	public TextField txtISBN;
	public TextField txtBorrowerName;
	public TextField txtBorrowerEmail;
	public TextField txtBorrowerPhoneNumber;
	public DatePicker dtBorrowDate;
	public DatePicker dtReturnDate;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		init();
	}
	protected void init(){
		System.out.println("Invoked from Generic Controller");
	}
	protected LibraryTracker toObject(boolean isEdit){
		LibraryTracker libraryTracker= new LibraryTracker();
		try {
			if(isEdit) {
				libraryTracker.setId(String.valueOf(libraryTracker.getValue()));
			}
			libraryTracker.setTitle(txtTitle.getText());
			libraryTracker.setAuthor(txtAuthor.getText());
			libraryTracker.setISBN(txtISBN.getText());
			libraryTracker.setBorrowerEmail(txtBorrowerEmail.getText());
			libraryTracker.setBorrowerPhoneNumber(txtBorrowerPhoneNumber.getText());
			libraryTracker.setBorrowDate(toDate(dtBorrowDate.getValue()));
			libraryTracker.setReturnDate(toDate(dtReturnDate.getValue()));
		}catch (Exception e){
			showErrorDialog("Error" ,e.getMessage());
		}
		return libraryTracker;
	}
	protected void setFields(String action){
		String formattedDate;
		LibraryTracker libraryTracker = GenericLibraryTrackerController.selectedItem;
		SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
		libraryTracker.setId(String.valueOf(libraryTracker.getValue()));
		txtTitle.setText(libraryTracker.getTitle());
		txtAuthor.setText(libraryTracker.getAuthor());
		txtISBN.setText(libraryTracker.getISBN());
		txtBorrowerName.setText(libraryTracker.getBorrowerName());
		txtBorrowerEmail.setText(libraryTracker.getBorrowerEmail());
		txtBorrowerPhoneNumber.setText(libraryTracker.getBorrowerPhoneNumber());
		dtBorrowDate.setValue(toLocalDate(libraryTracker.getBorrowDate()));
		dtReturnDate.setValue(toLocalDate(libraryTracker.getReturnDate()));
	}

	protected void clearFields(String action){
		comboId.getSelectionModel().clearSelection();
		txtTitle.setText("");
		txtAuthor.setText("");
		txtISBN.setText("");
		txtBorrowerName.setText("");
		txtBorrowerEmail.setText("");
		txtBorrowerPhoneNumber.setText("");
		dtBorrowDate.setValue(null);
		dtReturnDate.setValue(null);
	}

	protected void enableFields(boolean enable){
		txtTitle.editableProperty().set(enable);
		txtAuthor.editableProperty().set(enable);
		txtISBN.editableProperty().set(enable);
		txtBorrowerName.editableProperty().set(enable);
		txtBorrowerEmail.editableProperty().set(enable);
		txtBorrowerPhoneNumber.editableProperty().set(enable);
		dtBorrowDate.editableProperty().set(enable);
		dtReturnDate.editableProperty().set(enable);
	}

	public int getId(){
		return Integer.parseInt(id.getText());
	}

	protected void showErrorDialog(String message, String expandedMessage){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(message);
		alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(expandedMessage)));
		alert.showAndWait();
	}
	public void onBack(MouseEvent actionEvent) throws IOException {
		Node node = ((Node) (actionEvent.getSource()));
		Window window = node.getScene().getWindow();
		window.hide();
		stage.setScene(manageScene);
		stage.show();
	}
	public void onClose(MouseEvent actionEvent) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit and lose changes? " , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			Platform.exit();
		}
	}
	LocalDate toLocalDate(Date date){
		Instant instant = date.toInstant();
		ZoneId z = ZoneId.of("Singapore");
		ZonedDateTime zdt = instant.atZone( z );
		return zdt.toLocalDate();
	}
	protected Date toDate(LocalDate ld){
		ZoneId z = ZoneId.of("Singapore");
		ZonedDateTime zdt = ld.atStartOfDay(z);
		Instant instant  = zdt.toInstant();
		return Date.from(instant);
	}
}

