package model;

import java.sql.*;
public class User {
    protected String name;
    protected int ID;
    public enum Type {
        Manager, Customer
    }

    private Type type;
    private int password;


    void createConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException ex){
            System.out.println("Driver not found");
        }
    }

    public User(String name, int ID, String type, int password) {
        this.name = name;
        this.ID = ID;
        if type.equals("m") {
            this.type = Type.Manager;
        } else {
            this.type = Type.Customer;
        }
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public Type getType() {
        return type;
    }

    public boolean logIn(int password) {
        if (password == this.password) {
            return true;
        }
        return false;
    }


}