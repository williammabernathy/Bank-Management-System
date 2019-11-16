package application.controllers;

import application.custClasses.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController
{
    @FXML private Button loginButton;
    @FXML private Button forgotPasswordButton;
    @FXML private TextField passwordTextField;
    @FXML private TextField useranameTextField;
    private static String loggedUser;

    public void handleLoginButtonAction(MouseEvent mouseEvent) throws IOException {
        // Authenticate user here
        // and display landing page if true
        String validateUser, validatePass;
        int verification = 0;

        //get username text
        validateUser = useranameTextField.getText();
        //get password text
        validatePass = passwordTextField.getText();
        //call validation method
        verification = Employee.validateLogin(validateUser, validatePass);

        //check if password matched
        if(verification == 1)
        {
            System.out.println(loggedUser);
            try
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../../resources/view/landingPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1251, 787);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("Bank Management System");
                stage.setScene(scene);
                stage.show();
            }
            catch (IOException e)
            {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create loading window", e);
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username or Password", ButtonType.OK);
            alert.showAndWait();
            useranameTextField.clear();
            passwordTextField.clear();
        }
    }

    public static void setUsername(String user)
    {
        loggedUser = user;
    }

    public static String getUsername()
    {
        return loggedUser;
    }
}
