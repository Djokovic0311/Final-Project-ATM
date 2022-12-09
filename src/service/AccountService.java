package service;

import dao.ConnectDao;
import model.User;
import utils.ATMConstant;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    ConnectDao connectDao = new ConnectDao();
    ATMConstant atmConstant = new ATMConstant();
    public List<Object> getAccountInfoForCustomer(User user) throws Exception {
        Connection conn = connectDao.connectToDb();
        List<Object> info = new ArrayList<>();
        info.add(user.getName());
        info.add(user.getPassword());
        return info;
    }
}
