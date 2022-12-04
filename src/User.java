public class User {
    private String name;
    private int ID;
    public enum Type {
        Manager, Customer
    }
    private Type type;
    private int password;

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