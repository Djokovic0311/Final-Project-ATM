import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import org.jdesktop.layout.GroupLayout;
/*
 * Created by JFormDesigner on Mon Dec 12 22:41:18 EST 2022
 */



/**
 * @author Mingxin Li
 */
public class GUIAddStockWindow extends JFrame {
    public GUIAddStockWindow() {
        initComponents();
    }

    private void add(ActionEvent e) {
        int stockID = Integer.parseInt(stockTextField.getText());
        int quantity = Integer.parseInt(quantityTextField.getText());
        int quantity = Integer.parseInt(priceTextField.getText());
        int status = stockController.trade(userName,stockID,quantity,tradeType);
        if(status == atmConstant.getSUCCESS()) {
            JOptionPane.showMessageDialog(null, "Success!!");
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Something wrong! Please Try it again!");
        }
    }

    private void cancel(ActionEvent e) {
        dispose();
        new GUIBankerHomePage(userAccounts, userInfo, userName).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Mingxin Li
        this2 = new JFrame();
        dialogPane2 = new JPanel();
        contentPanel2 = new JPanel();
        stockLabel = new JLabel();
        priceLabel = new JLabel();
        stockTextField = new JTextField();
        priceTextField = new JTextField();
        quantityLabel = new JLabel();
        quantityTextField = new JTextField();
        buttonBar2 = new JPanel();
        tradeButton = new JButton();
        cancelButton2 = new JButton();

        //======== this2 ========
        {
            this2.setTitle("Add Stock");
            Container this2ContentPane = this2.getContentPane();
            this2ContentPane.setLayout(new BorderLayout());

            //======== dialogPane2 ========
            {
                dialogPane2.setBorder(new EmptyBorder(12, 12, 12, 12));
                dialogPane2.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
                0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
                . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
                red) ,dialogPane2. getBorder( )) ); dialogPane2. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){  public void propertyChange (java .
                beans .PropertyChangeEvent e) {if ("bor\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
                dialogPane2.setLayout(new BorderLayout());

                //======== contentPanel2 ========
                {
                    contentPanel2.setLayout(null);

                    //---- stockLabel ----
                    stockLabel.setText("stockID");
                    contentPanel2.add(stockLabel);
                    stockLabel.setBounds(new Rectangle(new Point(85, 45), stockLabel.getPreferredSize()));

                    //---- priceLabel ----
                    priceLabel.setText("Price");
                    contentPanel2.add(priceLabel);
                    priceLabel.setBounds(new Rectangle(new Point(85, 100), priceLabel.getPreferredSize()));
                    contentPanel2.add(stockTextField);
                    stockTextField.setBounds(170, 40, 105, 30);
                    contentPanel2.add(priceTextField);
                    priceTextField.setBounds(170, 95, 105, 30);

                    //---- quantityLabel ----
                    quantityLabel.setText("Quantity");
                    contentPanel2.add(quantityLabel);
                    quantityLabel.setBounds(85, 150, 70, 16);
                    contentPanel2.add(quantityTextField);
                    quantityTextField.setBounds(170, 145, 105, 30);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < contentPanel2.getComponentCount(); i++) {
                            Rectangle bounds = contentPanel2.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = contentPanel2.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        contentPanel2.setMinimumSize(preferredSize);
                        contentPanel2.setPreferredSize(preferredSize);
                    }
                }
                dialogPane2.add(contentPanel2, BorderLayout.CENTER);

                //======== buttonBar2 ========
                {
                    buttonBar2.setBorder(new EmptyBorder(12, 0, 0, 0));
                    buttonBar2.setLayout(new GridBagLayout());
                    ((GridBagLayout)buttonBar2.getLayout()).columnWidths = new int[] {0, 85, 80};
                    ((GridBagLayout)buttonBar2.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                    //---- tradeButton ----
                    tradeButton.setText("Add");
                    tradeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            add(e);
                        }
                    });
                    buttonBar2.add(tradeButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- cancelButton2 ----
                    cancelButton2.setText("Cancel");
                    cancelButton2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            cancel(e);
                        }
                    });
                    buttonBar2.add(cancelButton2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                dialogPane2.add(buttonBar2, BorderLayout.SOUTH);
            }
            this2ContentPane.add(dialogPane2, BorderLayout.CENTER);
            this2.pack();
            this2.setLocationRelativeTo(this2.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Mingxin Li
    private JFrame this2;
    private JPanel dialogPane2;
    private JPanel contentPanel2;
    private JLabel stockLabel;
    private JLabel priceLabel;
    private JTextField stockTextField;
    private JTextField priceTextField;
    private JLabel quantityLabel;
    private JTextField quantityTextField;
    private JPanel buttonBar2;
    private JButton tradeButton;
    private JButton cancelButton2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
