package dao;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao {


    // select user by id
    public User selectUserById(int id, String type) throws Exception {
        Connection conn = ConnectDao.connectToDb();
        Statement stmt = conn.createStatement();
        User user;
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM Person WHERE ID = " + id + " AND type=\'" + type + "\'"+";");
        while(rs.next()){
            user = new User(rs.getString(1), rs.getInt(0),rs.getString(2),rs.getString(3));
            return user;
        }
        return null;
    }
    public boolean insertIntoUser(int id, String name, String pwd) throws Exception {
        Connection conn = ConnectDao.connectToDb();
        Statement stmt = conn.createStatement();
        ResultSet rs;
        try {
            stmt.executeQuery("INSERT INTO Person (ID, userName, userPassword)" +
                    "VALUES ( " + id + ", " + name + ", " + pwd +  ");");
        }catch(Exception E){
            return false;
        }

        return true;
    }


}
