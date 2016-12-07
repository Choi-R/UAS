/**
 * Created by Black Feather on 25/11/2016.
 */
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;


class Login extends JFrame
{

    Login()
    {
        setTitle("Login User");
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

                try
                {
                    String databaseUsername = "";
                    String databasePassword = "";
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
                    String query = "SELECT * FROM login WHERE nama_user=? AND pass_user=?"; //PREPARE STATEMENT
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1,txt_username.getText());
                    stmt.setString(2,txt_password.getText());
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next())
                    {
                        databaseUsername=rs.getString("nama_user");
                        databasePassword=rs.getString("pass_user");
                    }
                    System.out.println(databaseUsername);
                    System.out.println(databasePassword);
                    if (txt_username.getText().equals(databaseUsername) && txt_password.getText().equals(databasePassword))
                    {
                        Main m =new Main();
                        System.out.println("Successful Login!\n----");
                        setVisible(false);
                        dispose(); // close
                        m.setVisible(true);
                        m.getname(txt_username.getText());
                    }
                    else
                    {
                        System.out.println("Incorrect Password\n----");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

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
