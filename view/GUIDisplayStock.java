/*
 * Created by JFormDesigner on Sun Dec 11 14:37:26 EST 2022
 */

package view;

import javax.swing.table.*;
import controller.AccountController;
import controller.StockController;
import controller.TransactionController;
import model.*;
import utils.ATMConstant;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUIDisplayStock extends JFrame {
    private ArrayList stocks;
    private List userAccounts;
    private List userInfo;
    private String userName;

    private TransactionController transactionController = new TransactionController();
    private AccountController accountController = new AccountController();
    private StockController stockController = new StockController();
    ATMConstant atmConstant = new ATMConstant();
    public GUIDisplayStock(List userAccounts, List userInfo, String userName, String type) throws Exception {
        this.userName = userName;
        this.userInfo = userInfo;
        this.userAccounts = accountController.getAccountsForCustomer(userName);
        if(Objects.equals(type, "held")) {
            this.stocks = new ArrayList<customerHeldStock>();
            this.stocks = stockController.showHeldStocks(userName);
        }
        else {
            this.stocks = new ArrayList<marketStock>();
            this.stocks = stockController.showMarketStocks();
        }
        initComponents();
        fillTable();
    }

    private void back(ActionEvent e) throws Exception {
        dispose();
        new GUIStock(userAccounts, userInfo, userName).setVisible(true);
    }

    private void fillTable() throws Exception {
        
        DefaultTableModel defaultModel = (DefaultTableModel) stockTable.getModel();
        for(Object stock : stocks) {
            Vector v = new Vector();
            if(stock instanceof customerHeldStock){
                int stockID = ((customerHeldStock) stock).getStockID();
                long timestamp = ((customerHeldStock) stock).getBuyTime();
                int quantity = ((customerHeldStock) stock).getQuantity();
                double price = ((customerHeldStock) stock).getPrice();

                v.addElement(stockID);
                v.addElement(price);
                v.addElement(timestamp);
                v.addElement(quantity);
            }
            else {
                int stockID = ((marketStock) stock).getStockID();
                double price = ((marketStock) stock).getPrice();
                v.addElement(stockID);
                v.addElement(price);
            }
            
            defaultModel.addRow(v);
            stockTable.setModel(defaultModel);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        stockTable = new JTable();
        buttonBar = new JPanel();
        backButton = new JButton();

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

                //======== scrollPane1 ========
                {

                    //---- stockTable ----
                    stockTable.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            null, null, null, null
                        }
                    ));
                    scrollPane1.setViewportView(stockTable);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(0, 0, 375, 200);

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
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- backButton ----
                backButton.setText("Back");
                backButton.addActionListener(e -> back(e));
                buttonBar.add(backButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JScrollPane scrollPane1;
    private JTable stockTable;
    private JPanel buttonBar;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
