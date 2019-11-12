package application.controllers;

import application.helpers.DynamicViews;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class landingPageController {
    @FXML
    private Pane dialogueAccountPane;
    @FXML
    private Pane dialogueMoneyExchangePane;


    public void accountButtonClicked(MouseEvent mouseEvent) throws IOException {
        //System.out.println("Testing landing page"+dialogue_pane);
        //dialogueMoneyExchangePane.setVisible(false);
        dialogueAccountPane.setVisible(true);
        dialogueMoneyExchangePane.setVisible(false);
        DynamicViews.loadScenes(dialogueAccountPane, "account");
    }

    public void customersButtonClicked(MouseEvent mouseEvent) throws IOException {
        //DynamicViews.loadScenes(dialogue_pane, "customers");
    }

    public void loanServicesButtonClicked(MouseEvent mouseEvent) throws IOException {
        //DynamicViews.loadScenes(dialogue_pane, "loan");
    }

    public void moneyExchangeButtonClicked(MouseEvent mouseEvent) throws IOException {
        //dialogue_pane.getChildren().add(null);
        dialogueAccountPane.setVisible(false);
        dialogueMoneyExchangePane.setVisible(true);
        DynamicViews.loadScenes(dialogueMoneyExchangePane, "moneyExchange");
    }

}
