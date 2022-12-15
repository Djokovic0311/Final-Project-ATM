/*
 * Created by JFormDesigner on Tue Dec 13 16:12:19 EST 2022
 */

package view;

import controller.StockController;
import utils.ATMConstant;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUIUpdateStockPrice extends JFrame {
    StockController stockController = new StockController();
    ATMConstant atmConstant = new ATMConstant();
    private String type;
    public GUIUpdateStockPrice(String type) {
        this.type = type;
        initComponents();
    }

    private void ok(ActionEvent e) {
        double price = Double.parseDouble(priceTextField.getText());
        int stockID = Integer.parseInt(stockIDTextField.getText());
        int status;
        if(Objects.equals(type, "update")){
            status = stockController.updateStock(stockID,price);
        }

        else {
            status = stockController.addStock(stockID,price);
        }
        if(status == atmConstant.getSUCCESS()){
            JOptionPane.showMessageDialog(null, "Success!!");
            setVisible(false);
            new GUIStockManagement().setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Fail to update!");
        }
    }

    private void cancel(ActionEvent e) {
        dispose();
        new GUIStockManagement().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        stockIDLabel = new JLabel();
        stockIDTextField = new JTextField();
        priceTextField = new JTextField();
        priceLabel = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

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

                //---- stockIDLabel ----
                stockIDLabel.setText("StockID");
                contentPanel.add(stockIDLabel);
                stockIDLabel.setBounds(new Rectangle(new Point(80, 55), stockIDLabel.getPreferredSize()));
                contentPanel.add(stockIDTextField);
                stockIDTextField.setBounds(180, 55, 120, stockIDTextField.getPreferredSize().height);
                contentPanel.add(priceTextField);
                priceTextField.setBounds(185, 110, 110, priceTextField.getPreferredSize().height);

                //---- priceLabel ----
                priceLabel.setText("Price");
                contentPanel.add(priceLabel);
                priceLabel.setBounds(new Rectangle(new Point(80, 115), priceLabel.getPreferredSize()));

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
                okButton.addActionListener(e -> ok(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JLabel stockIDLabel;
    private JTextField stockIDTextField;
    private JTextField priceTextField;
    private JLabel priceLabel;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
