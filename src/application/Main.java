package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.controllers.ConnectDB;

import java.sql.Connection;

public class Main extends Application
{
    private static Connection connection;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        connection = ConnectDB.setupConnection();

        System.out.println(connection);

        Parent root = FXMLLoader.load(getClass().getResource("../resources/view/loginPage.fxml"));
        primaryStage.setTitle("Bank Management System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
