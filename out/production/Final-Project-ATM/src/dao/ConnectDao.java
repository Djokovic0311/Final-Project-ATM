package dao;

import utils.ATMConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDao {
    static ATMConstant atmConstant = new ATMConstant();

    //connect to DB
    public static Connection connectToDb() throws Exception {
        System.out.println("Connecting to DB");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(atmConstant.getDBURL(), atmConstant.getDBUSERNAME(), atmConstant.getDBPWD());
            System.out.println("Connected Succesfully!");
            return conn;
        }catch(Exception e){
            System.out.println("Failed to connect!");
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public static void closeDbConnection(Connection connection) throws SQLException {
        if(connection!=null || !connection.isClosed()){
            connection.close();
        }else {
            System.out.println("Connection already closed");
        }

        System.out.println("Connection closed");
    }
}
