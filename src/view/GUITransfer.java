/*
 * Created by JFormDesigner on Sat Dec 10 18:11:49 EST 2022
 */

package view;

import controller.TransactionController;
import model.CurrencyType;
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
public class GUITransfer extends JFrame {
    private java.util.List userAccounts;
    private List userInfo;
    private String userName;

    private TransactionController transactionController = new TransactionController();
    ATMConstant atmConstant = new ATMConstant();

    public GUITransfer(List userAccounts, List userInfo, String userName) {
        this.userName = userName;
        this.userInfo = userInfo;
        this.userAccounts = userAccounts;
        initComponents();
    }

    private void cancel(ActionEvent e) {
        dispose();
        setVisible(false);
        new GUICustomerMoneyWindow(userAccounts, userInfo, userName);
    }

    private void transfer(ActionEvent e) {
        CurrencyType currencyType = CurrencyType.valueOf(Objects.requireNonNull(currencyTypeComboBox.getSelectedItem()).toString());
        double amount = Double.parseDouble(amountTextField.getText());
        int fromAccountID = Integer.parseInt(fromAccountIDTextField.getText());
        int toAccountID = Integer.parseInt(toAccountIDTextField.getText());
        int customerID = Utils.createHashCodeForPersonId(userName);
        int status = transactionController.transfer(customerID,fromAccountID, toAccountID,amount,currencyType);
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
        CurrencyTypeLabel = new JLabel();
        currencyTypeComboBox = new JComboBox<>();
        amountTextField = new JTextField();
        amountLabel = new JLabel();
        toAccountLabel = new JLabel();
        toAccountIDTextField = new JTextField();
        fromAccountLabel = new JLabel();
        fromAccountIDTextField = new JTextField();
        buttonBar = new JPanel();
        transferButton = new JButton();
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

                //---- CurrencyTypeLabel ----
                CurrencyTypeLabel.setText("CurrencyType");
                contentPanel.add(CurrencyTypeLabel);
                CurrencyTypeLabel.setBounds(80, 105, 120, 20);

                //---- currencyTypeComboBox ----
                currencyTypeComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "USD",
                    "EUR",
                    "GBP",
                    "CNY",
                    "HKD"
                }));
                contentPanel.add(currencyTypeComboBox);
                currencyTypeComboBox.setBounds(195, 100, 84, 30);
                contentPanel.add(amountTextField);
                amountTextField.setBounds(195, 145, 85, 30);

                //---- amountLabel ----
                amountLabel.setText("Amount");
                contentPanel.add(amountLabel);
                amountLabel.setBounds(new Rectangle(new Point(80, 150), amountLabel.getPreferredSize()));

                //---- toAccountLabel ----
                toAccountLabel.setText("To AccountID");
                contentPanel.add(toAccountLabel);
                toAccountLabel.setBounds(80, 65, 85, 16);
                contentPanel.add(toAccountIDTextField);
                toAccountIDTextField.setBounds(195, 60, 120, 30);

                //---- fromAccountLabel ----
                fromAccountLabel.setText("From AccountID");
                contentPanel.add(fromAccountLabel);
                fromAccountLabel.setBounds(80, 30, 100, 16);
                contentPanel.add(fromAccountIDTextField);
                fromAccountIDTextField.setBounds(195, 25, 120, 30);

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

                //---- transferButton ----
                transferButton.setText("Transfer");
                transferButton.addActionListener(e -> transfer(e));
                buttonBar.add(transferButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JLabel CurrencyTypeLabel;
    private JComboBox<String> currencyTypeComboBox;
    private JTextField amountTextField;
    private JLabel amountLabel;
    private JLabel toAccountLabel;
    private JTextField toAccountIDTextField;
    private JLabel fromAccountLabel;
    private JTextField fromAccountIDTextField;
    private JPanel buttonBar;
    private JButton transferButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
