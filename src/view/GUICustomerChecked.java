/*
 * Created by JFormDesigner on Wed Dec 14 15:28:29 EST 2022
 */

package view;

import java.awt.event.*;
import model.Account;
import model.CheckingAccount;
import model.CurrencyType;
import model.SavingAccount;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author unknown
 */
public class GUICustomerChecked extends JFrame {
    private List<Account> accounts = new ArrayList<>();
    public GUICustomerChecked(List<Account> accounts) throws Exception {
        this.accounts = accounts;
        initComponents();
        fillTable();
    }

    private void ok(ActionEvent e) throws Exception {
        dispose();
        new GUIManagerCheckCustomer().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        accountTable = new JTable();
        buttonBar = new JPanel();
        okButton = new JButton();

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
                            null, null, null
                        }
                    ));
                    scrollPane1.setViewportView(accountTable);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(0, 0, 385, 160);

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

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> {
                    try {
                        ok(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
        DefaultTableModel defaultModel = (DefaultTableModel) accountTable.getModel();
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
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable accountTable;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
