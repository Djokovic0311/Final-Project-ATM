import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Mon Dec 12 21:47:23 EST 2022
 */



/**
 * @author Mingxin Li
 */
public class GUIStockManager extends JFrame {
    public GUIStockManager() {
        initComponents();
    }

    private void updatePrice(ActionEvent e){
        dispose();
        GUIUpdateStockPrice usp = new GUIUpdateStockPrice();
    }

    private void back(ActionEvent e) {
        dispose();
        GUIBankerHomePage bhp = new GUIBankerHomePage();
        bhp.setVisible(true);
    }

    private void logout(ActionEvent e) {
        dispose();
        GUILoginWindow lw = new GUILoginWindow();
        bhp.setVisible(true);
    }

    private void addStock(ActionEvent e) {
        dispose();
        new GUIAddStockWindow(stockID, stock).setVisible(true);
    }

    private void checkMarket(ActionEvent e) {
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Mingxin Li
        dialogPane2 = new JPanel();
        contentPanel2 = new JPanel();
        updatePriceButton = new JButton();
        addStockButton = new JButton();
        checkMarketButton = new JButton();
        buttonBar2 = new JPanel();
        backButton = new JButton();
        logoutButton = new JButton();

        //======== this ========
        setTitle("Stock Center");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== dialogPane2 ========
        {
            dialogPane2.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane2.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,dialogPane2. getBorder( )) ); dialogPane2. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){  public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("bor\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            dialogPane2.setLayout(null);

            //======== contentPanel2 ========
            {
                contentPanel2.setLayout(new GridLayout(3, 3, 10, 10));

                //---- updatePriceButton ----
                updatePriceButton.setText("Update Price");
                updatePriceButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        updatePrice(e);
                    }
                });
                contentPanel2.add(updatePriceButton);

                //---- addStockButton ----
                addStockButton.setText("Add Stock");
                addStockButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        addStock(e);
                    }
                });
                contentPanel2.add(addStockButton);

                //---- checkMarketButton ----
                checkMarketButton.setText("Check Stock Market");
                checkMarketButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        checkMarket(e);
                    }
                });
                contentPanel2.add(checkMarketButton);
            }
            dialogPane2.add(contentPanel2);
            contentPanel2.setBounds(12, 12, 373, 198);

            //======== buttonBar2 ========
            {
                buttonBar2.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar2.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar2.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar2.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- backButton ----
                backButton.setText("back");
                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        back(e);
                    }
                });
                buttonBar2.add(backButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- logoutButton ----
                logoutButton.setText("Log out");
                logoutButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        logout(e);
                    }
                });
                buttonBar2.add(logoutButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
            }
            dialogPane2.add(buttonBar2);
            buttonBar2.setBounds(15, 210, 374, buttonBar2.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < dialogPane2.getComponentCount(); i++) {
                    Rectangle bounds = dialogPane2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = dialogPane2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                dialogPane2.setMinimumSize(preferredSize);
                dialogPane2.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(dialogPane2);
        dialogPane2.setBounds(new Rectangle(new Point(0, 0), dialogPane2.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            ((JComponent)contentPane).setMinimumSize(preferredSize);
            ((JComponent)contentPane).setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Mingxin Li
    private JPanel dialogPane2;
    private JPanel contentPanel2;
    private JButton updatePriceButton;
    private JButton addStockButton;
    private JButton checkMarketButton;
    private JPanel buttonBar2;
    private JButton backButton;
    private JButton logoutButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
