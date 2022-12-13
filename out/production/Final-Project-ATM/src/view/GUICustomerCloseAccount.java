/*
 * Created by JFormDesigner on Sat Dec 10 11:34:00 EST 2022
 */

package view;

import controller.AccountController;
import utils.ATMConstant;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUICustomerCloseAccount extends JFrame {
    private List userAccounts;
    private String username;
    private List userInfo;
    private AccountController accountController = new AccountController();
    ATMConstant atmConstant = new ATMConstant();

    public GUICustomerCloseAccount(List userAccounts, List userinfo, String username) {
        this.userAccounts = userAccounts;
        this.username = username;
        this.userInfo = userinfo;
        initComponents();
    }

    private void cancel(ActionEvent e) {
        dispose();
        new GUICustomerAccountWindow(userAccounts, userInfo, username).setVisible(true);
    }

    private void confirm(ActionEvent e) {
        int accountID = Integer.parseInt(accountIDTextField.getText());
        int status = accountController.closeAccount(username, accountID);

        if(status == atmConstant.getSUCCESS()) {
            JOptionPane.showMessageDialog(null, "Success!!");
            new GUICustomerAccountWindow(userAccounts, userInfo,username).setVisible(true);
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Please try again!!");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        accountIDLabel = new JLabel();
        accountIDTextField = new JTextField();
        buttonBar = new JPanel();
        confirmButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Close an Account");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- accountIDLabel ----
                accountIDLabel.setText("AccountID");
                contentPanel.add(accountIDLabel);
                accountIDLabel.setBounds(new Rectangle(new Point(60, 65), accountIDLabel.getPreferredSize()));
                contentPanel.add(accountIDTextField);
                accountIDTextField.setBounds(new Rectangle(new Point(190, 60), accountIDTextField.getPreferredSize()));

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

                //---- confirmButton ----
                confirmButton.setText("Confirm");
                confirmButton.addActionListener(e -> confirm(e));
                buttonBar.add(confirmButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JLabel accountIDLabel;
    private JTextField accountIDTextField;
    private JPanel buttonBar;
    private JButton confirmButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
