/*
 * Created by JFormDesigner on Sat Dec 10 19:29:28 EST 2022
 */

package view;

import java.awt.event.*;
import javax.swing.table.*;
import controller.TransactionController;
import model.CurrencyType;
import model.Transaction;
import model.TransactionType;
import utils.ATMConstant;

import java.awt.*;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUITransactionHistory extends JFrame {
    private List userAccounts;
    private List userInfo;
    private List<Transaction> transactions;
    private String userName;


    private TransactionController transactionController = new TransactionController();
    ATMConstant atmConstant = new ATMConstant();
    public GUITransactionHistory(List userInfo, List userAccounts, String userName) throws Exception {
        this.userName = userName;
        this.userInfo = userInfo;
        this.userAccounts = userAccounts;
        initComponents();
        fillTable();
    }

    private void back(ActionEvent e) throws Exception {
        dispose();
        new GUICustomerMoneyWindow(userAccounts, userInfo, userName).setVisible(true);
    }
    private void fillTable() throws Exception {
        this.transactions = transactionController.getTransactionsForCustomer(userName);
        DefaultTableModel defaultModel = (DefaultTableModel) transactionTable.getModel();
        for(Transaction transaction : transactions) {

            double amount = transaction.getAmount();
            int transactionID = transaction.getID();
            int customerID = transaction.getuserID();
            long timestamp = transaction.getTimestamp();
            String currencyType = String.valueOf(transaction.getCurrencyType());
            String transactionType = String.valueOf(transaction.getType());
            int toAccountID = transaction.getToAccountID();
            int fromAccountID = transaction.getFromAccountID();
            Vector v = new Vector();

            v.addElement(transactionID);
            v.addElement(amount);
            v.addElement(transactionType);
            v.addElement(currencyType);
            v.addElement(timestamp);
            v.addElement(customerID);
            v.addElement(toAccountID);
            v.addElement(fromAccountID);

            defaultModel.addRow(v);
            transactionTable.setModel(defaultModel);
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        transactionTable = new JTable();
        buttonBar = new JPanel();
        backButton = new JButton();

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

                    //---- transactionTable ----
                    transactionTable.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                            //            v.addElement(transactionID);
                            //            v.addElement(amount);
                            //            v.addElement(transactionType);
                            //            v.addElement(currencyType);
                            //            v.addElement(timestamp);
                            //            v.addElement(customerID);
                            //            v.addElement(toAccountID);
                            //            v.addElement(fromAccountID);
                        new String[] {
                            "transactionID", "amount", "transactionType", "currencyType", "timestamp", "customerID", "toAccountID", "fromAccountID"
                        }
                    ));
                    scrollPane1.setViewportView(transactionTable);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(0, 0, 455, 280);

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
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- backButton ----
                backButton.setText("Back");
                backButton.addActionListener(e -> {
                    try {
                        back(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBar.add(backButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable transactionTable;
    private JPanel buttonBar;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
