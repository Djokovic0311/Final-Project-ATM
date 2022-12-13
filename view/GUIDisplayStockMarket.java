package view;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
/*
 * Created by JFormDesigner on Tue Dec 13 17:11:41 EST 2022
 */

import controller.AccountController;
import controller.StockController;
import model.marketStock;
import utils.ATMConstant;



/**
 * @author Mingxin Li
 */
public class GUIDisplayStockMarket extends JFrame {
    private ArrayList stocks;
    private List userInfo;
    private String userName;
    private AccountController accountController = new AccountController();
    private StockController stockController = new StockController();
    ATMConstant atmConstant = new ATMConstant();
    public GUIDisplayStockMarket(List userInfo, String userName) throws Exception {
        this.userName = userName;
        this.userInfo = userInfo;
        this.stocks = new ArrayList<marketStock>();
        this.stocks = stockController.showMarketStocks();
        initComponents();
        fillTable();
    }

    private void back(ActionEvent e) {
        dispose();
        new GUIBankerHomePage((java.util.List) userInfo, userName).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Mingxin Li
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        stockTable = new JTable();
        buttonBar = new JPanel();
        backButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
            .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax
            . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,
            12 ) ,java . awt. Color .red ) ,dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans
            .PropertyChangeListener ( ){  public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bor\u0064er" .equals ( e.
            getPropertyName () ) )throw new RuntimeException( ) ;} } );
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
                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        back(e);
                    }
                });
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


    private void fillTable() throws Exception {
        DefaultTableModel defaultModel = (DefaultTableModel) stockTable.getModel();
        for(Object stock : stocks) {
            Vector v = new Vector();
            int stockID = ((marketStock) stock).getStockID();
            double price = ((marketStock) stock).getPrice();
            v.addElement(stockID);
            v.addElement(price);
            defaultModel.addRow(v);
            stockTable.setModel(defaultModel);
        }
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Mingxin Li
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable stockTable;
    private JPanel buttonBar;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
