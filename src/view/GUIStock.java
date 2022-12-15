/*
 * Created by JFormDesigner on Sat Dec 10 23:50:46 EST 2022
 */

package view;

import controller.AccountController;
import controller.StockController;
import controller.TransactionController;
import model.CurrencyType;
import model.SecurityAccount;
import utils.ATMConstant;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Objects;
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
    private AccountController accountController = new AccountController();
    private StockController stockController = new StockController();
    ATMConstant atmConstant = new ATMConstant();
    public GUIStock(List userAccounts, List userInfo, String userName) throws Exception {
        this.userName = userName;
        this.userInfo = userInfo;
        if(!Objects.equals(userName, "banker"))
            this.userAccounts = accountController.getAccountsForCustomer(userName);
        initComponents();
        if(!Objects.equals(userName, "banker"))
            showSecurityAccount();
    }

    private void back(ActionEvent e) throws Exception {
        dispose();
        setVisible(false);
        new GUICustomerMoneyWindow(userAccounts, userInfo, userName);
    }

    private void buy(ActionEvent e) {
        dispose();
        new GUIBuyOrSellStock(userAccounts, userInfo, userName,"buy").setVisible(true);
    }



    private void sell(ActionEvent e) {
        dispose();
        new GUIBuyOrSellStock(userAccounts, userInfo, userName,"sell").setVisible(true);
    }

    private void checkHeld(ActionEvent e) throws Exception {
        dispose();
        new GUIDisplayStock(userAccounts,userInfo,userName,"held").setVisible(true);
    }


    private void checkMarket(ActionEvent e) throws Exception {
        dispose();
        new GUIDisplayStock(userAccounts,userInfo,userName,"market").setVisible(true);
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        buyButton = new JButton();
        sellButton = new JButton();
        checkHeldButton = new JButton();
        checkMarketButton = new JButton();
        security = new JPanel();
        buttonBar = new JPanel();
        backButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- buyButton ----
                buyButton.setText("Buy stock");
                buyButton.addActionListener(e -> {
			button1(e);
			buy(e);
		});
                contentPanel.add(buyButton);
                buyButton.setBounds(new Rectangle(new Point(45, 120), buyButton.getPreferredSize()));

                //---- sellButton ----
                sellButton.setText("Sell stock");
                sellButton.addActionListener(e -> sell(e));
                contentPanel.add(sellButton);
                sellButton.setBounds(240, 120, 78, 30);

                //---- checkHeldButton ----
                checkHeldButton.setText("Check held stocks ");
                checkHeldButton.addActionListener(e -> checkHeld(e));
                contentPanel.add(checkHeldButton);
                checkHeldButton.setBounds(25, 160, 140, 30);

                //---- checkMarketButton ----
                checkMarketButton.setText("Check stock market");
                checkMarketButton.addActionListener(e -> checkMarket(e));
                contentPanel.add(checkMarketButton);
                checkMarketButton.setBounds(215, 160, 145, 30);

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
                backButton.addActionListener(e -> {
			back(e);
			back(e);
		});
                buttonBar.add(backButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
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
    private JButton buyButton;
    private JButton sellButton;
    private JButton checkHeldButton;
    private JButton checkMarketButton;
    private JPanel security;
    private JPanel buttonBar;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
