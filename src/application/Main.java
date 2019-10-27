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

        //System.out.println(connection);

        Parent root = FXMLLoader.load(getClass().getResource("../resources/view/login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
