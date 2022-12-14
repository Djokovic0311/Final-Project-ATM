/*
 * Created by JFormDesigner on Sun Dec 11 23:53:22 EST 2022
 */

package view;

import controller.AccountController;
import controller.StockController;
import controller.TransactionController;
import model.Account;
import model.CurrencyType;
import utils.ATMConstant;

import java.awt.*;
import java.awt.event.*;
import java.util.Currency;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUITranscurrency extends JFrame {
    private java.util.List userAccounts;
    private List userInfo;
    private String userName;

    private TransactionController transactionController = new TransactionController();
    private AccountController accountController = new AccountController();
    private StockController stockController = new StockController();
    ATMConstant atmConstant = new ATMConstant();

    public GUITranscurrency() throws Exception {
        this.userAccounts = accountController.getAccountsForCustomer(userName);

        this.userInfo = userInfo;
        this.userName = userName;
        initComponents();
    }


    private void ok(ActionEvent e) throws Exception {
        double amount = Double.parseDouble(amountTextField.getText());
        CurrencyType from = CurrencyType.valueOf(fromCurrencyComboBox.getSelectedItem().toString());
        CurrencyType to = CurrencyType.valueOf(toCurrencyComboBox.getSelectedItem().toString());
        int accountID = Integer.parseInt(accountIDTextField.getText());
        int status = accountController.transcurrency(userName,accountID,from,to,amount);
        if(status == atmConstant.getSUCCESS()){
            JOptionPane.showMessageDialog(null, "Success!!");
            setVisible(false);
            new GUICustomerMoneyWindow(userInfo,userAccounts,userName).setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Something wrong! Please Try it again!");
        }
    }

    private void cancel(ActionEvent e) throws Exception {
        dispose();
        new GUICustomerMoneyWindow(userInfo,userAccounts,userName).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        fromCurrencyLabel = new JLabel();
        toCurrencyLabel = new JLabel();
        fromCurrencyComboBox = new JComboBox<>();
        toCurrencyComboBox = new JComboBox<>();
        amountLabel = new JLabel();
        amountTextField = new JTextField();
        accountLabel = new JLabel();
        accountIDTextField = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
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

                //---- fromCurrencyLabel ----
                fromCurrencyLabel.setText("CurrencyType");
                contentPanel.add(fromCurrencyLabel);
                fromCurrencyLabel.setBounds(50, 60, 120, 20);

                //---- toCurrencyLabel ----
                toCurrencyLabel.setText("CurrencyType");
                contentPanel.add(toCurrencyLabel);
                toCurrencyLabel.setBounds(50, 105, 120, 20);

                //---- fromCurrencyComboBox ----
                fromCurrencyComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "USD",
                    "EUR",
                    "GBP",
                    "CNY",
                    "HKD"
                }));
                contentPanel.add(fromCurrencyComboBox);
                fromCurrencyComboBox.setBounds(170, 60, 84, 30);

                //---- toCurrencyComboBox ----
                toCurrencyComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "USD",
                    "EUR",
                    "GBP",
                    "CNY",
                    "HKD"
                }));
                contentPanel.add(toCurrencyComboBox);
                toCurrencyComboBox.setBounds(170, 100, 84, 30);

                //---- amountLabel ----
                amountLabel.setText("Amount");
                contentPanel.add(amountLabel);
                amountLabel.setBounds(50, 150, 47, 16);
                contentPanel.add(amountTextField);
                amountTextField.setBounds(170, 145, 85, 30);

                //---- accountLabel ----
                accountLabel.setText("AccountID");
                contentPanel.add(accountLabel);
                accountLabel.setBounds(50, 25, 63, 16);
                contentPanel.add(accountIDTextField);
                accountIDTextField.setBounds(170, 25, 120, 30);

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
                        ok(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JLabel fromCurrencyLabel;
    private JLabel toCurrencyLabel;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JLabel amountLabel;
    private JTextField amountTextField;
    private JLabel accountLabel;
    private JTextField accountIDTextField;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
