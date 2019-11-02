package application.custClasses;

import java.util.Date;

public class Account
{
    private String accID;
    private String custID;
    private String accType;
    private Date creationDate;
    private double accAmount;

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
}
