/*
 * Created by JFormDesigner on Sun Dec 11 15:09:51 EST 2022
 */

package view;

import controller.AccountController;
import controller.StockController;
import controller.TransactionController;
import utils.ATMConstant;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUILoan extends JFrame {
    private List userAccounts;
    private List userInfo;
    private String userName;

    private TransactionController transactionController = new TransactionController();
    private AccountController accountController = new AccountController();
    private StockController stockController = new StockController();
    ATMConstant atmConstant = new ATMConstant();
    public GUILoan(List userInfo, String userName) throws Exception {
        this.userAccounts = accountController.getAccountsForCustomer(userName);
        this.userInfo = userInfo;
        this.userName = userName;
        initComponents();
    }

    private void back(ActionEvent e) throws Exception {
        dispose();
        new GUICustomerMoneyWindow(userInfo,userAccounts,userName).setVisible(true);
    }

    private void display(ActionEvent e) throws Exception {
        dispose();
        new GUIDisplayLoan(userInfo,userName).setVisible(true);
    }

    private void require(ActionEvent e) {
        dispose();
        new GUIRequireLoan(userInfo,userName).setVisible(true);
    }

    private void pay(ActionEvent e) {
        dispose();
        new GUIPayForLoan(userInfo,userName).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        displayButton = new JButton();
        requireButton = new JButton();
        payButton = new JButton();
        buttonBar = new JPanel();
        backButton = new JButton();

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

                //---- displayButton ----
                displayButton.setText("Display Loan");
                displayButton.addActionListener(e -> {
                    try {
                        display(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                contentPanel.add(displayButton);
                displayButton.setBounds(new Rectangle(new Point(80, 35), displayButton.getPreferredSize()));

                //---- requireButton ----
                requireButton.setText("Require Loan");
                requireButton.addActionListener(e -> require(e));
                contentPanel.add(requireButton);
                requireButton.setBounds(80, 95, 111, 30);

                //---- payButton ----
                payButton.setText("Pay for Loan");
                payButton.addActionListener(e -> pay(e));
                contentPanel.add(payButton);
                payButton.setBounds(80, 155, 111, 30);

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
    private JButton displayButton;
    private JButton requireButton;
    private JButton payButton;
    private JPanel buttonBar;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
