package application.custClasses;

import java.util.Date;

public class Customer
{
    private String custID;
    private String Fname;
    private String Lname;
    private String address;
    private String phoneNum;
    private Date birthday;
    private int age;

    Customer()
    {

    }

    Customer(String custID, String Fname, String Lname, String address, String phoneNum, Date birthday, int age)
    {
        this.custID = custID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.address = address;
        this.phoneNum = phoneNum;
        this.birthday = birthday;
        this.age = age;
    }

    // getters
    String getCustID()
    {
        return this.custID;
    }

    String getFname()
    {
        return this.Fname;
    }

    String getLname()
    {
        return this.Lname;
    }

    String getAddress()
    {
        return this.address;
    }

    String getPhoneNum()
    {
        return this.phoneNum;
    }

    Date getBirthday()
    {
        return this.birthday;
    }

    int getAge()
    {
        return this.age;
    }

    // setters
    void setCustID(String custID)
    {
        this.custID = custID;
    }

    void setFname(String Fname)
    {
        this.Fname = Fname;
    }

    void setLname(String Lname)
    {
        this.Lname = Lname;
    }

    void setAddress(String address)
    {
        this.address = address;
    }

    void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    void setAge(int age)
    {
        this.age = age;
    }
}
