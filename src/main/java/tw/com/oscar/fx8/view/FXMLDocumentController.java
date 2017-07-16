package tw.com.oscar.fx8.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tw.com.oscar.fx8.SplashLoading;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {
    
    public static AnchorPane rootP;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane root;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!SplashLoading.isSplashLoaded) {
            loadSplahsScreen();
        }
        
        rootP = root;
        
        try {
            VBox box = FXMLLoader.load(getClass().getResource("view/SidePaneContent.fxml"));
            drawer.setSidePane(box);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();
            
            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
    }
    
    private void loadSplahsScreen() {
        try {
            SplashLoading.isSplashLoaded = true;
            
            StackPane pane = FXMLLoader.load(getClass().getResource("view/Welcome.fxml"));
            root.getChildren().addAll(pane);
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            
            fadeIn.play();
            
            fadeIn.setOnFinished(e -> {
                fadeOut.play();
            });
            
            fadeOut.setOnFinished(e -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource("view/FXMLDocument.fxml"));
                    root.getChildren().addAll(parentContent);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
