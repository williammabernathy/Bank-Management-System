package application.custClasses;

import application.controllers.ConnectDB;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transfer
{
    private String tranID;
    private String accID;
    private String custID;
    private Date tranDate;
    private double tranAmount;

    private static Connection connection;
    private static DateTimeFormatter form = DateTimeFormatter.ofPattern("MM/dd/YYYY");

    Transfer()
    {

    }

    Transfer(String tranID, String accID, String custID, Date tranDate, double tranAmount)
    {
        this.tranID = tranID;
        this.accID = accID;
        this.custID = custID;
        this.tranDate = tranDate;
        this.tranAmount = tranAmount;
    }

    // getters
    String getTranID()
    {
        return this.tranID;
    }

    String getAccID()
    {
        return this.accID;
    }

    String getCustID()
    {
        return this.custID;
    }

    Date getTranDate()
    {
        return this.tranDate;
    }

    double getTranAmount()
    {
        return this.tranAmount;
    }

    // setters
    void setTranID(String tranID)
    {
        this.tranID = tranID;
    }

    void setAccID(String accID)
    {
        this.accID = accID;
    }

    void setCustID(String custID)
    {
        this.custID = custID;
    }

    void setTranDate(Date tranDate)
    {
        this.tranDate = tranDate;
    }

    void setTranAmount(double tranAmount)
    {
        this.tranAmount = tranAmount;
    }

    public static int newTransfer(String fromID, String toID, LocalDate date, String amount){
        connection = ConnectDB.setupConnection();
        int count = 0;
        double doubleAmount = Double.parseDouble(amount);

        try{
            String query = String.format("INSERT INTO Transfer (senderAccountID, receiverAccountID, transferdate, transferAmount) VALUES(%s, %s, STR_TO_DATE('%s', '%%m/%%d/%%Y'), %.2f);", fromID, toID, form.format(date), doubleAmount);
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
