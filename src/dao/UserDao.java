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
                if(Objects.equals(type, "Customer")){
                    return new Customer(name,userID,userType,password);
                }
                else return new Manager(name,userID,userType,password);

            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    public boolean insertIntoUser(int id, String name, String pwd) throws Exception {
        Connection conn = ConnectDao.connectToDb();

        String query = "INSERT INTO Person (ID, userName, userPassword, userType)" +
                "VALUES (?,?,?,?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs;
        try {
            stmt.setInt(1,id);
            stmt.setString(2,name);
            stmt.setString(3,pwd);
            stmt.setString(4,"Customer");
            stmt.executeUpdate();
        }catch(Exception E){
            return false;
        }

        return true;
    }


}
