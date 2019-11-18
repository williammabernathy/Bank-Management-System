package application.custClasses;

import application.controllers.ConnectDB;
import application.controllers.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer
{
    private String custID;
    private String Fname;
    private String Lname;
    private String address;
    private String phoneNum;
    private Date birthday;
    private int age;
    private static Connection connection;

    Customer()
    {

    }

    Customer(String custID, String Fname, String Lname, String address, String phoneNum, Date birthday)
    {
        this.custID = custID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.address = address;
        this.phoneNum = phoneNum;
        this.birthday = birthday;
    }

    // getters
    public String getCustID()
    {
        return this.custID;
    }

    public String getFname()
    {
        return this.Fname;
    }

    public String getLname()
    {
        return this.Lname;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getPhoneNum()
    {
        return this.phoneNum;
    }

    public Date getBirthday()
    {
        return this.birthday;
    }

    public int getAge()
    {
        return this.age;
    }

    // setters
    public void setCustID(String custID)
    {
        this.custID = custID;
    }

    public void setFname(String Fname)
    {
        this.Fname = Fname;
    }

    public void setLname(String Lname)
    {
        this.Lname = Lname;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String toString()
    {
        return ""+Fname+" "+Lname+"";
    }

    public static ObservableList<Customer> getAllCustomers()
    {
        connection = ConnectDB.setupConnection();
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        try
        {
            String query = "select * from customer";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            //String custID, String Fname, String Lname, String address, String phoneNum, Date birthday, int age
            while (rs.next())
            {
                String custID = rs.getString("customerID");
                String Fname = rs.getString("customerFname");
                String Lname = rs.getString("customerLname");
                String address = rs.getString("customerAddress");
                String phoneNum = rs.getString("customerPhone");
                Date birthday = rs.getDate("customerBirthday");

                Customer cust = new Customer(custID, Fname, Lname, address, phoneNum, birthday);

                allCustomers.add(cust);
            }
            st.close();
        }
        catch(Exception e)
        {
            Logger logger = Logger.getLogger(Employee.class.getName());
            logger.log(Level.SEVERE, "Failed to connect to database:", e);
        }

        return allCustomers;
    }

    public static int createNewCustomer(String firstName, String lastName, String phoneNum, LocalDate birthday, String address, String city, String state, String zip)
    {
        connection = ConnectDB.setupConnection();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        int count = 0;

        try
        {
            String query = "INSERT INTO Customer (customerFname, customerLname, customerAddress, customerPhone, customerBirthday) " +
                    "VALUES('"+firstName+"', '"+lastName+"', '"+address+"', '"+phoneNum+"', STR_TO_DATE('"+form.format(birthday)+"', '%m/%d/%Y'));";
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
