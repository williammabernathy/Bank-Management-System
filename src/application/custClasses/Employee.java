package application.custClasses;

public class Employee
{
    private String empID;
    private String Fname;
    private String Lname;
    private String username;
    private String password;

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
}
