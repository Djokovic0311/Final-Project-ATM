/*
 * Created by JFormDesigner on Sat Dec 10 23:50:46 EST 2022
 */

package view;

import controller.TransactionController;
import model.CurrencyType;
import model.SecurityAccount;
import utils.ATMConstant;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUIStock extends JFrame {
    private List userAccounts;
    private List userInfo;
    private String userName;

    private TransactionController transactionController = new TransactionController();
    ATMConstant atmConstant = new ATMConstant();
    public GUIStock(List userAccounts, List userInfo, String userName) {
        this.userName = userName;
        this.userInfo = userInfo;
        this.userAccounts = userAccounts;
        initComponents();
        showSecurityAccount();
    }

    private void back(ActionEvent e) {
        dispose();
        setVisible(false);
        new GUICustomerMoneyWindow(userAccounts, userInfo, userName);
    }

    private void button1(ActionEvent e) {
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        security = new JPanel();
        buttonBar = new JPanel();
        backButton = new JButton();
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

                //---- button1 ----
                button1.setText("Buy stock");
                button1.addActionListener(e -> button1(e));
                contentPanel.add(button1);
                button1.setBounds(new Rectangle(new Point(45, 120), button1.getPreferredSize()));

                //---- button2 ----
                button2.setText("Sell stock");
                contentPanel.add(button2);
                button2.setBounds(240, 120, 78, 30);

                //---- button3 ----
                button3.setText("Check held stocks ");
                contentPanel.add(button3);
                button3.setBounds(25, 160, 140, 30);

                //---- button4 ----
                button4.setText("Check stock market");
                contentPanel.add(button4);
                button4.setBounds(215, 160, 145, 30);

                //======== security ========
                {
                    security.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < security.getComponentCount(); i++) {
                            Rectangle bounds = security.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = security.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        security.setMinimumSize(preferredSize);
                        security.setPreferredSize(preferredSize);
                    }
                }
                contentPanel.add(security);
                security.setBounds(new Rectangle(new Point(150, 40), security.getPreferredSize()));

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

                //---- backButton ----
                backButton.setText("back");
                backButton.addActionListener(e -> back(e));
                buttonBar.add(backButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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

    private void showSecurityAccount() {
        for (Object account : userAccounts) {
            if (account instanceof SecurityAccount) {
                JPanel oneAccount = new JPanel();
                oneAccount.setBorder(BorderFactory.createTitledBorder("Amount " + ((SecurityAccount) account).getAccountID()));
                oneAccount.setLayout(new GridLayout(((SecurityAccount) account).getBalance().size(), 2, 0, 5));
                security.add(oneAccount);
                for (CurrencyType type : ((SecurityAccount) account).getBalance().keySet()) {
                    JLabel t = new JLabel(type.name());
                    t.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
                    JLabel b = new JLabel(String.valueOf(((SecurityAccount) account).getBalance().get(type)));
                    b.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
                    oneAccount.add(t);
                    oneAccount.add(b);
                }
            }
        }
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel security;
    private JPanel buttonBar;
    private JButton backButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
