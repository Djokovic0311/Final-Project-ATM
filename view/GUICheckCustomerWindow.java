package view;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

import utils.ATMConstant;
import utils.Utils;
/*
 * Created by JFormDesigner on Mon Dec 12 12:53:53 EST 2022
 */



/**
 * @author Mingxin Li
 */
public class GUICheckCustomerWindow extends JFrame {
	ATMConstant atmConstant = new ATMConstant();
    public GUICheckCustomerWindow() {
        initComponents();
    }

    private void check(ActionEvent e) throws Exception{
        String userName = userNameTextField.getText().toString();
        List userInfo;//get from database 
        if(Utils.isEmpty(userName)) {
            JOptionPane.showMessageDialog(this,"UserName cannot be empty");
            return;
        }
        GUIUserProfileWindow upw = new GUIUserProfileWindow(userInfo, userName);
        upw.setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Mingxin Li
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        usernameLabel = new JLabel();
        userNameTextField = new JTextField();
        buttonBar = new JPanel();
        cancelButton = new JButton();
        checkButton = new JButton();

        //======== this ========
        setTitle("Check A Customer");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
            swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border
            . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog"
            ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) ,dialogPane. getBorder
            ( )) ); dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){  public void propertyChange (java
            .beans .PropertyChangeEvent e) {if ("bor\u0064er" .equals (e .getPropertyName () )) throw new RuntimeException
            ( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                //---- usernameLabel ----
                usernameLabel.setText("UserName");

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .add(contentPanelLayout.createSequentialGroup()
                            .add(63, 63, 63)
                            .add(usernameLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.RELATED)
                            .add(userNameTextField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(95, Short.MAX_VALUE))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .add(contentPanelLayout.createSequentialGroup()
                            .add(38, 38, 38)
                            .add(contentPanelLayout.createParallelGroup(GroupLayout.BASELINE)
                                .add(usernameLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .add(userNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(85, Short.MAX_VALUE))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //---- checkButton ----
                checkButton.setText("Check");
                checkButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        check(e);
                    }
                });
                buttonBar.add(checkButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Mingxin Li
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel usernameLabel;
    private JTextField userNameTextField;
    private JPanel buttonBar;
    private JButton cancelButton;
    private JButton checkButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
