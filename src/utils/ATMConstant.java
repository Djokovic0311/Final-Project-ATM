package utils;

public class ATMConstant {
    private int idBits = 8;
    private int SUCCESS = 0;
    // TODO: DB INFO
    private String DBURL = "";
    private String DBUSERNAME = "";
    private String DBPWD = "";

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
}
