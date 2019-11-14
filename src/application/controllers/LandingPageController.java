package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class LandingPageController {
    @FXML
    private Pane customerListPane;
    private Pane createAccountPane;

    public LandingPageController(){
    }

    public void setInitialViewState(){
    }

    public void accountButtonClicked(MouseEvent mouseEvent) {
        System.out.println("cust"+customerListPane);
    }

    public void homeButtonClicked(MouseEvent mouseEvent) {
    }

    public void customersButtonClicked(MouseEvent mouseEvent) {
    }

    public void loanServicesButtonClicked(MouseEvent mouseEvent) {
    }

    public void moneyExchangeButtonClicked(MouseEvent mouseEvent) {
    }
}
