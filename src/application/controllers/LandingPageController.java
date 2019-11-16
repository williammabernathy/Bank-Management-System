package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LandingPageController {
    @FXML private Button modifyButton;
    @FXML private Button selectButton;
    @FXML private Button selectCreateNewCustomer;
    @FXML private Button mainAccountButton;
    @FXML private Button mainCustomersButton;
    @FXML private Button mainLoanServices;
    @FXML private Button mainMoneyExchangeButton;
    @FXML private Button loggedUserActorButton;
    @FXML private Button createNewAccountButton;
    @FXML private Button selectAccountButton;
    @FXML private Button CancelButton;
    @FXML private Pane selectCustomerPane;
    @FXML private Pane createNewCustomerPane;
    @FXML private Pane modifyCustomerPane;
    @FXML private Pane createNewAccountPane;
    @FXML private Pane customerListPane;
    @FXML private Pane accountPane;
    @FXML private HBox searchBox;
    @FXML private TableView accountTableView;
    @FXML private TextField searchTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField lastNameTextField;

    public LandingPageController(){

    }

    public void accountButtonClicked(MouseEvent mouseEvent) {
        accountTableView.setDisable(true);
        searchBox.setVisible(true);
        searchTextField.setPromptText("Search for an account with account number or customer ID");
        displaySelectedView(accountPane);
    }

    public void customersButtonClicked(MouseEvent mouseEvent) {
        displaySelectedView(customerListPane);
        searchTextField.setPromptText("Search for an customer with name or customer ID");
        searchBox.setVisible(true);
    }

    public void cancelNewCustomerButtonClicked(MouseEvent mouseEvent) {searchBox.setVisible(true); displaySelectedView(customerListPane); searchTextField.setPromptText("Search for an customer with name or customer ID");}
    public void submitNewCustomerButtonClicked(MouseEvent mouseEvent) {searchBox.setVisible(false);/*get data entered by employee and update database */}

    public void modifyButtonClicked(MouseEvent mouseEvent) {
        searchBox.setVisible(false);
        displaySelectedView(modifyCustomerPane);
    }


    public void selectButtonClicked(MouseEvent mouseEvent) {
        searchBox.setVisible(false);
        displaySelectedView(selectCustomerPane);
    }
    public void createNewCustomerButtonClicked(MouseEvent mouseEvent) {
        searchBox.setVisible(false);
        displaySelectedView(createNewCustomerPane);
    }
    public void searchTextButtonClicked(MouseEvent mouseEvent){
        selectAccountButton.setDisable(false);
        accountTableView.setDisable(false);
    }


    public void displaySelectedView(Pane selectedView){
         Pane[] arrayOfViews = {customerListPane, createNewAccountPane, modifyCustomerPane, createNewCustomerPane,selectCustomerPane, accountPane};
        for (Pane p: arrayOfViews) {
            if (p != selectedView){
                p.setVisible(false);
            }
            else {
                p.setVisible(true);
            }
        }

    }


    public void createNewAccountButtonClicked(MouseEvent mouseEvent) {
        searchBox.setVisible(false);
        displaySelectedView(createNewAccountPane);
    }

    public void closeAccountButtonClicked(MouseEvent mouseEvent) {
    }

    public void submitNewAccountButtonClicked(MouseEvent mouseEvent) {
    }

    public void selectAccountButtonClicked(MouseEvent mouseEvent) {
    }

    public void loanServicesButtonClicked(MouseEvent mouseEvent) {
    }

    public void moneyExchangeButtonClicked(MouseEvent mouseEvent) {
    }

}
