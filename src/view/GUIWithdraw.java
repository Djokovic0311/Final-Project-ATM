/*
 * Created by JFormDesigner on Sat Dec 10 13:33:27 EST 2022
 */

package view;

import controller.TransactionController;
import model.CurrencyType;
//import sun.awt.UNIXToolkit;
import utils.ATMConstant;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUIWithdraw extends JFrame {
    private List userAccounts;
    private List userInfo;
    private String userName;

    private TransactionController transactionController = new TransactionController();
    ATMConstant atmConstant = new ATMConstant();

    public GUIWithdraw(List userAccounts, List userInfo, String userName) {
        this.userName = userName;
        this.userInfo = userInfo;
        this.userAccounts = userAccounts;
        initComponents();
    }

    private void withdraw(ActionEvent e) throws Exception {
        CurrencyType currencyType = CurrencyType.valueOf(Objects.requireNonNull(currencyTypeComboBox.getSelectedItem()).toString());
        double amount = Double.parseDouble(amountTextField.getText());
        int accountID = Integer.parseInt(accountIDTextField.getText());
        int customerID = Utils.createHashCodeForPersonId(userName);
        int status = transactionController.withdraw(customerID,accountID,amount,currencyType);
        if(status == atmConstant.getSUCCESS()) {
            JOptionPane.showMessageDialog(null, "Success!!");
            setVisible(false);
            new GUICustomerMoneyWindow(userAccounts, userInfo, userName);
        } else {
            JOptionPane.showMessageDialog(null, "Something wrong! Please Try it again!");
        }

    }

    private void cancel(ActionEvent e) throws Exception {
        dispose();
        setVisible(false);
        new GUICustomerMoneyWindow(userAccounts, userInfo, userName);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        accountIDTextField = new JTextField();
        currencyTypeComboBox = new JComboBox<>();
        amountTextField = new JTextField();
        buttonBar = new JPanel();
        withdrawButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Withdraw ");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- label1 ----
                label1.setText("AccountID");
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(50, 35), label1.getPreferredSize()));

                //---- label2 ----
                label2.setText("Amount");
                contentPanel.add(label2);
                label2.setBounds(50, 130, 47, 16);

                //---- label3 ----
                label3.setText("CurrencyType");
                contentPanel.add(label3);
                label3.setBounds(50, 80, 120, 20);
                contentPanel.add(accountIDTextField);
                accountIDTextField.setBounds(160, 30, 120, accountIDTextField.getPreferredSize().height);

                //---- currencyTypeComboBox ----
                currencyTypeComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "USD",
                    "EUR",
                    "GBP",
                    "CNY",
                    "HKD"
                }));
                contentPanel.add(currencyTypeComboBox);
                currencyTypeComboBox.setBounds(160, 80, 84, 30);
                contentPanel.add(amountTextField);
                amountTextField.setBounds(160, 125, 85, 30);

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
            dialogPane.add(contentPanel, BorderLayout.WEST);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- withdrawButton ----
                withdrawButton.setText("Withdraw");
                withdrawButton.addActionListener(e -> {
                    try {
                        withdraw(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBar.add(withdrawButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> {
                    try {
                        cancel(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
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
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JTextField accountIDTextField;
    private JComboBox<String> currencyTypeComboBox;
    private JTextField amountTextField;
    private JPanel buttonBar;
    private JButton withdrawButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
