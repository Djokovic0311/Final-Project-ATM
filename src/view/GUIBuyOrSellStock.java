/*
 * Created by JFormDesigner on Sun Dec 11 01:17:26 EST 2022
 */

package view;

import controller.StockController;
import controller.TransactionController;
import utils.ATMConstant;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUIBuyOrSellStock extends JFrame {
    private List userAccounts;
    private List userInfo;
    private String userName;

    private StockController stockController = new StockController();
    private String tradeType;
    ATMConstant atmConstant = new ATMConstant();
    public GUIBuyOrSellStock(List userAccounts, List userInfo, String userName,String tradeType) {
        this.userName = userName;
        this.userInfo = userInfo;
        this.userAccounts = userAccounts;
        this.tradeType = tradeType;
        initComponents();
    }
    
    private void cancel(ActionEvent e) throws Exception {
        dispose();
        new GUIStock(userAccounts, userInfo, userName).setVisible(true);
    }

    private void trade(ActionEvent e) {
        int stockID = Integer.parseInt(stockTextField.getText());
        int quantity = Integer.parseInt(quantityTextField.getText());

        int status = stockController.trade(userName,stockID,quantity,tradeType);
        if(status == atmConstant.getSUCCESS()) {
            JOptionPane.showMessageDialog(null, "Success!!");
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Something wrong! Please Try it again!");
        }
        
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        stockLabel = new JLabel();
        stockTextField = new JTextField();
        quantityLabel = new JLabel();
        quantityTextField = new JTextField();
        buttonBar = new JPanel();
        tradeButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Trade Stocks");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- stockLabel ----
                stockLabel.setText("stockID");
                contentPanel.add(stockLabel);
                stockLabel.setBounds(new Rectangle(new Point(85, 45), stockLabel.getPreferredSize()));
                contentPanel.add(stockTextField);
                stockTextField.setBounds(170, 40, 105, stockTextField.getPreferredSize().height);

                //---- quantityLabel ----
                quantityLabel.setText("quantity");
                contentPanel.add(quantityLabel);
                quantityLabel.setBounds(new Rectangle(new Point(85, 100), quantityLabel.getPreferredSize()));
                contentPanel.add(quantityTextField);
                quantityTextField.setBounds(170, 95, 105, 30);

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

                //---- tradeButton ----
                tradeButton.setText("Trade");
                tradeButton.addActionListener(e -> trade(e));
                buttonBar.add(tradeButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JLabel stockLabel;
    private JTextField stockTextField;
    private JLabel quantityLabel;
    private JTextField quantityTextField;
    private JPanel buttonBar;
    private JButton tradeButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
