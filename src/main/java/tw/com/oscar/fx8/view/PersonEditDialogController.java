package tw.com.oscar.fx8.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import tw.com.oscar.fx8.model.Person;
import tw.com.oscar.fx8.util.DateUtil;

@SuppressWarnings("deprecation")
public class PersonEditDialogController {
    
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField birthdayField;
    
    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;
    
    @FXML
    private void initialize() {
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setPerson(Person person) {
        this.person = person;
        
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("yyyy/MM/dd");
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    @FXML
    private void okHandle() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));
            
            okClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    private void cancelHandle() {
        dialogStage.close();
    }
    
    private boolean isInputValid() {
        String errorMessage = "";
        
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        
        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Dialogs.create().title("Invalid Fields").masthead("Please correct invalid fields").message(errorMessage)
                    .showError();
            return false;
        }
    }
    
}
