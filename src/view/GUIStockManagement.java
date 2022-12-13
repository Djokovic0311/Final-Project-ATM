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


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        updatePriceButton = new JButton();
        checkStockButton = new JButton();
        button3 = new JButton();
        buttonBar = new JPanel();
        bakButton = new JButton();

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

                //---- updatePriceButton ----
                updatePriceButton.setText("Update stock Price");
                updatePriceButton.addActionListener(e -> updatePrice(e));
                contentPanel.add(updatePriceButton);
                updatePriceButton.setBounds(new Rectangle(new Point(75, 25), updatePriceButton.getPreferredSize()));

                //---- checkStockButton ----
                checkStockButton.setText("Check Stock Market");
                checkStockButton.addActionListener(e -> checkStock(e));
                contentPanel.add(checkStockButton);
                checkStockButton.setBounds(75, 75, 140, 30);

                //---- button3 ----
                button3.setText("Add a stock");
                contentPanel.add(button3);
                button3.setBounds(80, 120, 78, 30);

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

                //---- bakButton ----
                bakButton.setText("back");
                buttonBar.add(bakButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JButton button3;
    private JPanel buttonBar;
    private JButton bakButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
