package view;
import controller.AccountController;
import controller.LoginController;
import utils.ATMConstant;
import utils.Utils;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;

/*
 * Created by JFormDesigner on Wed Dec 07 17:34:37 EST 2022
 */



/**
 * @author Jiahang Li
 * @version 1.0
 */
public class GUILoginWindow extends JFrame{
    private LoginController loginController = new LoginController();
    private AccountController accountController = new AccountController();
    private ATMConstant atmConstant = new ATMConstant();

    public GUILoginWindow() {
        initComponents();
    }

    private void reset(ActionEvent ae) {
        userNameTextField.setText("");
        passwordTextField.setText("");
        RoleComboBox.setSelectedIndex(0);
    }

    private void login(ActionEvent e) throws Exception {
        String userName = userNameTextField.getText();
        String password = passwordTextField.getText();
        String role =  Objects.requireNonNull(RoleComboBox.getSelectedItem()).toString();

        if(Utils.isEmpty(userName)) {
            JOptionPane.showMessageDialog(this,"UserName cannot be empty");
            return;
        }
        if(Utils.isEmpty(password)) {
            JOptionPane.showMessageDialog(this,"Password cannot be empty");
            return;
        }
        if("Customer".equals(role)) {
            // customer login

            int statusCode = loginController.signIn(userName, password,role);
            if(statusCode == atmConstant.getSUCCESS()) {
                System.out.println(userName+"success");
                //TODO: SET CUSTOMER HOMEPAGE VISIBLE
                JOptionPane.showMessageDialog(null,"Hello customer!");
                setVisible(false);
                List userInfo = accountController.getAccountInfoForCustomer(userName);

                new GUICustomerHomePage(userInfo, userName).setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
            }
        }
        else if("Manager".equals(role)) {
            // manager login

            int statusCode = loginController.signIn(userName, password,role);

            if(statusCode == atmConstant.getSUCCESS()) {
                //TODO: SET MANAGER HOMEPAGE VISIBLE
                JOptionPane.showMessageDialog(null,"Hello manager!");
                setVisible(false);
                new GUIManagerHomepage().setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
            }
        }
    }

    private void register(ActionEvent e) {
        dispose();
        new GUIRegistry().setVisible(true);
    }

    private void exit(ActionEvent e) {
        dispose();
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        heading = new JLabel();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        userNameTextField = new JTextField();
        passwordTextField = new JPasswordField();
        RoleComboBox = new JComboBox<>();
        roleLabel = new JLabel();
        buttonBar = new JPanel();
        exitButton = new JButton();
        loginButton = new JButton();
        registerButton = new JButton();
        resetButton = new JButton();

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

                //---- heading ----
                heading.setText("Welcome to ATM!");
                heading.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(heading);
                heading.setBounds(0, 0, 374, heading.getPreferredSize().height);

                //---- usernameLabel ----
                usernameLabel.setText("UserName");
                contentPanel.add(usernameLabel);
                usernameLabel.setBounds(80, 35, 80, 45);

                //---- passwordLabel ----
                passwordLabel.setText("Password");
                contentPanel.add(passwordLabel);
                passwordLabel.setBounds(80, 80, 80, 45);
                contentPanel.add(userNameTextField);
                userNameTextField.setBounds(165, 45, 130, 30);
                contentPanel.add(passwordTextField);
                passwordTextField.setBounds(165, 90, 130, 30);

                //---- RoleComboBox ----
                RoleComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Customer",
                    "Manager"
                }));
                contentPanel.add(RoleComboBox);
                RoleComboBox.setBounds(160, 135, 135, RoleComboBox.getPreferredSize().height);

                //---- roleLabel ----
                roleLabel.setText("Role");
                contentPanel.add(roleLabel);
                roleLabel.setBounds(80, 125, 80, 45);

                //======== buttonBar ========
                {
                    buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                    buttonBar.setLayout(new GridBagLayout());
                    ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                    ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                    //---- exitButton ----
                    exitButton.setText("Exit");
                    exitButton.addActionListener(e -> exit(e));
                    buttonBar.add(exitButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- loginButton ----
                    loginButton.setText("Login");
                    loginButton.addActionListener(e -> {
                        try {
                            login(e);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    buttonBar.add(loginButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));

                    //---- registerButton ----
                    registerButton.setText("New User? Click to Register!");
                    registerButton.addActionListener(e -> register(e));
                    buttonBar.add(registerButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                    //---- resetButton ----
                    resetButton.setText("Reset");
                    resetButton.addActionListener(e -> reset(e));
                    buttonBar.add(resetButton, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                }
                contentPanel.add(buttonBar);
                buttonBar.setBounds(0, 165, 375, 90);

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
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel heading;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField userNameTextField;
    private JPasswordField passwordTextField;
    private JComboBox<String> RoleComboBox;
    private JLabel roleLabel;
    private JPanel buttonBar;
    private JButton exitButton;
    private JButton loginButton;
    private JButton registerButton;
    private JButton resetButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    public static void main(String[] args){
        new GUILoginWindow().setVisible(true);
    }
}
