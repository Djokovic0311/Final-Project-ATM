/*
 * Created by JFormDesigner on Fri Dec 09 12:16:25 EST 2022
 */

package view;

import java.awt.event.*;
import model.*;

import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Jiahang Li
 */
public class GUICustomerAccountWindow extends JFrame {
    private List userAccounts;
    private String username;
    public GUICustomerAccountWindow(List userAccounts,String username) {
        this.userAccounts = userAccounts;
        this.username = username;
        initComponents();
        fillInfo(userAccounts);
    }

    private void openAccount(ActionEvent e) {
        // TODO add your code here
        dispose();
        new GUICustomerOpenAccount(userAccounts, username).setVisible(true);
    }

    private void closeAccount(ActionEvent e) {
        // TODO add your code here
        dispose();
        new GUICustomerCloseAccount(userAccounts,username).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        checking = new JPanel();
        saving = new JPanel();
        loan = new JPanel();
        security = new JPanel();
        buttonBar = new JPanel();
        openAccountButton = new JButton();
        closeAccountButton = new JButton();

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

                //======== checking ========
                {
                    checking.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < checking.getComponentCount(); i++) {
                            Rectangle bounds = checking.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = checking.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        checking.setMinimumSize(preferredSize);
                        checking.setPreferredSize(preferredSize);
                    }
                }
                contentPanel.add(checking);
                checking.setBounds(new Rectangle(new Point(70, 50), checking.getPreferredSize()));

                //======== saving ========
                {
                    saving.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < saving.getComponentCount(); i++) {
                            Rectangle bounds = saving.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = saving.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        saving.setMinimumSize(preferredSize);
                        saving.setPreferredSize(preferredSize);
                    }
                }
                contentPanel.add(saving);
                saving.setBounds(new Rectangle(new Point(85, 140), saving.getPreferredSize()));

                //======== loan ========
                {
                    loan.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < loan.getComponentCount(); i++) {
                            Rectangle bounds = loan.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = loan.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        loan.setMinimumSize(preferredSize);
                        loan.setPreferredSize(preferredSize);
                    }
                }
                contentPanel.add(loan);
                loan.setBounds(new Rectangle(new Point(120, 130), loan.getPreferredSize()));

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
                security.setBounds(new Rectangle(new Point(130, 165), security.getPreferredSize()));

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

                //---- openAccountButton ----
                openAccountButton.setText("Open an account");
                openAccountButton.addActionListener(e -> openAccount(e));
                buttonBar.add(openAccountButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- closeAccountButton ----
                closeAccountButton.setText("Close an account");
                closeAccountButton.addActionListener(e -> closeAccount(e));
                buttonBar.add(closeAccountButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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

    public void fillInfo(List userAccounts){
        // Checking
        checking.removeAll();
        saving.removeAll();
        int checkAccountNum = 0;
        int savingAccountNum = 0;
        int loanAccountNum = 0;
        int securityAccountNum = 0;
        for (Object amount : userAccounts) {
            if (amount instanceof CheckingAccount) {
                checkAccountNum ++;
                JPanel oneAccount = new JPanel();
                oneAccount.setBorder(BorderFactory.createTitledBorder("Amount " + ((CheckingAccount) amount).getAccountID()));
                oneAccount.setLayout(new GridLayout(((CheckingAccount) amount).getBalance().size(), 2, 0, 5));
                checking.add(oneAccount);
                for (CurrencyType type : ((CheckingAccount) amount).getBalance().keySet()) {
                    JLabel t = new JLabel(type.name());
                    t.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
                    JLabel b = new JLabel(String.valueOf(((CheckingAccount) amount).getBalanceByCurrency(type)));
                    b.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
                    oneAccount.add(t);
                    oneAccount.add(b);
                }
            } else if (amount instanceof SavingAccount) {
                savingAccountNum ++;
                JPanel oneAccount = new JPanel();
                oneAccount.setBorder(BorderFactory.createTitledBorder("Amount " + ((SavingAccount) amount).getAccountID()));
                oneAccount.setLayout(new GridLayout(((SavingAccount) amount).getBalance().size(), 2, 0, 5));
                saving.add(oneAccount);
                for (CurrencyType type : ((SavingAccount) amount).getBalance().keySet()) {
                    JLabel t = new JLabel(type.name());
                    t.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
                    JLabel b = new JLabel(String.valueOf(((SavingAccount) amount).getBalance().get(type)));
                    b.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
                    oneAccount.add(t);
                    oneAccount.add(b);
                }
            } else if (amount instanceof LoanAccount) {
                loanAccountNum++;
                JPanel oneAccount = new JPanel();
                oneAccount.setBorder(BorderFactory.createTitledBorder("Amount " + ((LoanAccount) amount).getAccountID()));
                oneAccount.setLayout(new GridLayout(((LoanAccount) amount).getBalance().size(), 2, 0, 5));
                loan.add(oneAccount);
                for (CurrencyType type : ((LoanAccount) amount).getBalance().keySet()) {
                    JLabel t = new JLabel(type.name());
                    t.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
                    JLabel b = new JLabel(String.valueOf(((LoanAccount) amount).getBalance().get(type)));
                    b.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
                    oneAccount.add(t);
                    oneAccount.add(b);
                }
            }
            else if (amount instanceof SecurityAccount) {
                securityAccountNum++;
                JPanel oneAccount = new JPanel();
                oneAccount.setBorder(BorderFactory.createTitledBorder("Amount " + ((SecurityAccount) amount).getAccountID()));
                oneAccount.setLayout(new GridLayout(((SecurityAccount) amount).getBalance().size(), 2, 0, 5));
                security.add(oneAccount);
                for (CurrencyType type : ((SecurityAccount) amount).getBalance().keySet()) {
                    JLabel t = new JLabel(type.name());
                    t.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
                    JLabel b = new JLabel(String.valueOf(((SecurityAccount) amount).getBalance().get(type)));
                    b.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
                    oneAccount.add(t);
                    oneAccount.add(b);
                }
            }
        }
        checking.setLayout(new GridLayout(checkAccountNum + 1, 1));
        saving.setLayout(new GridLayout(savingAccountNum + 1, 1));
        loan.setLayout(new GridLayout(loanAccountNum + 1, 1));
        security.setLayout(new GridLayout(loanAccountNum + 1, 1));

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel checking;
    private JPanel saving;
    private JPanel loan;
    private JPanel security;
    private JPanel buttonBar;
    private JButton openAccountButton;
    private JButton closeAccountButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
