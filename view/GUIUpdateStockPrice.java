
package view;

import controller.AccountController;
import controller.StockController;
import controller.TransactionController;
import dao.StockDao;

import org.jdesktop.layout.GroupLayout;
import utils.ATMConstant;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

public class GUIUpdateStockPrice extends JFrame {
    private List userInfo;
    private String userName;
    private StockController stockController = new StockController();
    ATMConstant atmConstant = new ATMConstant();
    public GUIUpdateStockPrice(List userInfo, String userName) {
        this.userInfo = userInfo;
        this.userName = userName;
        initComponents();
    }
    
    private void cancel(ActionEvent e) throws Exception {
        dispose();
        new GUIBankerHomePage(userInfo, userName).setVisible(true);
    }

    private void update(ActionEvent e) {
        int stockID = Integer.parseInt(stockTextField.getText());
        int nprice = Integer.parseInt(priceTextField.getText());
        StockDao stock = new StockDao();
        stock.updatePriceByID(stockID, nprice);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Mingxin Li
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        stockLabel = new JLabel();
        stockTextField = new JTextField();
        priceLabel = new JLabel();
        priceTextField = new JTextField();
        buttonBar = new JPanel();
        OKButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("Update Stocks");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
            .EmptyBorder(0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax
            .swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,
            12),java.awt.Color.red),dialogPane. getBorder()));dialogPane. addPropertyChangeListener(new java.beans
            .PropertyChangeListener(){ public void propertyChange(java.beans.PropertyChangeEvent e){if("bor\u0064er".equals(e.
            getPropertyName()))throw new RuntimeException();}});
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

                //---- priceLabel ----
                priceLabel.setText("New Price");
                contentPanel.add(priceLabel);
                priceLabel.setBounds(new Rectangle(new Point(85, 100), priceLabel.getPreferredSize()));
                contentPanel.add(priceTextField);
                priceTextField.setBounds(170, 95, 105, 30);

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

                //---- OKButton ----
                OKButton.setText("Comfirm");
                OKButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        update(e);
                    }
                });
                buttonBar.add(OKButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
							cancel(e);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
    // Generated using JFormDesigner Evaluation license - Mingxin Li
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel stockLabel;
    private JTextField stockTextField;
    private JLabel priceLabel;
    private JTextField priceTextField;
    private JPanel buttonBar;
    private JButton OKButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
