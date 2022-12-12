/*
 * Created by JFormDesigner on Sun Dec 11 22:57:42 EST 2022
 */

package view;

import java.awt.event.*;
import controller.AccountController;
import controller.LoanController;
import controller.TransactionController;
import utils.ATMConstant;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUIPayForLoan extends JFrame {
    private List userInfo;
    private String userName;

    private TransactionController transactionController = new TransactionController();
    private AccountController accountController = new AccountController();
    private LoanController loanController = new LoanController();
    ATMConstant atmConstant = new ATMConstant();

    public GUIPayForLoan(List userInfo, String userName) {
        this.userInfo = userInfo;
        this.userName = userName;
        initComponents();
    }

    private void pay(ActionEvent e) throws Exception {
        int accountID = Integer.parseInt(accountField.getText());
        int loanID = Integer.parseInt(loanIDTextField.getText());
        double amount = Double.parseDouble(amountField.getText());
        int status = loanController.payForLoan(userName,amount,accountID,loanID);
        if(status == atmConstant.getSUCCESS()){
            JOptionPane.showMessageDialog(null, "Success!!");
            setVisible(false);
            new GUILoan(userInfo,userName).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Something wrong! Please Try it again!");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        amountField = new JTextField();
        amountLabel = new JLabel();
        accountLabel = new JLabel();
        accountField = new JTextField();
        loanIDLabel = new JLabel();
        loanIDTextField = new JTextField();
        buttonBar = new JPanel();
        payButton = new JButton();
        cancelButton = new JButton();

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
                contentPanel.add(amountField);
                amountField.setBounds(175, 60, 90, 30);

                //---- amountLabel ----
                amountLabel.setText("Amount");
                contentPanel.add(amountLabel);
                amountLabel.setBounds(70, 65, 47, 16);

                //---- accountLabel ----
                accountLabel.setText("AccountID");
                contentPanel.add(accountLabel);
                accountLabel.setBounds(70, 15, 70, 16);
                contentPanel.add(accountField);
                accountField.setBounds(175, 10, 90, 30);

                //---- loanIDLabel ----
                loanIDLabel.setText("LoanID");
                contentPanel.add(loanIDLabel);
                loanIDLabel.setBounds(70, 110, 47, 16);
                contentPanel.add(loanIDTextField);
                loanIDTextField.setBounds(175, 105, 90, 30);

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

                //---- payButton ----
                payButton.setText("Pay");
                payButton.addActionListener(e -> {
                    try {
                        pay(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBar.add(payButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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
    private JTextField amountField;
    private JLabel amountLabel;
    private JLabel accountLabel;
    private JTextField accountField;
    private JLabel loanIDLabel;
    private JTextField loanIDTextField;
    private JPanel buttonBar;
    private JButton payButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
