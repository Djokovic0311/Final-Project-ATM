/*
 * Created by JFormDesigner on Fri Dec 09 20:57:08 EST 2022
 */

package view;

import controller.AccountController;
import model.AccountType;
import model.CurrencyType;
import utils.ATMConstant;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.util.Currency;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUICustomerOpenAccount extends JFrame {
    private List userAccounts;
    private String username;
    private List userInfo;
    private AccountController accountController = new AccountController();
    ATMConstant atmConstant = new ATMConstant();
    public GUICustomerOpenAccount(List userAccounts, List userInfo, String username) {
        this.userAccounts = userAccounts;
        this.username = username;
        this.userInfo = userInfo;
        initComponents();

    }

    private void cancel(ActionEvent e) {
        dispose();
        new GUICustomerAccountWindow(userAccounts, userInfo, username).setVisible(true);
    }

    private void open(ActionEvent e) throws Exception {
        // TODO add your code here
        CurrencyType currencyType = CurrencyType.valueOf(Objects.requireNonNull(currencyTypeComboBox.getSelectedItem()).toString());
        AccountType accountType = AccountType.valueOf(Objects.requireNonNull(accountTypeComboBox.getSelectedItem()).toString());
        double balance = Double.parseDouble(balanceTextField.getText());
        int customerID = Utils.createHashCodeForPersonId(username);
        int status = 0;
        if(accountType == AccountType.CHECKINGS || accountType == AccountType.SAVINGS) {
            status = accountController.createNewCheckingOrSavingAccount(customerID,accountType,balance,currencyType);
        }
        else if(accountType == AccountType.SECURITY) {
            status = accountController.createNewSecurityAccount(username,accountType,balance,currencyType);
        }

        if(status == atmConstant.getSUCCESS()) {
            JOptionPane.showMessageDialog(null, "Success!!");
            new GUICustomerAccountWindow(userAccounts, userInfo, username).setVisible(true);
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Please try again!!");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        currencyTypeLabel = new JLabel();
        balanceLabel = new JLabel();
        accountTypeComboBox = new JComboBox<>();
        currencyTypeComboBox = new JComboBox<>();
        balanceTextField = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Open a new account!");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- label1 ----
                label1.setText("AccountType");
                contentPanel.add(label1);
                label1.setBounds(new Rectangle(new Point(45, 40), label1.getPreferredSize()));

                //---- currencyTypeLabel ----
                currencyTypeLabel.setText("CurrencyType");
                contentPanel.add(currencyTypeLabel);
                currencyTypeLabel.setBounds(45, 80, 90, 16);

                //---- balanceLabel ----
                balanceLabel.setText("Initial balance");
                contentPanel.add(balanceLabel);
                balanceLabel.setBounds(45, 120, 90, 16);

                //---- accountTypeComboBox ----
                accountTypeComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "CHECKINGS",
                    "SAVINGS",
                    "SECURITY"
                }));
                contentPanel.add(accountTypeComboBox);
                accountTypeComboBox.setBounds(new Rectangle(new Point(165, 35), accountTypeComboBox.getPreferredSize()));

                //---- currencyTypeComboBox ----
                currencyTypeComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "USD",
                    "EUR",
                    "GBP",
                    "CNY",
                    "HKD"
                }));
                contentPanel.add(currencyTypeComboBox);
                currencyTypeComboBox.setBounds(165, 75, 84, 30);
                contentPanel.add(balanceTextField);
                balanceTextField.setBounds(165, 120, 85, balanceTextField.getPreferredSize().height);

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

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> {
                    try {
                        open(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JLabel label1;
    private JLabel currencyTypeLabel;
    private JLabel balanceLabel;
    private JComboBox<String> accountTypeComboBox;
    private JComboBox<String> currencyTypeComboBox;
    private JTextField balanceTextField;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
