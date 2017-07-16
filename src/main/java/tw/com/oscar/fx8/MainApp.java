package tw.com.oscar.fx8;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.dialog.Dialogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.oscar.fx8.model.Person;
import tw.com.oscar.fx8.model.PersonListWrapper;
import tw.com.oscar.fx8.view.BirthdayStatisticsController;
import tw.com.oscar.fx8.view.MainViewController;
import tw.com.oscar.fx8.view.PersonEditDialogController;
import tw.com.oscar.fx8.view.RootLayoutController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.prefs.Preferences;

public class MainApp extends Application {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);
    private static final EntityManagerFactory emFactory;
    
    static {
        emFactory = Persistence.createEntityManagerFactory("OscarPU");
    }
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personList = FXCollections.observableArrayList();
    
    public MainApp() {
        personList.add(new Person("Oscar", "Wei"));
        personList.add(new Person("Amy", "Hung"));
        personList.add(new Person("Sunny", "Wei"));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }
    
    @Override
    public void init() throws Exception {
        LOGGER.info("[Enter] MainApp.init");
        super.init();
        setUserAgentStylesheet(STYLESHEET_MODENA); // JavaFX 8 default theme
        // setUserAgentStylesheet(STYLESHEET_CASPIAN); // JavaFX 2 default theme
        LOGGER.info("[Exit] MainApp.init");
    }
    
    @Override
    public void stop() throws Exception {
        LOGGER.info("[Enter] MainApp.stop");
        super.stop();
        LOGGER.info("[Exit] MainApp.stop");
    }
    
    @Override
    public void start(Stage primaryStage) {
        LOGGER.info("[Enter] MainApp.start");
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Oscar JavaFX 8 Sample");
        this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
        initRootLayout();
        showMainView();
        LOGGER.info("[Exit] MainApp.start");
    }
    
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
    
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            
            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");
            
            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }
    
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            
            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
            
            personList.clear();
            personList.addAll(wrapper.getPersons());
            
            // Save the file path to the registry.
            setPersonFilePath(file);
            
        } catch (Exception e) { // catches ANY exception
            // Dialogs.create().title("Error").masthead("Could not load data
            // from file:\n" + file.getPath())
            // .showException(e);
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String exceptionText = sw.toString();
            
            Label label = new Label("The exception stacktrace was:");
            
            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);
            
            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);
            
            alert.getDialogPane().setExpandableContent(expContent);
            alert.showAndWait();
        }
    }
    
    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personList);
            
            m.marshal(wrapper, file);
            
            setPersonFilePath(file);
        } catch (Exception e) {
            Dialogs.create().title("Error").masthead("Could not save data to file:\n" + file.getPath())
                    .showException(e);
        }
    }
    
    public void showBirthdayStatistics() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(personList);
            
            dialogStage.show();
            
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    public boolean showPersonEditDialog(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = loader.load();
            
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
    
    public void print(Node node) {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT,
                Printer.MarginType.DEFAULT);
        PrinterJob job = PrinterJob.createPrinterJob();
        if (null != job) {
            boolean success = job.printPage(node);
            
            if (success) {
                job.endJob();
            }
        }
    }
    
    private void showMainView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainView.fxml"));
            AnchorPane mainView = loader.load();
            
            rootLayout.setCenter(mainView);
            MainViewController controller = loader.getController();
            controller.setMainApp(this);
            
            // fade();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    private void initRootLayout() {
        try {
            Parameters params = getParameters();
            String[] paramNames = params.getRaw().stream().toArray(String[]::new);
            LOGGER.info(Arrays.toString(paramNames));
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();
            
            Scene scene = new Scene(rootLayout, Color.FLORALWHITE);
            /** can add more style sheet **/
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
            primaryStage.setFullScreen(true);
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(800);
            primaryStage.setScene(scene);
            
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            
            primaryStage.show();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public ObservableList<Person> getPersonList() {
        return personList;
    }
    
    @SuppressWarnings("unused")
    private void fade() {
        Rectangle rect = new Rectangle(100, 40, 100, 100);
        rect.setArcHeight(50);
        rect.setArcWidth(50);
        rect.setFill(Color.VIOLET);
        
        FadeTransition ft = new FadeTransition(Duration.millis(3000), rect);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(4);
        ft.setAutoReverse(true);
        
        ft.play();
    }
}
