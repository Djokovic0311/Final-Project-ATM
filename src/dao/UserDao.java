package dao;

import model.User;

import java.sql.*;

public class UserDao {


    // select user by id
    public User selectUserById(int id, String type) throws SQLException {
        // TODO:
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
        Statement stmt = con.createStatement();
        User user;
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM Person WHERE ID =" + id + "AND type=" + type + ";");
        while(rs.next()){
            user = new User(rs.getString(1), rs.getInt(0),rs.getString(2),rs.getString(3));
            return user;
        }
        return null;
    }
    public boolean insertIntoUser(int id, String name, String pwd) throws SQLException {
        // TODO:
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
        Statement stmt = con.createStatement();
        ResultSet rs;
        try {
            stmt.executeQuery("INSERT INTO Person (ID, userName, userPassword)" +
                    "VALUES ( " + id + ", " + name + ", " + pwd + ", " + "customer" +  ");");
        }catch(Exception E){
            return false;
        }

        return true;
    }

    private User parseRS(ResultSet rs) {

        // TODO:
        return new User();
    }

}
