/*
 * Created by JFormDesigner on Sat Dec 10 23:50:46 EST 2022
 */

package view;

import controller.AccountController;
import controller.StockController;
import controller.TransactionController;
import dao.AccountDao;
import model.Account;
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
    private AccountDao accountDao = new AccountDao();


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
        new GUICustomerMoneyWindow(userAccounts, userInfo, userName).setVisible(true);
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
        accountIDLabel = new JLabel();
        balanceLabel = new JLabel();
        realizedLabel = new JLabel();
        unrealizedLabel = new JLabel();
        accountIDTextField = new JTextField();
        balanceTextField = new JTextField();
        realizedTextField = new JTextField();
        unrealizedTextField = new JTextField();
        buttonBar = new JPanel();
        buyButton = new JButton();
        sellButton = new JButton();
        checkHeldButton = new JButton();
        checkMarketButton = new JButton();
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

                //---- accountIDLabel ----
                accountIDLabel.setText("Security Account ID");
                contentPanel.add(accountIDLabel);
                accountIDLabel.setBounds(new Rectangle(new Point(50, 25), accountIDLabel.getPreferredSize()));

                //---- balanceLabel ----
                balanceLabel.setText("balance");
                contentPanel.add(balanceLabel);
                balanceLabel.setBounds(50, 55, 120, 16);

                //---- realizedLabel ----
                realizedLabel.setText("realized profit");
                contentPanel.add(realizedLabel);
                realizedLabel.setBounds(50, 90, 120, 16);

                //---- unrealizedLabel ----
                unrealizedLabel.setText("unrealized profit");
                contentPanel.add(unrealizedLabel);
                unrealizedLabel.setBounds(50, 125, 120, 16);

                //---- accountIDTextField ----
                accountIDTextField.setEditable(false);
                contentPanel.add(accountIDTextField);
                accountIDTextField.setBounds(200, 20, 125, accountIDTextField.getPreferredSize().height);

                //---- balanceTextField ----
                balanceTextField.setEditable(false);
                contentPanel.add(balanceTextField);
                balanceTextField.setBounds(200, 55, 125, 30);

                //---- realizedTextField ----
                realizedTextField.setEditable(false);
                contentPanel.add(realizedTextField);
                realizedTextField.setBounds(200, 90, 125, 30);

                //---- unrealizedTextField ----
                unrealizedTextField.setEditable(false);
                contentPanel.add(unrealizedTextField);
                unrealizedTextField.setBounds(200, 125, 125, 30);

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

                //---- buyButton ----
                buyButton.setText("Buy stock");
                buyButton.addActionListener(e -> {
                    buy(e);
                });
                buttonBar.add(buyButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- sellButton ----
                sellButton.setText("Sell stock");
                sellButton.addActionListener(e -> sell(e));
                buttonBar.add(sellButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- checkHeldButton ----
                checkHeldButton.setText("Check held stocks ");
                checkHeldButton.addActionListener(e -> {
                    try {
                        checkHeld(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBar.add(checkHeldButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- checkMarketButton ----
                checkMarketButton.setText("Check stock market");
                checkMarketButton.addActionListener(e -> {
                    try {
                        checkMarket(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBar.add(checkMarketButton, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- backButton ----
                backButton.setText("back");
                backButton.addActionListener(e -> {
                    try {
                        back(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                buttonBar.add(backButton, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
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
                accountIDTextField.setText(((SecurityAccount) account).getAccountID()+"");
                balanceTextField.setText(((SecurityAccount) account).getBalanceByCurrency(CurrencyType.USD)+"");

                realizedTextField.setText(accountDao.getRealizedProfit(((SecurityAccount) account).getAccountID())+"");
//                unrealizedTextField.setText(((SecurityAccount) account).getUnrealizedProfit()+"");
                break;
            }

        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel accountIDLabel;
    private JLabel balanceLabel;
    private JLabel realizedLabel;
    private JLabel unrealizedLabel;
    private JTextField accountIDTextField;
    private JTextField balanceTextField;
    private JTextField realizedTextField;
    private JTextField unrealizedTextField;
    private JPanel buttonBar;
    private JButton buyButton;
    private JButton sellButton;
    private JButton checkHeldButton;
    private JButton checkMarketButton;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}