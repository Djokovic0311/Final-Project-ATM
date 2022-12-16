package dao;

import utils.ATMConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDao {
    static ATMConstant atmConstant = new ATMConstant();

    //connect to DB
    public static Connection connectToDb() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(atmConstant.getDBURL(), atmConstant.getDBUSERNAME(), atmConstant.getDBPWD());
            return conn;
        }catch(Exception e){
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public static void closeDbConnection(Connection connection) throws SQLException {
        if(connection!=null || !connection.isClosed()){
            connection.close();
        }
    }
}
