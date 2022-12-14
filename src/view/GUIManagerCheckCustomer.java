/*
 * Created by JFormDesigner on Tue Dec 13 15:58:54 EST 2022
 */

package view;

import controller.AccountController;
import controller.LoginController;
import model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

/**
 * @author unknown
 */
public class GUIManagerCheckCustomer extends JFrame {
    AccountController accountController = new AccountController();
    LoginController loginController = new LoginController();
    private List accounts;
    public GUIManagerCheckCustomer() {
        initComponents();
    }

    private void back(ActionEvent e) {
        dispose();
        new GUIManagerHomepage().setVisible(true);
    }

    private void check(ActionEvent e) throws Exception {
        int customerID = Integer.parseInt(customerIDTextField.getText());
        Customer customer = (Customer) loginController.getUserByID(customerID);
        this.accounts = accountController.getAccountsForCustomer(customer.getName());
        fillTable();
    }

    private void fillTable() throws Exception {
        DefaultTableModel defaultModel = (DefaultTableModel) accountTable.getModel();
        defaultModel.setNumRows(0);
        for(Object account : accounts) {
            Vector v = new Vector();
            if(account instanceof SavingAccount || account instanceof CheckingAccount){
                int accountID = ((Account) account).getAccountID();
                String accountType = String.valueOf(((Account) account).getType());
                double balance = 0;
                for(CurrencyType currencyType : ((Account) account).getBalance().keySet()){
                    balance += ((Account) account).getBalanceByCurrency(currencyType) * currencyType.getValue();
                }
                v.addElement(accountID);
                v.addElement(accountType);
                v.addElement(balance);
            }
            defaultModel.addRow(v);
            accountTable.setModel(defaultModel);
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        customerIDLabel = new JLabel();
        customerIDTextField = new JTextField();
        buttonBar = new JPanel();
        checkButton = new JButton();
        backButton = new JButton();
        scrollPane1 = new JScrollPane();
        accountTable = new JTable();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- customerIDLabel ----
                customerIDLabel.setText("customerID");
                contentPanel.add(customerIDLabel);
                customerIDLabel.setBounds(new Rectangle(new Point(75, 15), customerIDLabel.getPreferredSize()));
                contentPanel.add(customerIDTextField);
                customerIDTextField.setBounds(200, 10, 85, customerIDTextField.getPreferredSize().height);

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

                //---- checkButton ----
                checkButton.setText("check");
                checkButton.addActionListener(e -> {
                    try {
                        check(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBar.add(checkButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- backButton ----
                backButton.setText("back");
                backButton.addActionListener(e -> back(e));
                buttonBar.add(backButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //======== scrollPane1 ========
        {

            //---- accountTable ----
            accountTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "accountID", "accountType", "total Balance"
                }
            ));
            scrollPane1.setViewportView(accountTable);
            scrollPane1.setBounds(0, 0, 375, 200);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel customerIDLabel;
    private JTextField customerIDTextField;
    private JPanel buttonBar;
    private JButton checkButton;
    private JButton backButton;
    private JScrollPane scrollPane1;
    private JTable accountTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
