package utils;

public class ATMConstant {
    private int idBits = 8;
    private int SUCCESS = 1;
    private int ERROR = 0;
    private int NO_USER_FOUND = 2;
    private int NO_ENOUGH_BALANCE = 3;
    // TODO: DB INFO
    private String DBURL = "";
    private String DBUSERNAME = "";
    private String DBPWD = "";
    private int MANAGER_ACCOUNT_ID = 0;

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
}
