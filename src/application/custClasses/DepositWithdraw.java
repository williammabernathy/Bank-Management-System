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


public class DepositWithdraw {
    private String dwID;
    private String accountID;
    private char type;
    private String dwDate;
    private double amount;

    private static Connection connection;
    private static DateTimeFormatter form = DateTimeFormatter.ofPattern("MM/dd/YYYY");

    DepositWithdraw(String depositID, String accountID, LocalDate depositDate, double amount, char type){
        this.dwID = depositID;
        this.accountID = accountID;
        this.dwDate = form.format(depositDate);
        this.amount = amount;
        this.type = type;
    }

    String getDepositID(){ return this.dwID;}
    String getAccountID(){ return this.accountID;}
    String getDepositDate(){return this.dwDate;}
    double getAmount(){return this.amount;}
    char getType(){return this.type;}

    public void setAccountID(String accountID) {this.accountID = accountID;}
    public void setDepositID(String depositID) {this.dwID = depositID;}
    public void setDepositDate(LocalDate depositDate) {this.dwDate = form.format(depositDate);;}
    public void setAmount(double amount) {this.amount = amount;}
    public void setType(char type){this.type = type;}


    public static int createNewDWEntry(String accountID, LocalDate date, String amount, char type){
        connection = ConnectDB.setupConnection();
        int count = 0;
        if (type == 'w')
            amount = "-"+amount;

        try{
            String query = String.format("INSERT INTO depositewithdraw(accountid, actiontype, dwDate, amount) VALUES(%s, '%c', STR_TO_DATE('%s', '%%m/%%d/%%Y'), %s);", accountID, type, form.format(date), amount);
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
