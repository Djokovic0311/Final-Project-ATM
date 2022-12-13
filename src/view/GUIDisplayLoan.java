/*
 * Created by JFormDesigner on Sun Dec 11 21:42:18 EST 2022
 */

package view;

import controller.AccountController;
import controller.LoanController;
import controller.StockController;
import controller.TransactionController;
import model.CurrencyType;
import model.Loan;
import model.Transaction;
import model.TransactionType;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * @author unknown
 */
public class GUIDisplayLoan extends JFrame {
    private List userAccounts;
    private List userInfo;
    private List<Loan> loans;
    private String userName;

    private TransactionController transactionController = new TransactionController();
    private AccountController accountController = new AccountController();
    private StockController stockController = new StockController();
    private LoanController loanController = new LoanController();
    public GUIDisplayLoan(List userInfo, String userName) throws Exception {
        this.userInfo = userInfo;
        this.userName = userName;
        initComponents();
        fillTable();
    }

    private void back(ActionEvent e) {
        // TODO add your code here
    }
    private void fillTable() throws Exception {
        this.loans = loanController.getLoansForCustomer(userName);
        DefaultTableModel defaultModel = (DefaultTableModel) loanTable.getModel();
        for(Loan loan : loans) {

            double amount = loan.getPrincipalAmount();
            int loanID = loan.getLoanID();
            CurrencyType currencyType = loan.getCurrency();
            double interestRate = loan.getRateOfInterest();

            Vector v = new Vector();


            v.addElement(amount);
            v.addElement(loanID);
            v.addElement(currencyType);
            v.addElement(interestRate);


            defaultModel.addRow(v);
            loanTable.setModel(defaultModel);
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        loanTable = new JTable();
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

                    //---- loanTable ----
                    loanTable.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            null, null, null, null
                        }
                    ));
                    scrollPane1.setViewportView(loanTable);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(0, 0, 375, 200);

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
                backButton.addActionListener(e -> back(e));
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
    private JTable loanTable;
    private JPanel buttonBar;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
