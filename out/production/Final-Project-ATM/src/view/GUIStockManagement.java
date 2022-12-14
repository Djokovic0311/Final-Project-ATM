/*
 * Created by JFormDesigner on Tue Dec 13 16:07:58 EST 2022
 */

package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUIStockManagement extends JFrame {
    public GUIStockManagement() {
        initComponents();
    }

    private void checkStock(ActionEvent e) throws Exception {
        dispose();
        new  GUIDisplayStock(null, null,"","held").setVisible(true);
    }

    private void updatePrice(ActionEvent e) {
        dispose();
        new GUIUpdateStockPrice("update").setVisible(true);
    }

    private void addStock(ActionEvent e) {
        dispose();
        new GUIUpdateStockPrice("add").setVisible(true);
    }

    private void back(ActionEvent e) {
        dispose();
        new GUIManagerHomepage().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        updatePriceButton = new JButton();
        checkStockButton = new JButton();
        addStockButton = new JButton();
        buttonBar = new JPanel();
        backButton = new JButton();

        //======== this ========
        setTitle("Manager Stock Center");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(null);

                //---- updatePriceButton ----
                updatePriceButton.setText("Update stock Price");
                updatePriceButton.addActionListener(e -> updatePrice(e));
                contentPanel.add(updatePriceButton);
                updatePriceButton.setBounds(new Rectangle(new Point(75, 25), updatePriceButton.getPreferredSize()));

                //---- checkStockButton ----
                checkStockButton.setText("Check Stock Market");
                checkStockButton.addActionListener(e -> {
                    try {
                        checkStock(e);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });
                contentPanel.add(checkStockButton);
                checkStockButton.setBounds(75, 75, 140, 30);

                //---- addStockButton ----
                addStockButton.setText("Add a stock");
                addStockButton.addActionListener(e -> addStock(e));
                contentPanel.add(addStockButton);
                addStockButton.setBounds(75, 125, 105, 30);

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
                backButton.setText("back");
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
    private JButton updatePriceButton;
    private JButton checkStockButton;
    private JButton addStockButton;
    private JPanel buttonBar;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
