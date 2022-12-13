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

    private void passwordChange(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        userNameContent = new JLabel();
        passwordContent = new JLabel();
        passwordChangeButton = new JButton();

        //======== this ========
        setTitle("User Profile");
        var contentPane = getContentPane();
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
        userNameContent.setBounds(new Rectangle(new Point(185, 60), userNameContent.getPreferredSize()));

        //---- passwordContent ----
        passwordContent.setText("default");
        contentPane.add(passwordContent);
        passwordContent.setBounds(new Rectangle(new Point(185, 110), passwordContent.getPreferredSize()));

        //---- passwordChangeButton ----
        passwordChangeButton.setText("change");
        passwordChangeButton.addActionListener(e -> passwordChange(e));
        contentPane.add(passwordChangeButton);
        passwordChangeButton.setBounds(new Rectangle(new Point(280, 105), passwordChangeButton.getPreferredSize()));

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
    private JButton passwordChangeButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
