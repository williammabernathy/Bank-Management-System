package application.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DynamicViews {
    private DynamicViews(){

    }
    public static void loadScenes(Pane dialogue_pane, String resource) throws IOException{
        Parent newScene = FXMLLoader.load(new DynamicViews().getClass().getResource("../../resources/view/"+resource+".fxml"));
        System.out.println("scene "+newScene);
        System.out.println("parent "+dialogue_pane);
        dialogue_pane.getChildren().add(newScene);
        dialogue_pane.setVisible(true);
    }
}
