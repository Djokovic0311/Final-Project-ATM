/*
 * Created by JFormDesigner on Sat Dec 10 23:50:46 EST 2022
 */

package view;

import controller.AccountController;
import controller.StockController;
import controller.TransactionController;
import model.CurrencyType;
import model.SecurityAccount;
import model.customerHeldStock;
import utils.ATMConstant;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUIManagerStock extends JFrame {
    private List userAccounts;
    private List userInfo;
    private String userName;
    private int stockID;
    private TransactionController transactionController = new TransactionController();
    private AccountController accountController = new AccountController();
    private StockController stockController = new StockController();
    ATMConstant atmConstant = new ATMConstant();
    public GUIManagerStock(List userAccounts, List userInfo, String userName) throws Exception {
        this.userName = userName;
        this.userInfo = userInfo;
        this.userAccounts = accountController.getAccountsForCustomer(userName);
        initComponents();
    }
    
    private void back(ActionEvent e) throws Exception {
        dispose();
        setVisible(false);
        new GUICustomerMoneyWindow(userAccounts, userInfo, userName);
    }

    private void update(ActionEvent e) throws Exception{
	dispose();
	new GUIUpdateStockPrice(userAccounts, userInfo, userName, stockID);
    }

    private void add(ActionEvent e) {
        dispose();
        new GUIAddStock(userAccounts, userInfo, userName, stockID, );
    }

    private void checkHeld(ActionEvent e) throws Exception {
        dispose();
        new GUIDisplayStock(userAccounts,userInfo,userName,"held").setVisible(true);
    }


    private void checkMarket(ActionEvent e) throws Exception {
        dispose();
        new GUIDisplayStock(userAccounts,userInfo,userName,"market").setVisible(true);
    }


    private void initComponents() {
        updatePriceButton = new JButton();
        checkStockButton = new JButton();
        addStockButton = new JButton();
        backButton = new JButton();

        //======== this ========
        setTitle("Manager Stock");
        var contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 2, 10, 10));

        //---- updatePriceButton ----
        updatePriceButton.setText("Update Pricce");
        updatePriceButton.addActionListener(e -> {
			try {
				update(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        contentPane.add(updatePriceButton);
        
        //---- checkStockButton ----
        checkStockButton.setText("Check A Customer");
        String userName = userNameTextField.getText().toString();
        if(Utils.isEmpty(userName)) {
            JOptionPane.showMessageDialog(this,"UserName cannot be empty");
            return;
        }
        checkStockButton.addActionListener(e -> {
        	
		});
        contentPane.add(checkStockButton);
        
        //---- addStockButton ----
        addStockButton.setText("Daily Report");
        addStockButton.addActionListener(e -> {
//
        });
        contentPane.add(addStockButton);


        //---- backButton ----
        backButton.setText("Log out");
        backButton.addActionListener(e -> back(e));
        contentPane.add(backButton);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton updatePriceButton;
    private JButton checkStockButton;
    private JButton addStockButton;
    private JButton backButton;
    private JTextField userNameTextField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
