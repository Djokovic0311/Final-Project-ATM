package model;

import java.util.Objects;

public class User {
    private String name;
    private int ID;

    public User() {

    }

    public enum Type {
        Manager, Customer
    }
    private Type type;
    private String password;

    public User(String name, int ID, String type, String password) {
        this.name = name;
        this.ID = ID;
        if (type.equals("m")) {
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

    public boolean logIn(String password) {
        if (Objects.equals(password, this.password)) {
            return true;
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}