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
//                System.out.println("1");
                return atmConstant.getNO_USER_FOUND();
            }
            else {
                if(Objects.equals(existingUser.getPassword(), password)) {
                    System.out.println("Success Login");
                    return atmConstant.getSUCCESS();
                }
                else {
//                    System.out.println("2");
                    System.out.println(existingUser.getPassword());
                    System.out.println(password);
                    System.out.println("Incorrect pwd");
                    return atmConstant.getERROR();
                }
            }
        }
        else {
            int managerID = atmConstant.getMANAGER_ACCOUNT_ID();
            Manager manager = (Manager) userDao.selectUserById(managerID,userType);
            if(manager == null || !Objects.equals(manager.getName(), userName)) {
                return atmConstant.getNO_USER_FOUND();
            }
            else {
                if(Objects.equals(manager.getPassword(), password) && Objects.equals(manager.getName(), "banker")) {
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

        Customer existingUser = (Customer) userDao.selectUserById(id, "Customer");
        if(existingUser == null) {
//            System.out.println("1");
            System.out.println(id);
            boolean status = userDao.insertIntoUser(id, userName, pwd);
            if(status) {

                return atmConstant.getSUCCESS();
            }

            else {
//                System.out.println("2");
                return atmConstant.getERROR();
            }
        }
        else return atmConstant.getERROR();
    }

    public Customer getCustomerInfo(String userName) throws Exception {
        int customerID = Utils.createHashCodeForPersonId(userName);
        Customer customer = (Customer) userDao.selectUserById(customerID,"Customer");
        return customer;
    }

    public Customer getCustomerByID(int userID) throws Exception {
        Customer customer = (Customer) userDao.selectUserById(userID,"Customer");
        return customer;
    }
}
