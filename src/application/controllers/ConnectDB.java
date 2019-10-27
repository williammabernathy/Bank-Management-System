package application.controllers;

import java.sql.Connection ;
import java.sql.DriverManager ;

public class ConnectDB
{
    private static Connection connection;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String dbConnection = "jdbc:mysql://127.0.0.1:3306/bankmanagementsystem";
    private static final String username = "root";
    private static final String password = "root";

    public ConnectDB()
    {

    }

    public static Connection setupConnection()
    {

        try
        {
            Class.forName(driver);
        }
        catch (Exception e)
        {
            //System.out.println("catch driver");
        }

        try
        {
            connection = DriverManager.getConnection(dbConnection, username, password);
            return connection;
        }
        catch (Exception e)
        {
            //System.out.println("catch connection");
        }

        return connection;
    }
}
