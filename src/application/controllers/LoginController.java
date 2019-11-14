package application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    public void handleLoginButtonAction(MouseEvent mouseEvent){
        //Authenticate user here
        // and display landing page if true
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../resources/view/landingPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1054, 702);
            Stage stage = new Stage();
            stage.setTitle("Bank Management System");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create loading window", e);
        }
    }
}
