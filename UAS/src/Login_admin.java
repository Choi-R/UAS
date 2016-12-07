import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
/**
 * Created by Black Feather on 05/12/2016.
 */
public class Login_admin extends JFrame
{
    Login_admin()
    {
        setTitle("Login Admin");
        setSize(300,120);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lbl_username = new JLabel ("Username");
        JLabel lbl_password = new JLabel ("Password");
        JTextField txt_username = new JTextField();
        JTextField txt_password = new JTextField();

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
                if (txt_username.getText().equals("admin") && txt_password.getText().equals("admin"))
                {
                    setVisible(false);
                    dispose(); // close
                    new Admin().setVisible(true);
                }
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
        new Login_admin();
    }
}
