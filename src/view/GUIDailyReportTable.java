/*
 * Created by JFormDesigner on Wed Dec 14 15:24:04 EST 2022
 */

package view;

import java.awt.event.*;
import model.Transaction;

import java.awt.*;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author unknown
 */
public class GUIDailyReportTable extends JFrame {
    private List<Transaction> transactions;
    public GUIDailyReportTable(List<Transaction> transactions) throws Exception {
        this.transactions = transactions;
        initComponents();
        fillTable();
    }
    private void fillTable() throws Exception {
        DefaultTableModel defaultModel = (DefaultTableModel) transactionTable.getModel();
        defaultModel.setNumRows(0);
        for(Transaction transaction : transactions) {
            Vector v = new Vector();
            int transactionID = transaction.getID();
            String type = String.valueOf(transaction.getType());
            int accountID = transaction.getFromAccountID();
            double amount = transaction.getAmount();
            String currency = transaction.getCurrencyType().toString();
            v.addElement(transactionID);
            v.addElement(type);
            v.addElement(accountID);
            v.addElement(amount);
            v.addElement(currency);

            defaultModel.addRow(v);
            transactionTable.setModel(defaultModel);
        }
        contentPanel.revalidate();
        scrollPane1.revalidate();
    }

    private void back(ActionEvent e) {
        dispose();
        new GUIDailyReport().setVisible(true);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        transactionTable = new JTable();
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

                    //---- transactionTable ----
                    transactionTable.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            "transactionID", "type", "accountID", "amount", "currencyType"
                        }
                    ));
                    scrollPane1.setViewportView(transactionTable);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(0, 0, 365, 155);

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
                okButton.addActionListener(e -> back(e));
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

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable transactionTable;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
