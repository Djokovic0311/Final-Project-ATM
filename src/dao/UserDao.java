package dao;

import model.User;

import java.sql.ResultSet;

public class UserDao {


    // select user by id
    public User selectUserById(int id) {
        // TODO:
        return null;
    }
    public boolean insertIntoUser(int id, String name, String pwd) {
        // TODO:
        return true;
    }

    private User parseRS(ResultSet rs) {

        // TODO:
        return new User();
    }

}
