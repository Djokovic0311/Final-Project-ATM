/*
 * Created by JFormDesigner on Fri Dec 09 12:16:25 EST 2022
 */

package view;

import java.awt.event.*;

import controller.AccountController;
import model.*;

import java.util.List;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Jiahang Li
 */
public class GUICustomerAccountWindow extends JFrame {
    private List<Account> userAccounts;
    private List userInfo;
    private String username;
    private AccountController accountController = new AccountController();
    public GUICustomerAccountWindow(List userInfo, List userAccounts,String username) throws Exception {
        this.userAccounts = accountController.getAccountsForCustomer(username);
        this.userInfo = userInfo;
        this.username = username;
        initComponents();
        fillTable();
    }

    private void openAccount(ActionEvent e) {
        dispose();
        new GUICustomerOpenAccount(userAccounts, userInfo, username).setVisible(true);
    }

    private void closeAccount(ActionEvent e) {

        dispose();
        new GUICustomerCloseAccount(userAccounts,userInfo, username).setVisible(true);
    }

    private void back(ActionEvent e) throws Exception {
        dispose();
        userInfo = accountController.getAccountInfoForCustomer(username);
        new GUICustomerHomePage(userInfo, username).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        accountTable = new JTable();
        buttonBar = new JPanel();
        backButton = new JButton();
        openAccountButton = new JButton();
        closeAccountButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //======== scrollPane1 ========
                {

                    //---- accountTable ----
                    accountTable.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            null, null, null, null, null
                        }
                    ));
                    scrollPane1.setViewportView(accountTable);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(20, 15, 330, 170);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < contentPanel.getComponentCount(); i++) {
                        Rectangle bounds = contentPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = contentPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- backButton ----
                backButton.setText("back");
                backButton.addActionListener(e -> {
                    try {
                        back(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBar.add(backButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- openAccountButton ----
                openAccountButton.setText("Open an account");
                openAccountButton.addActionListener(e -> openAccount(e));
                buttonBar.add(openAccountButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- closeAccountButton ----
                closeAccountButton.setText("Close an account");
                closeAccountButton.addActionListener(e -> closeAccount(e));
                buttonBar.add(closeAccountButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    private void fillTable() throws Exception {
        this.userAccounts = accountController.getAccountsForCustomer(username);
        DefaultTableModel defaultModel = (DefaultTableModel) accountTable.getModel();
        for(Account account : userAccounts) {

            int accountID = account.getAccountID();
            String accountType = account.getType().toString();
            double balanceUSD = account.getBalanceByCurrency(CurrencyType.USD);
            double balanceCNY = account.getBalanceByCurrency(CurrencyType.CNY);
            double balanceEUR = account.getBalanceByCurrency(CurrencyType.EUR);
            Vector v = new Vector();


            v.addElement(accountID);
            v.addElement(accountType);
            v.addElement(balanceUSD);
            v.addElement(balanceCNY);
            v.addElement(balanceEUR);

            defaultModel.addRow(v);
            accountTable.setModel(defaultModel);
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable accountTable;
    private JPanel buttonBar;
    private JButton backButton;
    private JButton openAccountButton;
    private JButton closeAccountButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
