/**
 * Created by Black Feather on 25/11/2016.
 */
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class Login extends JFrame
{
    Login()
    {
        setTitle("Login");
        setSize(300,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lbl_username = new JLabel ("Username");
        JLabel lbl_password = new JLabel ("Password");
        JTextField txt_username = new JTextField();
        JPasswordField txt_password = new JPasswordField();

        JPanel panel1 = new JPanel(new GridLayout(2,2));
        JPanel panel2 = new JPanel(new BorderLayout());

        panel1.add(lbl_username);
        panel1.add(txt_username);
        panel1.add(lbl_password);
        panel1.add(txt_password);

        JButton btn_login = new JButton ("Login");

        panel2.add(btn_login,BorderLayout.EAST);

        btn_login.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                setVisible(false);
                dispose(); // close
                new Main().setVisible(true);
            }
        });

        //setLayout(new GridLayout(2,1));
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        add(panel1);
        add(panel2);

        setVisible(true);

    }
    public static void main (String[] args)
    {
        new Login();
    }
}
