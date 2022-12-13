package utils;

public class ATMConstant {
    private int idBits = 8;
    private int SUCCESS = 1;
    private int ERROR = 0;
    private int NO_USER_FOUND = 2;
    private int NO_ENOUGH_BALANCE = 3;
    // TODO: DB INFO
    private String DBURL = "jdbc:mysql://localhost:3306/Bank?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String DBUSERNAME = "root";
    private String DBPWD = "123456";
    private double LOAN_INTEREST = 0.05;
    private int MANAGER_ACCOUNT_ID = 0;

    private double REDEEM_INTEREST = 0.1;

    public int getIdBits() {
        return idBits;
    }

    public void setIdBits(int idBits) {
        this.idBits = idBits;
    }

    public int getSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(int SUCCESS) {
        this.SUCCESS = SUCCESS;
    }

    public String getDBURL() {
        return DBURL;
    }

    public void setDBURL(String DBURL) {
        this.DBURL = DBURL;
    }

    public String getDBUSERNAME() {
        return DBUSERNAME;
    }

    public void setDBUSERNAME(String DBUSERNAME) {
        this.DBUSERNAME = DBUSERNAME;
    }

    public String getDBPWD() {
        return DBPWD;
    }

    public void setDBPWD(String DBPWD) {
        this.DBPWD = DBPWD;
    }

    public int getERROR() {
        return ERROR;
    }

    public void setERROR(int ERROR) {
        this.ERROR = ERROR;
    }

    public int getNO_USER_FOUND() {
        return NO_USER_FOUND;
    }

    public void setNO_USER_FOUND(int NO_USER_FOUND) {
        this.NO_USER_FOUND = NO_USER_FOUND;
    }

    public int getMANAGER_ACCOUNT_ID() {
        return MANAGER_ACCOUNT_ID;
    }

    public void setMANAGER_ACCOUNT_ID(int MANAGER_ACCOUNT_ID) {
        this.MANAGER_ACCOUNT_ID = MANAGER_ACCOUNT_ID;
    }

    public int getNO_ENOUGH_BALANCE() {
        return NO_ENOUGH_BALANCE;
    }

    public void setNO_ENOUGH_BALANCE(int NO_ENOUGH_BALANCE) {
        this.NO_ENOUGH_BALANCE = NO_ENOUGH_BALANCE;
    }

    public double getLOAN_INTEREST() {
        return LOAN_INTEREST;
    }

    public void setLOAN_INTEREST(double LOAN_INTEREST) {
        this.LOAN_INTEREST = LOAN_INTEREST;
    }

    public double getREDEEM_INTEREST() {
        return REDEEM_INTEREST;
    }

    public void setREDEEM_INTEREST(double REDEEM_INTEREST) {
        this.REDEEM_INTEREST = REDEEM_INTEREST;
    }

}
