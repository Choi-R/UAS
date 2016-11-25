/**
 * Created by Black Feather on 25/11/2016.
 */
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class Admin extends JFrame
{
   Admin()
    {
        setTitle("Admin");
        setSize(500,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        //panel 1
        JLabel l1panel1 = new JLabel("User Name");
        JLabel l2panel1 = new JLabel("Password");
        JTextField tf1panel1 = new JTextField();
        JTextField tf2panel2 = new JTextField();

        panel1.add(l1panel1);
        panel1.add(tf2panel2);
        panel1.add(l2panel1);
        panel1.add(tf1panel1);


        panel1.setLayout(new GridLayout(2,2));

        //panel 2
        JButton b1panel2 = new JButton("Create");
        JButton b2panel2 = new JButton("Delete");
        JButton b3panel2 = new JButton("Update");
        JButton b4panel2 = new JButton("Table");

        panel2.add(b1panel2);
        panel2.add(b2panel2);
        panel2.add(b3panel2);
        panel2.add(b4panel2);
        panel2.setLayout(new GridLayout(1,4));

        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        add (panel1);
        add (panel2);

        setVisible(true);
    }
    public static void main (String[] args)
    {
        new Admin();
    }
}
