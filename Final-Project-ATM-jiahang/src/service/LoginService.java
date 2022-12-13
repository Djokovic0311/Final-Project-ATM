package service;/*
    This is the class for login service
    @author: Jiahang Li
    @version: 1.0
*/

import dao.UserDao;

import model.Customer;
import model.User;
import utils.ATMConstant;
import utils.Utils;

import java.util.Objects;
import model.Manager;
public class LoginService {
    DbService dbService = new DbService();
    AccountService accountService = new AccountService();
    UserDao userDao = new UserDao();

    ATMConstant atmConstant = new ATMConstant();

    public int signIn(String userName,String password) {
        Customer customer = new Customer();
        customer.setName(userName);
        int customerId = Utils.createHashCodeForPersonId(userName);
        customer.setID(customerId);
        customer.setPassword(password);

        Customer existingUser = (Customer) userDao.selectUserById(customerId);
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

    public int signUp(Customer customer, String pwd) {
        int id = customer.getID();
        String userName = customer.getName();
        Customer existingUser = (Customer) userDao.selectUserById(id);
        if(existingUser == null) {
            boolean status = userDao.insertIntoUser(id, userName, pwd);
            if(status) {
                return atmConstant.getSUCCESS();
            }
            else return atmConstant.getERROR();
        }
        else return atmConstant.getERROR();
    }

    public User getCustomerInfo(String userName) {
        int customerID = Utils.createHashCodeForPersonId(userName);
        User user = userDao.selectUserById(customerID);
        return user;
    }
    
    public Manager getManagerInfo(String userName) {
    	Manager manager = new Manager();
    	int managerID = manager.getID();
    	Manager manager = userDao.selectUserById(managerID);
    	return manager;
    }
}