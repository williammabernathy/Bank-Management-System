package application.controllers;

import application.helpers.DynamicViews;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LandingPageController {
    @FXML
    private Pane interactivePane;
    @FXML
    private Pane dialogueAccountPane;
    @FXML
    private Pane dialogueMoneyExchangePane;
    @FXML
    private TextField searchTextField;




    public LandingPageController(){

    }

    public void accountButtonClicked(MouseEvent mouseEvent) throws IOException {
        dialogueMoneyExchangePane.setVisible(false);
        DynamicViews.loadScenes(dialogueAccountPane, "/dialogues/account");
        //accCont = new AccountController(searchTextField);
    }

    public void customersButtonClicked(MouseEvent mouseEvent) throws IOException {
        System.out.println("search"+searchTextField);
        //DynamicViews.loadScenes(dialogue_pane, "customers");
    }

    public void loanServicesButtonClicked(MouseEvent mouseEvent) throws IOException {
        //DynamicViews.loadScenes(dialogue_pane, "loan");
    }

    public void moneyExchangeButtonClicked(MouseEvent mouseEvent) throws IOException {
        dialogueAccountPane.setVisible(false);
        DynamicViews.loadScenes(dialogueMoneyExchangePane, "/dialogues/moneyExchange");

    }

    // ----------- From account.fxml-----------------
    public void accountsButtonClicked(MouseEvent mouseEvent) {
        System.out.println("search"+searchTextField);
        //searchTextField.setVisible(true);
    }

    //when create account button is clicked, display input fields in a pane below
    public void createAccountButtonClicked(ActionEvent event) {
        //System.out.println("Int"+interactivePane);
        //DynamicViews.loadScenes(interactivePane, "createAccountPage");
    }

    // search box will be focused and background will be blurred
    public void modifyAccountButtonClicked(MouseEvent mouseEvent) {
    }

    //same as accounts button except this one include a close button
    public void closeAccountButtonClicked(MouseEvent mouseEvent) {
    }

    //search box with account number, employee or id
    public void accountInformationButtonClicked(MouseEvent mouseEvent) {
    }
}

