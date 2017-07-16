package tw.com.oscar.fx8.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tw.com.oscar.fx8.MainApp;
import tw.com.oscar.fx8.model.Person;
import tw.com.oscar.fx8.util.DateUtil;

public class MainViewController {
    
    private MainApp mainApp;
    
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label birthdayLabel;
    
    public MainViewController() {
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        personTable.setItems(this.mainApp.getPersonList());
    }
    
    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        this.showPersonDetail(null);
        personTable.getSelectionModel().selectedItemProperty()
                .addListener((observal, oldValue, newValue) -> showPersonDetail(newValue));
    }
    
    @FXML
    private void newPersonHandle() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonList().add(tempPerson);
        }
    }
    
    @FXML
    private void editPersonHandle() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetail(selectedPerson);
            }
            
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a person in the table.");
            
            alert.showAndWait();
        }
    }
    
    @FXML
    private void deletePersonHandle() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
//			Dialogs.create().title("No Selection").masthead("No Person Selected")
//					.message("Please select a person in the table.").showWarning();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a person in the table.");
            
            alert.showAndWait();
        }
    }
    
    private void showPersonDetail(Person person) {
        if (null != person) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            birthdayLabel.setText("");
        }
    }
    
}
