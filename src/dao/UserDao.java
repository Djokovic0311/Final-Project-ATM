package dao;

import model.Customer;
import model.Manager;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class UserDao {


    // select user by id
    public Object selectUserById(int id, String type) {
        System.out.println("The input id is: " + id);
        try {
            Connection conn = ConnectDao.connectToDb();
            String query = "SELECT * FROM Person WHERE ID = ? AND userType = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, type);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                String name = rs.getString(2);
                int userID = rs.getInt(1);
                String userType = rs.getString(4);
                String password = rs.getString(3);
                System.out.println("name is" + name);
                System.out.println("ID is" + userID);
                System.out.println("type is" + userType);
                System.out.println("password is" + password);
                if(Objects.equals(type, "Customer")){
                    return new Customer(name,userID,userType,password);
                }
                else return new Manager(name,userID,userType,password);

            }
            System.out.println("User not found");
            return null;
        } catch (Exception e) {
            System.out.println("Exception");
            return null;
        }
    }
    public boolean insertIntoUser(int id, String name, String pwd) throws Exception {
        if (selectUserById(id, "Customer") != null) {
            return false;
        }
        Connection conn = ConnectDao.connectToDb();
        String query = "INSERT INTO Person (ID, userName, userPassword, userType)" +
                "VALUES (?,?,?,?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        try {
            stmt.setInt(1,id);
            stmt.setString(2,name);
            stmt.setString(3,pwd);
            stmt.setString(4,"Customer");
            stmt.executeUpdate();
        }catch (Exception e) { return false; }
        return true;
    }


}
