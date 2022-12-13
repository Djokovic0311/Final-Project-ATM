/*
 * Created by JFormDesigner on Tue Dec 13 15:39:15 EST 2022
 */

package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class GUIManagerHomepage extends JFrame {
    public GUIManagerHomepage() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        button1 = new JButton();
        button3 = new JButton();
        button2 = new JButton();
        button4 = new JButton();

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

                //---- button1 ----
                button1.setText("Check customer");
                contentPanel.add(button1);

                //---- button3 ----
                button3.setText("text");
                contentPanel.add(button3);

                //---- button2 ----
                button2.setText("text");
                contentPanel.add(button2);

                //---- button4 ----
                button4.setText("Log out");
                contentPanel.add(button4);
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
    private JButton button1;
    private JButton button3;
    private JButton button2;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
