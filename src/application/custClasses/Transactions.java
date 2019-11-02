package application.custClasses;

import java.util.Date;

public class Transactions
{
    private String tranID;
    private String accID;
    private String custID;
    private Date tranDate;
    private double tranAmount;

    Transactions()
    {

    }

    Transactions(String tranID, String accID, String custID, Date tranDate, double tranAmount)
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
}
