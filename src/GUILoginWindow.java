import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Wed Dec 07 17:34:37 EST 2022
 */



/**
 * @author unknown
 */
public class GUILoginWindow extends JFrame {
    public GUILoginWindow() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        heading = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        comboBox1 = new JComboBox<>();
        label4 = new JLabel();
        buttonBar = new JPanel();
        registerButton = new JButton();
        loginButton = new JButton();

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

                //---- heading ----
                heading.setText("Welcome to ATM!");
                heading.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(heading);
                heading.setBounds(0, 0, 374, heading.getPreferredSize().height);

                //---- label2 ----
                label2.setText("UserName");
                contentPanel.add(label2);
                label2.setBounds(80, 35, 80, 45);

                //---- label3 ----
                label3.setText("Password");
                contentPanel.add(label3);
                label3.setBounds(80, 80, 80, 45);
                contentPanel.add(textField1);
                textField1.setBounds(165, 45, 130, 30);
                contentPanel.add(textField2);
                textField2.setBounds(165, 90, 130, 30);

                //---- comboBox1 ----
                comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Customer",
                    "Manager"
                }));
                contentPanel.add(comboBox1);
                comboBox1.setBounds(160, 135, 135, comboBox1.getPreferredSize().height);

                //---- label4 ----
                label4.setText("Role");
                contentPanel.add(label4);
                label4.setBounds(80, 125, 80, 45);

                //======== buttonBar ========
                {
                    buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                    buttonBar.setLayout(new GridBagLayout());
                    ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                    ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                    //---- registerButton ----
                    registerButton.setText("New User? Click to Register!");
                    buttonBar.add(registerButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                    //---- loginButton ----
                    loginButton.setText("Login");
                    buttonBar.add(loginButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 0), 0, 0));
                }
                contentPanel.add(buttonBar);
                buttonBar.setBounds(0, 178, 374, buttonBar.getPreferredSize().height);

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
    private JLabel label2;
    private JLabel label3;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox<String> comboBox1;
    private JLabel label4;
    private JPanel buttonBar;
    private JButton registerButton;
    private JButton loginButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
