/*
 * Created by JFormDesigner on Wed Dec 07 22:58:40 EST 2022
 */

package view;

import controller.AccountController;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**
 * @author unknown
 */
public class GUICustomerHomePage extends JFrame {
    private List userInfo;

    private AccountController accountController = new AccountController();
    private String userName;
    public GUICustomerHomePage(List userInfo, String userName) {
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

    private void money(ActionEvent e) throws Exception {
        // TODO add your code here
        dispose();
        List userAccounts = accountController.getAccountsForCustomer(userName);
        new GUICustomerMoneyWindow(userInfo,userAccounts,userName).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Mingxin Li
        profileButton = new JButton();
        accountButton = new JButton();
        moneyButton = new JButton();
        backButton = new JButton();

        //======== this ========
        setTitle("Hello Customer!");
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 2, 10, 10));

        //---- profileButton ----
        profileButton.setText("Manage Profile");
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profile(e);
                profile(e);
            }
        });
        contentPane.add(profileButton);

        //---- accountButton ----
        accountButton.setText("Account Matters");
        accountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                account(e);
            }
        });
        contentPane.add(accountButton);

        //---- moneyButton ----
        moneyButton.setText("Money Matters");
        moneyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                money(e);
                money(e);
            }
        });
        contentPane.add(moneyButton);

        //---- backButton ----
        backButton.setText("Log out");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back(e);
            }
        });
        contentPane.add(backButton);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Mingxin Li
    private JButton profileButton;
    private JButton accountButton;
    private JButton moneyButton;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
