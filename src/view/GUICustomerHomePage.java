/*
 * Created by JFormDesigner on Wed Dec 07 22:58:40 EST 2022
 */

package view;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class GUICustomerHomePage extends JFrame {
    public GUICustomerHomePage() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        button1 = new JButton();
        button3 = new JButton();
        button2 = new JButton();
        button4 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 2, 10, 10));

        //---- button1 ----
        button1.setText("text");
        contentPane.add(button1);

        //---- button3 ----
        button3.setText("text");
        contentPane.add(button3);

        //---- button2 ----
        button2.setText("text");
        contentPane.add(button2);

        //---- button4 ----
        button4.setText("text");
        contentPane.add(button4);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton button1;
    private JButton button3;
    private JButton button2;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
