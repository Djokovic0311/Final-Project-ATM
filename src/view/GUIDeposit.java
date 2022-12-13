/*
 * Created by JFormDesigner on Sat Dec 10 17:52:24 EST 2022
 */

package view;

import java.awt.event.*;
import controller.TransactionController;
import model.CurrencyType;
import utils.ATMConstant;
import utils.Utils;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUIDeposit extends JFrame {
    private List userAccounts;
    private List userInfo;
    private String userName;

    private TransactionController transactionController = new TransactionController();
    ATMConstant atmConstant = new ATMConstant();
    public GUIDeposit(List userAccounts, List userInfo, String userName) {
        this.userName = userName;
        this.userInfo = userInfo;
        this.userAccounts = userAccounts;
        initComponents();
    }

    private void cancel(ActionEvent e) throws Exception {
        dispose();
        setVisible(false);
        new GUICustomerMoneyWindow(userAccounts, userInfo, userName);
    }

    private void deposit(ActionEvent e) throws Exception {
        CurrencyType currencyType = CurrencyType.valueOf(Objects.requireNonNull(currencyTypeComboBox.getSelectedItem()).toString());
        double amount = Double.parseDouble(amountTextField.getText());
        int accountID = Integer.parseInt(accountIDTextField.getText());
        int customerID = Utils.createHashCodeForPersonId(userName);
        int status = transactionController.deposit(customerID,accountID,amount,currencyType);
        if(status == atmConstant.getSUCCESS()) {
            JOptionPane.showMessageDialog(null, "Success!!");
            setVisible(false);
            new GUICustomerMoneyWindow(userAccounts, userInfo, userName);
        } else {
            JOptionPane.showMessageDialog(null, "Something wrong! Please Try it again!");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        accountLabel = new JLabel();
        CurrencyTypeLabel = new JLabel();
        amountLabel = new JLabel();
        accountIDTextField = new JTextField();
        currencyTypeComboBox = new JComboBox<>();
        amountTextField = new JTextField();
        buttonBar = new JPanel();
        depositButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Deposit");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- accountLabel ----
                accountLabel.setText("AccountID");
                contentPanel.add(accountLabel);
                accountLabel.setBounds(85, 45, 63, 16);

                //---- CurrencyTypeLabel ----
                CurrencyTypeLabel.setText("CurrencyType");
                contentPanel.add(CurrencyTypeLabel);
                CurrencyTypeLabel.setBounds(85, 80, 120, 20);

                //---- amountLabel ----
                amountLabel.setText("Amount");
                contentPanel.add(amountLabel);
                amountLabel.setBounds(85, 120, 47, 16);
                contentPanel.add(accountIDTextField);
                accountIDTextField.setBounds(175, 40, 120, 30);

                //---- currencyTypeComboBox ----
                currencyTypeComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "USD",
                    "EUR",
                    "CNY"
                }));
                contentPanel.add(currencyTypeComboBox);
                currencyTypeComboBox.setBounds(180, 80, 84, 30);
                contentPanel.add(amountTextField);
                amountTextField.setBounds(180, 120, 85, 30);

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

                //---- depositButton ----
                depositButton.setText("Deposit");
                depositButton.addActionListener(e -> deposit(e));
                buttonBar.add(depositButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancel(e));
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
    private JLabel accountLabel;
    private JLabel CurrencyTypeLabel;
    private JLabel amountLabel;
    private JTextField accountIDTextField;
    private JComboBox<String> currencyTypeComboBox;
    private JTextField amountTextField;
    private JPanel buttonBar;
    private JButton depositButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
