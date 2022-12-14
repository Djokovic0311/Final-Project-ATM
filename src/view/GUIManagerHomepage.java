/*
 * Created by JFormDesigner on Tue Dec 13 15:39:15 EST 2022
 */

package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUIManagerHomepage extends JFrame {
    public GUIManagerHomepage() {
        initComponents();
    }

    private void logOut(ActionEvent e) {
        dispose();
        GUILoginWindow guiLoginWindow = new GUILoginWindow();
        guiLoginWindow.setVisible(true);
    }

    private void dailyReport(ActionEvent e) {
        // TODO add your code here
    }

    private void checkCustomer(ActionEvent e) {
        dispose();
        new GUIManagerCheckCustomer().setVisible(true);
    }

    private void stockManagement(ActionEvent e) {
        dispose();
        new GUIStockManagement().setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        checkCustomerButton = new JButton();
        dailyReportButton = new JButton();
        stockManagementButton = new JButton();
        logOutButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridLayout(2, 2));

                //---- checkCustomerButton ----
                checkCustomerButton.setText("Check customer");
                checkCustomerButton.addActionListener(e -> checkCustomer(e));
                contentPanel.add(checkCustomerButton);

                //---- dailyReportButton ----
                dailyReportButton.setText("Daily Report");
                dailyReportButton.addActionListener(e -> dailyReport(e));
                contentPanel.add(dailyReportButton);

                //---- stockManagementButton ----
                stockManagementButton.setText("Stock Management");
                stockManagementButton.addActionListener(e -> stockManagement(e));
                contentPanel.add(stockManagementButton);

                //---- logOutButton ----
                logOutButton.setText("Log out");
                logOutButton.addActionListener(e -> logOut(e));
                contentPanel.add(logOutButton);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JButton checkCustomerButton;
    private JButton dailyReportButton;
    private JButton stockManagementButton;
    private JButton logOutButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
