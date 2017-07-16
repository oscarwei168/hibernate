package tw.com.oscar.fx8.view;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import org.controlsfx.dialog.Dialogs;
import tw.com.oscar.fx8.MainApp;

import java.io.File;

public class RootLayoutController {
    
    private MainApp mainApp;
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private void handleNew() {
        mainApp.getPersonList().clear();
        mainApp.setPersonFilePath(null);
    }
    
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        
        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        
        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }
    
    @FXML
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.setPersonFilePath(personFile);
        } else {
            handleSaveAs();
        }
    }
    
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();
        
        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        
        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }
    
    @FXML
    private void handleAbout() {
        Dialogs.create().title("AddressApp").masthead("About")
                .message("Author: Marco Jakob\nWebsite: http://code.makery.ch").showInformation();
    }
    
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    @FXML
    private void handleShowBirthdayStatistics() {
        mainApp.showBirthdayStatistics();
    }
    
}
