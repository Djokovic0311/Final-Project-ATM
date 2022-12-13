package view;

import controller.LoginController;
import utils.ATMConstant;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Wed Dec 07 20:07:57 EST 2022
 */



/**
 * @author Jiahang Li
 * @version 1.0
 */
public class GUIRegistry extends JFrame {
    private LoginController loginController = new LoginController();
    private ATMConstant atmConstant = new ATMConstant();
    public GUIRegistry() {
        initComponents();
    }

    private void register(ActionEvent e) throws Exception {
        String userName = this.userNameTextField.getText();
        String password = String.valueOf(this.passwordTextField.getPassword());
        int i = loginController.signUpCustomer(userName,password);
        if(Utils.isEmpty(userName)) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty");
            return;
        }
        if(Utils.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty");
            return;
        }
        if(i == atmConstant.getSUCCESS()) {
            // succeed to register, go back to login page
            JOptionPane.showMessageDialog(null, "Registration Success!!!");
            dispose();
            new GUILoginWindow().setVisible(true);
        } else {
            //TODO: fail to register
            JOptionPane.showMessageDialog(null, "Fail to register");
        }
    }

    private void cancel(ActionEvent e) {
        
        dispose();
        GUILoginWindow loginWindow = new GUILoginWindow();
        loginWindow.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        userNameTextField = new JTextField();
        passwordTextField = new JPasswordField();
        buttonBar = new JPanel();
        registerButton = new JButton();
        cancelButton = new JButton();

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

                //---- label1 ----
                label1.setText("New Customer Register");
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label1);
                label1.setBounds(0, 0, 374, label1.getPreferredSize().height);

                //---- usernameLabel ----
                usernameLabel.setText("UserName");
                contentPanel.add(usernameLabel);
                usernameLabel.setBounds(80, 35, 80, 45);

                //---- passwordLabel ----
                passwordLabel.setText("Password");
                contentPanel.add(passwordLabel);
                passwordLabel.setBounds(80, 85, 80, 45);
                contentPanel.add(userNameTextField);
                userNameTextField.setBounds(165, 45, 130, 30);
                contentPanel.add(passwordTextField);
                passwordTextField.setBounds(165, 100, 130, 30);

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

                //---- registerButton ----
                registerButton.setText("Register");
                registerButton.addActionListener(e -> register(e));
                buttonBar.add(registerButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
    private JLabel label1;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField userNameTextField;
    private JPasswordField passwordTextField;
    private JPanel buttonBar;
    private JButton registerButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

}
