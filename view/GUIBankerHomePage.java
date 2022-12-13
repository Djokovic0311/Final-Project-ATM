import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;
/*
 * Created by JFormDesigner on Mon Dec 12 12:30:59 EST 2022
 */



/**
 * @author Mingxin Li
 */
public class GUIBankerHomePage extends JFrame {
    private List userInfo;
    private AccountController accountController = new AccountController();
    private String userName;
    public GUIBankerHomePage(List userInfo, String userName) {
        this.userInfo = userInfo;
        this.userName = userName;
        initComponents();
    }

    private void profile(ActionEvent e) {//manager profile
        dispose();
        new GUIUserProfileWindow(userInfo, userName).setVisible(true);
    }

    private void checkCustomer(ActionEvent e) {
        dispose();
        new GUICheckCustomerWindow(userName).setVisible(true);
    }

    private void stock(ActionEvent e){//
        dispose();
        List managerAccounts = accountController.getAccountsForCustomer(userName);
        new GUIDisplayStock(managerAccounts, userInfo, userName, "manager");
    }

    private void back(ActionEvent e) {
        dispose();
        GUILoginWindow guiLoginWindow = new GUILoginWindow();
        guiLoginWindow.setVisible(true);
    }

    private void dailyReport(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Mingxin Li
        managerInfoButton = new JButton();
        checkCustomerButton = new JButton();
        dailyReportButton = new JButton();
        stockButton = new JButton();
        backButton = new JButton();

        //======== this ========
        setTitle("Hello Manager!");
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());

        //---- managerInfoButton ----
        managerInfoButton.setText("Manager Profile");
        managerInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profile(e);
            }
        });
        contentPane.add(managerInfoButton);

        //---- checkCustomerButton ----
        checkCustomerButton.setText("Check Customer");
        checkCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkCustomer(e);
            }
        });
        contentPane.add(checkCustomerButton);

        //---- dailyReportButton ----
        dailyReportButton.setText("Daily Report");
        dailyReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dailyReport(e);
            }
        });
        contentPane.add(dailyReportButton);

        //---- stockButton ----
        stockButton.setText("Stock");
        stockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//show the whole stock market
            stock(e);
            }
        });
        contentPane.add(stockButton);

        //---- backButton ----
        backButton.setText("Log out");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back(e);
            }
        });
        contentPane.add(backButton);
        setSize(400, 260);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Mingxin Li
    private JButton managerInfoButton;
    private JButton checkCustomerButton;
    private JButton dailyReportButton;
    private JButton stockButton;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
