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

import javax.xml.soap.Text;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class LandingPageController {
    // general/main pane elements
    @FXML private TextField searchTextField;
    @FXML private Label displaySelectedCustomer;
    @FXML private Button mainAccountButton;
    @FXML private Button mainCustomersButton;
    @FXML private Button mainLoanServices;
    @FXML private Button mainMoneyExchangeButton;
    @FXML private Button loggedUserActorButton;
    @FXML private HBox searchBox;

    // customer search pane and children
    @FXML private Pane selectCustomerPane;
    @FXML private Pane createNewCustomerPane;
    @FXML private Pane createNewAccountPane;
    @FXML private Pane customerListPane;
    @FXML private ListView customerListView;

    // create customer fields
    @FXML private Button CancelButtonClicked;
    @FXML private Button submitNewCustomerButton;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField phoneTextField;
    @FXML private DatePicker datePickerField;
    @FXML private TextField addressTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField stateTextField;
    @FXML private TextField zipcodeTextField;

    // account pane and children
    @FXML private Pane accountPane;
    @FXML private ListView allAccountsListView;
    @FXML private Button selectAccountButton;
    @FXML private Button submitNewAccountButton;
    @FXML private Button cancelNewAccountButton;
    @FXML private TextField accountCustID;
    @FXML private ComboBox accountTypeField;
    @FXML private TextField accountHolderName;
    @FXML private TextField accountBalanceField;

    // global variables
    private static ObservableList<Customer> allCustomers;
    private static ObservableList<Account> allAccounts;
    Customer selectedCust;

    //modify customer pane and children
    @FXML private Pane modifyCustomerPane;
    @FXML private TextField modifiedFirstNameTextField;
    @FXML private TextField modifiedLastNameTextField;
    @FXML private TextField modifiedPhoneTextField;
    @FXML private DatePicker modifedDatePickerField;
    @FXML private TextField modifiedAddressTextField;
    @FXML private TextField modifiedCityTextField;
    @FXML private TextField mofifiedStateTextField;
    @FXML private TextField modifiedZipcodeTextField;
    @FXML private Button submitModifiedButton;
    @FXML private Button cancelModifiedButtonClicked;

    //Money Exchange
    @FXML private TextArea moneyExchangeTextArea;
    @FXML private Pane moneyExchangePane;

    //Withdraw Money Pane children
    @FXML private TextField withdrawAmountTextField;
    @FXML private ComboBox withdrawAccountTypeComboBox;
    @FXML private Button submitWithdrawButton;

    //Deposit Money Pane children
    @FXML private TextField depositAmountTextField;
    @FXML private ComboBox depositAccountTypeComboBox;
    @FXML private Button submitDepositButton;

    //Transfer Money Pane children
    @FXML private TextField transferAmountTextField;
    @FXML private TextField fromTransferMoneyTextField;
    @FXML private TextField toTransferMoneyTextField;
    @FXML private Button submitTransferButton;

    //Loan Services Pane and children
    @FXML private Pane loanServicesPane;
    @FXML private ListView loanCustomerAccountListView;
    @FXML private TextField loanSearchCustomerTextField;
    @FXML private Button loanSearchCustomerButton;
    @FXML private Button loanSubmitButton;
    @FXML private Button loanCancelButton;


    public LandingPageController()
    {

    }

    //all elements to be called when scene is first loaded should be here
    public void initialize()
    {
        //fill combobox in account creation pane with values
        accountTypeField.getItems().addAll("Checking", "Savings", "Loan");

        //disable the sidebar buttons until customer is selected
        mainAccountButton.setDisable(true);
        mainLoanServices.setDisable(true);
        mainMoneyExchangeButton.setDisable(true);

        //set the customer listview selection type to only allow a single customer to be selected
        customerListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //update logout button with what user is currently logged in
        loggedUserActorButton.setText("Log out of: "+ LoginController.getUsername());

        //get all customers from database
        allCustomers = Customer.getAllCustomers();

        //fill the listview with all customers
        customerListView.getItems().addAll(allCustomers);
    }

    // refresh all contents of the customer listview
    public void refreshCustomerListView()
    {
        //clear the current items in the listview
        customerListView.getItems().clear();

        //set the customer listview selection type to only allow a single customer to be selected
        customerListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //get all customers from database
        allCustomers = Customer.getAllCustomers();

        //fill the listview with all customers
        customerListView.getItems().addAll(allCustomers);
    }

    // refresh all contents of the customer listview
    public void refreshAccountListView()
    {
        //clear the current items in the listview
        allAccountsListView.getItems().clear();

        //get all accounts related to the selected customers
        allAccounts = Account.getAllAccounts(selectedCust.getCustID());

        //fill listview with accounts under that customer
        allAccountsListView.getItems().addAll(allAccounts);
    }

    /*
     *
     * General/Main Pane Functions
     *
     */
    //log out button
    public void loggedUserActorClick(ActionEvent event)
    {
        //get the current stage
        Stage stage = (Stage) loggedUserActorButton.getScene().getWindow();

        //close it to return to log in screen
        stage.close();
    }

    //account sidebar button
    public void accountButtonClicked(MouseEvent mouseEvent)
    {
        searchBox.setVisible(true);
        searchTextField.setPromptText("Search for an account with account number or customer ID");
        displaySelectedView(accountPane);

        accountCustID.setText(selectedCust.getCustID());
        accountHolderName.setText(selectedCust.getLname() +" "+selectedCust.getFname());
    }

    //customer side bar button
    public void customersButtonClicked(MouseEvent mouseEvent)
    {
        displaySelectedView(customerListPane);
        searchTextField.setPromptText("Search for an customer with name or customer ID");
        searchBox.setVisible(true);
    }

    public void searchTextButtonClicked(MouseEvent mouseEvent)
    {
        selectAccountButton.setDisable(false);
    }

    /*
     *
     * Customer List Pane (Home)
     *
     */
    // select customer button
    public void selectButtonClicked(ActionEvent event)
    {
        allAccountsListView.getItems().clear();

        selectedCust = (Customer) customerListView.getSelectionModel().getSelectedItem();

        //if nothing is selected
        if(selectedCust == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a customer.", ButtonType.OK);
            alert.showAndWait();
        }
        //if a customer IS selected
        else
        {
            //get the selected Customer object
            selectedCust = (Customer) customerListView.getSelectionModel().getSelectedItem();

            //set the text to display what customer we are working with
            displaySelectedCustomer.setText("Selected Customer: " + selectedCust.getFname());

            //get all accounts related to the selected customers
            allAccounts = Account.getAllAccounts(selectedCust.getCustID());

            //fill listview with accounts under that customer
            allAccountsListView.getItems().addAll(allAccounts);

            mainLoanServices.setDisable(false);
            mainMoneyExchangeButton.setDisable(false);
            mainAccountButton.setDisable(false);
        }
    }

    // modify customer information from customer tab
    public void modifyButtonClicked(MouseEvent mouseEvent) {
        searchBox.setVisible(false);
        displaySelectedView(modifyCustomerPane);
    }

    // create a new customer from the customer tab
    public void createNewCustomerButtonClicked(MouseEvent mouseEvent) {
        searchBox.setVisible(false);
        displaySelectedView(createNewCustomerPane);
    }

    /*
    *
    * Create Customer Pane
    *
    */
    // submit a newly created customer from the new/create customer tab
    public void submitNewCustomerButtonClicked(ActionEvent event)
    {
        // check that all input fields have a value
        if(firstNameTextField.getText() == null || lastNameTextField.getText() == null || phoneTextField.getText() == null || datePickerField.getValue() == null ||
                addressTextField.getText() == null || cityTextField.getText() == null || stateTextField.getText() == null || zipcodeTextField.getText() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be entered!", ButtonType.OK);
            alert.showAndWait();
        }
        else
        {
            // if all inputs are present, get the values to pass
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String phoneNum = phoneTextField.getText();
            LocalDate birthday = datePickerField.getValue();
            String address = addressTextField.getText();
            String city = cityTextField.getText();
            String state = stateTextField.getText();
            String zip = zipcodeTextField.getText();

            // insert new customer into database
            int check = Customer.createNewCustomer(firstName, lastName, phoneNum, birthday, address, city, state, zip);

            // if check > 1, insert was successful
            if(check > 0)
            {
                // display success notification
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "New customer created!", ButtonType.OK);
                alert.showAndWait();

                // clear all fields
                firstNameTextField.clear();
                lastNameTextField.clear();
                phoneTextField.clear();
                datePickerField.setValue(null);
                addressTextField.clear();
                cityTextField.clear();
                stateTextField.clear();
                zipcodeTextField.clear();

                //refresh list view
                refreshCustomerListView();

                //return to customer list
                displaySelectedView(customerListPane);
            }
            // something went wrong with insert
            else
            {
                // display error message
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong while trying to insert a new customer", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    // cancel creating new customer
    public void cancelNewCustomerButtonClicked(ActionEvent event)
    {
        // clear all fields
        firstNameTextField.clear();
        lastNameTextField.clear();
        phoneTextField.clear();
        datePickerField.setValue(null);
        addressTextField.clear();
        cityTextField.clear();
        stateTextField.clear();
        zipcodeTextField.clear();

        //refresh list view
        refreshCustomerListView();

        //return to customer list
        displaySelectedView(customerListPane);
    }

    public void cancelNewCustomerButtonClicked(MouseEvent mouseEvent)
    {
        searchBox.setVisible(true); displaySelectedView(customerListPane);
        searchTextField.setPromptText("Search for an customer with name or customer ID");
    }

    public void submitNewCustomerButtonClicked(MouseEvent mouseEvent)
    {
        searchBox.setVisible(false); /*get data entered by employee and update database */
    }

    /*
    *
    * Account List Pane
    *
    */

    public void createNewAccountButtonClicked(MouseEvent mouseEvent)
    {
        searchBox.setVisible(false);
        displaySelectedView(createNewAccountPane);
    }

    public void closeAccountButtonClicked(MouseEvent mouseEvent)
    {

    }

    /*
    *
    * Creating New Account
    *
    */
    //submit the new account
    public void submitNewAccount(ActionEvent mouseEvent)
    {
        //check that balance is a double/number
        try
        {
            Double.parseDouble(accountBalanceField.getText());
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Balance must be a number!", ButtonType.OK);
            alert.showAndWait();

        }
        //check that all fields have data
        if(accountBalanceField.getText() == null || accountTypeField.getValue() == null)
        {
            // display error message
            Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be entered!", ButtonType.OK);
            alert.showAndWait();
        }
        else
        {
            //get entered values
            String customerID = accountCustID.getText();
            String accountType = "";
            if(accountTypeField.getValue() == "Savings")
            {
                accountType = "S";
            }
            else if(accountTypeField.getValue() == "Checking")
            {
                accountType = "C";
            }
            else if(accountTypeField.getValue() == "Loan")
            {
                accountType = "L";
            }
            LocalDate creationDate = java.time.LocalDate.now();
            String amount = accountBalanceField.getText();

            //query database with entered information
            int check = Account.createNewAccount(customerID, accountType, creationDate, amount);

            // if check > 1, insert was successful
            if(check > 0)
            {
                // display success notification
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "New Account created!", ButtonType.OK);
                alert.showAndWait();

                // clear all fields
                accountTypeField.valueProperty().set(null);
                accountBalanceField.clear();

                //refresh list view
                refreshAccountListView();

                //return to customer list
                displaySelectedView(accountPane);
            }
            // something went wrong with insert
            else
            {
                // display error message
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong while trying to create a new account!", ButtonType.OK);
                alert.showAndWait();
            }

        }
    }

    //cancel creating the account
    public void cancelNewAccount(ActionEvent mouseEvent)
    {
        accountTypeField.valueProperty().set(null);
        accountBalanceField.clear();

        //refresh list view
        refreshAccountListView();

        //return to customer list
        displaySelectedView(accountPane);
    }

    /*
    *
    * Loan Services Pane
    *
    */
    public void loanServicesButtonClicked(ActionEvent mouseEvent) {
        searchBox.setVisible(false);
        displaySelectedView(loanServicesPane);
    }
    /*
     *
     * Money Services Pane
     *
     */
            public void submitWithdrawButtonClicked(ActionEvent actionEvent) {
            }

            public void submitDepositButtonClicked(ActionEvent actionEvent) {
            }

            public void submitTransferButtonClicked(ActionEvent actionEvent) {

            }

    /*
     *
     * Other
     *
     */

    public void displaySelectedView(Pane selectedView) {
        Pane[] arrayOfViews = {customerListPane, createNewAccountPane, modifyCustomerPane, createNewCustomerPane, selectCustomerPane, accountPane, moneyExchangePane, loanServicesPane};
        for (Pane p : arrayOfViews) {
            if (p != selectedView) {
                p.setVisible(false);
                accountBalanceField.clear();
                //return;
            }
            else {
                p.setVisible(true);
            }
        }
    }

    public void moneyExchangeButtonClicked(ActionEvent actionEvent) {
        displaySelectedView(moneyExchangePane);
    }

    public void submitNewAccountButtonClicked(MouseEvent mouseEvent) {
    }
}
