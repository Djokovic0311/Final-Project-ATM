/*
 * Created by JFormDesigner on Fri Dec 09 11:21:03 EST 2022
 */

package view;

import java.awt.event.*;
import model.User;

import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 * @author unknown
 */
public class GUIUserProfileWindow extends JFrame {
    private List userInfo;
    private String username;

    public GUIUserProfileWindow(List userInfo, String userName) {
        this.userInfo = userInfo;
        this.username = userName;
        initComponents();
        setText(userInfo);
    }


    private void back(ActionEvent e) {
        dispose();
        new GUICustomerHomePage(userInfo,username).setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        userNameContent = new JLabel();
        passwordContent = new JLabel();
        backButton = new JButton();

        //======== this ========
        setTitle("User Profile");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("UserName");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(75, 60), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("Password");
        contentPane.add(label2);
        label2.setBounds(75, 110, 63, 16);

        //---- userNameContent ----
        userNameContent.setText("default");
        contentPane.add(userNameContent);
        userNameContent.setBounds(185, 60, 85, userNameContent.getPreferredSize().height);

        //---- passwordContent ----
        passwordContent.setText("default");
        contentPane.add(passwordContent);
        passwordContent.setBounds(180, 110, 85, passwordContent.getPreferredSize().height);

        //---- backButton ----
        backButton.setText("back");
        backButton.addActionListener(e -> back(e));
        contentPane.add(backButton);
        backButton.setBounds(new Rectangle(new Point(300, 215), backButton.getPreferredSize()));

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
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    public void setText(List userInfo){
        userNameContent.setText(userInfo.get(0)+"");
        passwordContent.setText(userInfo.get(1)+"");
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel userNameContent;
    private JLabel passwordContent;
    private JButton backButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
