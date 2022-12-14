package dao;

import model.User;

import java.sql.*;

public class UserDao {


    // select user by id
    public User selectUserById(int id, String type) throws SQLException {
        // TODO:
        try {
            String query;
            User user;
            ResultSet rs;
            query = "SELECT * FROM Person where ID = ? AND type = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, type);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString(1), rs.getInt(0), rs.getString(2), rs.getString(3));
                return user;
            }
            return null;
        } catch(Exception E){
            System.out.println("Error Occured");
            return null;
        }
    }
    public boolean insertIntoUser(int id, String name, String pwd) throws SQLException {
        // TODO:

        try {
            String query;
            User user;
            ResultSet rs;
            query = "INSERT INTO Person (ID, userName, userPassword) VALUES (?, ?,?, 'customer');";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, pwd);
            rs = stmt.executeQuery();
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
