package application.custClasses;

import application.controllers.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Deposit {
    private String depositID;
    private String accountID;
    private String depositDate;
    private double amount;

    private static Connection connection;
    private static DateTimeFormatter form = DateTimeFormatter.ofPattern("MM/dd/YYYY");

    Deposit(String depositID, String accountID, LocalDate depositDate, double amount){
        this.depositID = depositID;
        this.accountID = accountID;
        this.depositDate = form.format(depositDate);
        this.amount = amount;
    }

    String getDepositID(){ return this.depositID;}
    String getAccountID(){ return this.accountID;}
    String getDepositDate(){return this.depositDate;}
    double getAmount(){return this.amount;}

    public void setAccountID(String accountID) {this.accountID = accountID;}
    public void setDepositID(String depositID) {this.depositID = depositID;}
    public void setDepositDate(LocalDate depositDate) {this.depositDate = form.format(depositDate);;}
    public void setAmount(double amount) {this.amount = amount;}


    public static int createNewDeposit(String accountID, LocalDate date, String amount){
        connection = ConnectDB.setupConnection();
        int count = 0;

        try{
            String query = String.format("INSERT INTO depositewithdraw(accountid, actiontype, dwDate, amount) VALUES(%s, '%c', STR_TO_DATE('%s', '%%m/%%d/%%Y'), %s);", accountID, 'd', form.format(date), amount);
            Statement st = connection.createStatement();
            int rs = st.executeUpdate(query);

            if(rs > 0)
            {
                count++;
            }
            st.close();
        }catch(Exception e)
        {
            Logger logger = Logger.getLogger(Employee.class.getName());
            logger.log(Level.SEVERE, "Failed to connect to database:", e);
        }
        return count;

    }
}
