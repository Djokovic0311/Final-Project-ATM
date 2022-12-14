/*
 * Created by JFormDesigner on Sun Dec 11 22:21:30 EST 2022
 */

package view;

import controller.AccountController;
import controller.LoanController;
import controller.StockController;
import controller.TransactionController;
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
public class GUIRequireLoan extends JFrame {
    private List userAccounts;
    private List userInfo;
    private String userName;

    private TransactionController transactionController = new TransactionController();
    private AccountController accountController = new AccountController();
    private LoanController loanController = new LoanController();
    ATMConstant atmConstant = new ATMConstant();

    public GUIRequireLoan(List userInfo, String userName) {
        this.userInfo = userInfo;
        this.userName = userName;
        initComponents();
    }

    private void cancel(ActionEvent e) throws Exception {
        dispose();
        new GUILoan(userInfo,userName).setVisible(true);
    }

    private void apply(ActionEvent e) throws Exception {
        double amount = Double.parseDouble(amountField.getText());
        CurrencyType currencyType = CurrencyType.valueOf(Objects.requireNonNull(currencyTypeComboBox.getSelectedItem()).toString());
        long timestamp = Utils.getTimestamp();
        int status = loanController.requireLoan(userName,amount,1,timestamp,currencyType);
        if(status == atmConstant.getSUCCESS()){
            JOptionPane.showMessageDialog(null, "Success!!");
            setVisible(false);
            new GUILoan(userInfo,userName).setVisible(true);
        } else{
            JOptionPane.showMessageDialog(null, "Something wrong! Please Try it again!");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        amountLabel = new JLabel();
        currencyTypeLabeel = new JLabel();
        currencyTypeComboBox = new JComboBox<>();
        amountField = new JTextField();
        buttonBar = new JPanel();
        applyButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Apply For Loan");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- amountLabel ----
                amountLabel.setText("Amount");
                contentPanel.add(amountLabel);
                amountLabel.setBounds(new Rectangle(new Point(65, 45), amountLabel.getPreferredSize()));

                //---- currencyTypeLabeel ----
                currencyTypeLabeel.setText("CurrencyType");
                contentPanel.add(currencyTypeLabeel);
                currencyTypeLabeel.setBounds(new Rectangle(new Point(65, 80), currencyTypeLabeel.getPreferredSize()));

                //---- currencyTypeComboBox ----
                currencyTypeComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "USD",
                    "EUR",
                    "GBP",
                    "CNY",
                    "HKD"
                }));
                contentPanel.add(currencyTypeComboBox);
                currencyTypeComboBox.setBounds(175, 75, 84, 30);
                contentPanel.add(amountField);
                amountField.setBounds(175, 40, 90, amountField.getPreferredSize().height);

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

                //---- applyButton ----
                applyButton.setText("Apply");
                applyButton.addActionListener(e -> {
                    try {
                        apply(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBar.add(applyButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JLabel amountLabel;
    private JLabel currencyTypeLabeel;
    private JComboBox<String> currencyTypeComboBox;
    private JTextField amountField;
    private JPanel buttonBar;
    private JButton applyButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
