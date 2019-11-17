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

public class Account
{
    private String accID;
    private String custID;
    private String accType;
    private Date creationDate;
    private double accAmount;
    private static Connection connection;

    Account()
    {

    }

    Account(String accID, String custID, String accType, Date creationDate, double accAmount)
    {
        this.accID = accID;
        this.custID = custID;
        this.accType = accType;
        this.creationDate = creationDate;
        this.accAmount = accAmount;
    }

    // getters
    String getAccID()
    {
        return this.accID;
    }

    String getCustID()
    {
        return this.custID;
    }

    String getAccType()
    {
        return this.accType;
    }

    Date getCreationDate()
    {
        return this.creationDate;
    }

    double getAccAmount()
    {
        return this.accAmount;
    }

    // setters
    void setAccID(String accID)
    {
        this.accID = accID;
    }

    void setCustID(String custID)
    {
        this.custID = custID;
    }

    void setAccType(String accType)
    {
        this.accType = accType;
    }

    void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    void setAccAmount(double accAmount)
    {
        this.accAmount = accAmount;
    }

    public String toString()
    {
        return ""+accType+": "+accAmount+"";
    }

    public static ObservableList<Account> getAllAccounts(String custID)
    {
        connection = ConnectDB.setupConnection();
        ObservableList<Account> allAccounts = FXCollections.observableArrayList();

        try
        {
            String query = "select * from customeraccounts where customerID = '"+custID+"'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                String accID = rs.getString("accountID");
                String customID = rs.getString("customerID");
                String type = rs.getString("accountType");
                Date creation = rs.getDate("creationDate");
                double amount = rs.getDouble("amount");

                Account acc = new Account(accID, customID, type, creation, amount);

                allAccounts.add(acc);
            }
            st.close();
        }
        catch(Exception e)
        {
            Logger logger = Logger.getLogger(Employee.class.getName());
            logger.log(Level.SEVERE, "Failed to connect to database:", e);
        }

        return allAccounts;
    }

    public static int createNewAccount(String custID, String accountType, LocalDate creationDate, String amount)
    {
        connection = ConnectDB.setupConnection();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        int count = 0;

        double convAmount = Double.parseDouble(amount);

        try
        {
            String query = "INSERT INTO CustomerAccounts (customerID, accountType, creationDate, amount) " +
                    "VALUES('"+custID+"', '"+accountType+"', STR_TO_DATE('"+form.format(creationDate)+"', '%m/%d/%Y'), "+convAmount+");";
            Statement st = connection.createStatement();
            int rs = st.executeUpdate(query);

            if(rs > 0)
            {
                count++;
            }
            st.close();
        }
        catch(Exception e)
        {
            Logger logger = Logger.getLogger(Employee.class.getName());
            logger.log(Level.SEVERE, "Failed to connect to database:", e);
        }

        return count;
    }
}
