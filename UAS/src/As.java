import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
/**
 * Created by Black Feather on 05/12/2016.
 */
public class As extends JFrame
{
    JButton administrator = new JButton("Administrator");
    JButton user = new JButton("User");
    As()
    {
        setTitle("Login As");
        setSize(300,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(administrator);
        add(user);
        setLayout(new GridLayout(1,2));

        administrator.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

                new Login_admin().setVisible(true);
            }
        });

        user.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

                new Login().setVisible(true);
            }
        });

        setVisible(true);
    }
    public static void main(String[] args)
    {
        new As();
    }
}
