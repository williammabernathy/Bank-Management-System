package application.custClasses;

import application.controllers.ConnectDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Employee
{
    private String empID;
    private String Fname;
    private String Lname;
    private String username;
    private String password;
    private static Connection connection;

    Employee()
    {

    }

    Employee(String empID, String Fname, String Lname, String username, String password)
    {
        this.empID = empID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.username = username;
        this.password = password;
    }

    // getters
    String getEmpID()
    {
        return this.empID;
    }

    String getFname()
    {
        return this.Fname;
    }

    String getLname()
    {
        return this.Lname;
    }

    String getUsername()
    {
        return this.username;
    }

    String getPassword()
    {
        return this.password;
    }

    // setters
    void setEmpID(String empID)
    {
        this.empID = empID;
    }

    void setFname(String Fname)
    {
        this.Fname = Fname;
    }

    void setLname(String Lname)
    {
        this.Lname = Lname;
    }

    void setUsername(String username)
    {
        this.username = username;
    }

    void setPassword(String password)
    {
        this.password = password;
    }

    public static int validateLogin(String enteredUser, String enteredPass)
    {
        connection = ConnectDB.setupConnection();
        try
        {
            String query = "select * from employee where employeeUsername = '"+enteredUser+"' AND employeePassword = PASSWORD('"+enteredPass+"')";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            /*
            while (rs.next())
            {

            }
            */
            st.close();
            return 1;
        }
        catch(Exception e)
        {
            Logger logger = Logger.getLogger(Employee.class.getName());
            logger.log(Level.SEVERE, "Failed to connect to database:", e);
        }

        return 0;
    }
}
