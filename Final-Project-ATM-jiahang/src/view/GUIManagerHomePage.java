/*
 * Created by JFormDesigner on Wed Dec 07 22:58:40 EST 2022
 */

package view;
import model.Manager;
import model.marketStock;
import utils.Utils;
import controller.AccountController;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GUIManagerHomePage extends JFrame {
	private Manager manager;
    private List userInfo;
    private AccountController accountController = new AccountController();
    private String userName;
    public GUIManagerHomePage(List userInfo, String userName) {
        this.userInfo = userInfo;
        this.userName = userName;
        initComponents();
    }

    private void profile(ActionEvent e) {
        dispose();
        new GUIUserProfileWindow(userInfo, userName).setVisible(true);
    }

    private void back(ActionEvent e) {
        dispose();
        GUILoginWindow guiLoginWindow = new GUILoginWindow();
        guiLoginWindow.setVisible(true);
    }

    private void account(ActionEvent e) throws Exception {
        dispose();
        List userAccounts = accountController.getAccountsForCustomer(userName);
        new GUICustomerAccountWindow(userInfo, userAccounts, userName).setVisible(true);
    }
    
    private void stock(ActionEvent e) throws Exception{
    	dispose();
    	List managerAccounts = accountController.getAccountsForCustomer(userName);
    	new GUIDisplayStock(managerAccounts, userInfo, userName, "manager");
    }
    
    private void money(ActionEvent e) throws Exception {
        // TODO add your code here
        dispose();
        List userAccounts = accountController.getAccountsForCustomer(userName);
        new GUICustomerMoneyWindow(userInfo,userAccounts,userName).setVisible(true);
    }

    private void initComponents() {
        checkCustomerButton = new JButton();
        dailyReportButton = new JButton();
        stockButton = new JButton();
        managerInfoButton = new JButton();
        backButton = new JButton();

        //======== this ========
        setTitle("Hello Manager!");
        var contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 2, 10, 10));

        //---- managerInfoButton ----
        managerInfoButton.setText("Manager Profile");
        managerInfoButton.addActionListener(e -> {
			profile(e);
			profile(e);
		});
        contentPane.add(managerInfoButton);
        
        //---- checkCustomerButton ----
        checkCustomerButton.setText("Check A Customer");
        String userName = userNameTextField.getText().toString();
        if(Utils.isEmpty(userName)) {
            JOptionPane.showMessageDialog(this,"UserName cannot be empty");
            return;
        }
        managerInfoButton.addActionListener(e -> {
			account(e);
		});
        contentPane.add(managerInfoButton);
        
        //---- dailyReportButton ----
        dailyReportButton.setText("Daily Report");
        dailyReportButton.addActionListener(e -> {
            try {
                //
            	manager.getDailyReport(Date date);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        contentPane.add(dailyReportButton);

        //---- stockButton ----
        stockButton.setText("Stock");
        stockButton.addActionListener(e -> {
            try {
                stock(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            try {
                stock(e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        contentPane.add(stockButton);

        //---- backButton ----
        backButton.setText("Log out");
        backButton.addActionListener(e -> back(e));
        contentPane.add(backButton);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton checkCustomerButton;
    private JButton dailyReportButton;
    private JButton stockButton;
    private JButton managerInfoButton;
    private JButton backButton;
    private JTextField userNameTextField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
