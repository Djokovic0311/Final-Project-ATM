package service;
/*
    This is the class for login service
    @author: Jiahang Li
    @version: 1.0
*/

import dao.UserDao;
import model.Customer;
import model.Manager;
import model.User;
import utils.ATMConstant;
import utils.Utils;

import java.util.Objects;

public class LoginService {
    DbService dbService = new DbService();
    AccountService accountService = new AccountService();
    UserDao userDao = new UserDao();

    ATMConstant atmConstant = new ATMConstant();

    public int signIn(String userName,String password, String userType) throws Exception {
        if(Objects.equals(userType, "Customer")){
            Customer customer = new Customer();
            customer.setName(userName);
            int customerId = Utils.createHashCodeForPersonId(userName);
            customer.setID(customerId);
            customer.setPassword(password);

            Customer existingUser = (Customer) userDao.selectUserById(customerId,userType);
            if(existingUser == null) {
                return atmConstant.getNO_USER_FOUND();
            }
            else {
                if(Objects.equals(existingUser.getPassword(), password)) {
                    System.out.println("Success Login");
                    return atmConstant.getSUCCESS();
                }
                else {
                    System.out.println("Incorrect pwd");
                    return atmConstant.getERROR();
                }
            }
        }
        else {
            int managerID = atmConstant.getMANAGER_ACCOUNT_ID();
            Manager manager = (Manager) userDao.selectUserById(managerID,userType);
            if(manager == null) {
                return atmConstant.getNO_USER_FOUND();
            }
            else {
                if(Objects.equals(manager.getPassword(), password)) {
                    System.out.println("Success Login");
                    return atmConstant.getSUCCESS();
                }
                else {
                    System.out.println("Incorrect pwd");
                    return atmConstant.getERROR();
                }
            }
        }

    }

    public int signUp(Customer customer, String pwd) throws Exception {
        int id = customer.getID();
        String userName = customer.getName();
        Customer existingUser = (Customer) userDao.selectUserById(id, "customer");
        if(existingUser == null) {
            boolean status = userDao.insertIntoUser(id, userName, pwd);
            if(status) {
                return atmConstant.getSUCCESS();
            }
            else return atmConstant.getERROR();
        }
        else return atmConstant.getERROR();
    }

    public User getCustomerInfo(String userName) throws Exception {
        int customerID = Utils.createHashCodeForPersonId(userName);
        User user = userDao.selectUserById(customerID,"customer");
        return user;
    }

    public User getCustomerByID(int userID) throws Exception {
        User user = userDao.selectUserById(userID,"customer");
        return user;
    }
}