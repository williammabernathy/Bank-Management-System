package application.controllers;

import application.custClasses.Account;
import application.custClasses.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    @FXML private Label displaySelectedCustomer;
    @FXML private TextField searchTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField lastNameTextField;
    @FXML private ListView customerListView;
    @FXML private ListView customerAccountListView;
    @FXML private ListView allAccountsListView;

    private static ObservableList<Customer> allCustomers;
    private static ObservableList<Account> allAccounts;

    public LandingPageController()
    {

    }

    //all elements to be called when scene is first loaded should be here
    public void initialize()
    {
        //set the customer listview selection type to only allow a single customer to be selected
        customerListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //update logout button with what user is currently logged in
        loggedUserActorButton.setText("Log out of: "+ LoginController.getUsername());

        //get all customers from database
        allCustomers = Customer.getAllCustomers();

        //fill the listview with all customers
        customerListView.getItems().addAll(allCustomers);
    }

    // select customer button
    public void selectButtonClicked(ActionEvent event)
    {
        Customer item = (Customer) customerListView.getSelectionModel().getSelectedItem();

        //if nothing is selected
        if(item == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a customer.", ButtonType.OK);
            alert.showAndWait();
        }
        //if a customer IS selected
        else
        {
            //get the selected Customer object
            item = (Customer) customerListView.getSelectionModel().getSelectedItem();

            //set the text to display what customer we are working with
            displaySelectedCustomer.setText("Selected Customer: " + item.getFname());

            //get all accounts related to the selected customers
            allAccounts = Account.getAllAccounts(item.getCustID());

            //fill listview with accounts under that customer
            allAccountsListView.getItems().addAll(allAccounts);
        }
    }

    //modify customer information from customer tab
    public void modifyButtonClicked(MouseEvent mouseEvent) {
        searchBox.setVisible(false);
        displaySelectedView(modifyCustomerPane);
    }

    //create a new customer from the customer tab
    public void createNewCustomerButtonClicked(MouseEvent mouseEvent) {
        searchBox.setVisible(false);
        displaySelectedView(createNewCustomerPane);
    }

    //log out button
    public void loggedUserActorClick(ActionEvent event)
    {
        //get the current stage
        Stage stage = (Stage) loggedUserActorButton.getScene().getWindow();

        //close it to return to log in screen
        stage.close();
    }

    public void accountButtonClicked(MouseEvent mouseEvent) {
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

    public void searchTextButtonClicked(MouseEvent mouseEvent){
        selectAccountButton.setDisable(false);
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
