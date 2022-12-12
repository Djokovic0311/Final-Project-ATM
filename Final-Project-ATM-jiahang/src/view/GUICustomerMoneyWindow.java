/*
 * Created by JFormDesigner on Sat Dec 10 12:14:24 EST 2022
 */

package view;

import controller.AccountController;
import controller.LoanController;
import controller.TransactionController;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUICustomerMoneyWindow extends JFrame {
    private List userInfo;
    private List userAccounts;
    private String userName;

    private LoanController loanController = new LoanController();
    private TransactionController transactionController = new TransactionController();
    private AccountController accountController = new AccountController();
    public GUICustomerMoneyWindow(List userInfo, List userAccounts, String userName) throws Exception {
        this.userInfo = userInfo;
        this.userAccounts = accountController.getAccountsForCustomer(userName);
        this.userName = userName;
        initComponents();
    }

    private void back(ActionEvent e) {
        dispose();
        new GUICustomerHomePage(userInfo, userName).setVisible(true);
    }

    private void logout(ActionEvent e) {
        dispose();
        GUILoginWindow guiLoginWindow = new GUILoginWindow();
        guiLoginWindow.setVisible(true);
    }

    private void withdraw(ActionEvent e) {
        dispose();
        new GUIWithdraw(userAccounts, userInfo, userName).setVisible(true);
    }

    private void deposit(ActionEvent e) {
        dispose();
        new GUIDeposit(userAccounts, userInfo, userName).setVisible(true);
    }

    private void transfer(ActionEvent e) {
        dispose();
        new GUITransfer(userAccounts,userInfo,userName).setVisible(true);
    }

    private void Transaction(ActionEvent e) throws Exception {
        dispose();
        new GUITransactionHistory(userAccounts,userInfo,userName).setVisible(true);
    }

    private void stock(ActionEvent e) throws Exception {
        dispose();
        new GUIStock(userAccounts,userInfo,userName).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        withdrawButton = new JButton();
        depositButton = new JButton();
        transferButton = new JButton();
        TransactionButton = new JButton();
        stockButton = new JButton();
        loanButton = new JButton();
        transcurrencyButton = new JButton();
        redeemButton = new JButton();
        buttonBar = new JPanel();
        backButton = new JButton();
        logoutButton = new JButton();

        //======== this ========
        setTitle("Welcome to Business Center!");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(null);

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridLayout(3, 3, 10, 10));

                //---- withdrawButton ----
                withdrawButton.setText("Withdraw");
                withdrawButton.addActionListener(e -> withdraw(e));
                contentPanel.add(withdrawButton);

                //---- depositButton ----
                depositButton.setText("Deposit");
                depositButton.addActionListener(e -> deposit(e));
                contentPanel.add(depositButton);

                //---- transferButton ----
                transferButton.setText("Transfer");
                transferButton.addActionListener(e -> transfer(e));
                contentPanel.add(transferButton);

                //---- TransactionButton ----
                TransactionButton.setText("Transaction");
                TransactionButton.addActionListener(e -> {
                    try {
                        Transaction(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                contentPanel.add(TransactionButton);

                //---- stockButton ----
                stockButton.setText("Stock");
                stockButton.addActionListener(e -> {
                    try {
                        stock(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                contentPanel.add(stockButton);

                //---- loanButton ----
                loanButton.setText("Loan");
                contentPanel.add(loanButton);

                //---- transcurrencyButton ----
                transcurrencyButton.setText("Transcurrency");
                contentPanel.add(transcurrencyButton);

                //---- redeemButton ----
                redeemButton.setText("Redeem");
                contentPanel.add(redeemButton);
            }
            dialogPane.add(contentPanel);
            contentPanel.setBounds(12, 12, 373, 198);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- backButton ----
                backButton.setText("back");
                backButton.addActionListener(e -> back(e));
                buttonBar.add(backButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- logoutButton ----
                logoutButton.setText("Log out");
                logoutButton.addActionListener(e -> logout(e));
                buttonBar.add(logoutButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
            }
            dialogPane.add(buttonBar);
            buttonBar.setBounds(15, 210, 374, buttonBar.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < dialogPane.getComponentCount(); i++) {
                    Rectangle bounds = dialogPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = dialogPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                dialogPane.setMinimumSize(preferredSize);
                dialogPane.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(dialogPane);
        dialogPane.setBounds(new Rectangle(new Point(0, 0), dialogPane.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton transferButton;
    private JButton TransactionButton;
    private JButton stockButton;
    private JButton loanButton;
    private JButton transcurrencyButton;
    private JButton redeemButton;
    private JPanel buttonBar;
    private JButton backButton;
    private JButton logoutButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
