/**
 * Created by Black Feather on 25/11/2016.
 */

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.JOptionPane;

class Admin extends JFrame
{
    //panel 1
    JLabel l1panel1 = new JLabel("User Name");
    JLabel l2panel1 = new JLabel("Password");
    JTextField tf1panel1 = new JTextField();
    JTextField tf2panel1 = new JTextField();

    //panel2
    JButton b1panel2 = new JButton("Create");
    JButton b2panel2 = new JButton("Delete");
    JButton b3panel2 = new JButton("Update");

    //panel3
    Vector<Vector<Object>> data = null;
    Vector<Object> columnNames = new Vector<Object>();
    JTable tpanel3 = new JTable (data,columnNames);
    JScrollPane sppanel3 = new JScrollPane (tpanel3);
    DefaultTableModel model = (DefaultTableModel)  tpanel3.getModel();

   Admin()
    {
        setTitle("Admin");
        setSize(500,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 =new JPanel();

        //panel 3

        columnNames.add("Nama User");
        columnNames.add("Password User");

        try
        {
            Db_admin db = new Db_admin ();
            data = db.select_user();

            System.out.println(db.isConnected());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        panel3.setLayout(new GridLayout(1,1));
        tpanel3.setFillsViewportHeight(true);
        model.setDataVector(data,columnNames);
        tpanel3.setModel (model);
        panel3.add(sppanel3);

        //panel 1
        panel1.add(l1panel1);
        panel1.add(tf1panel1);
        panel1.add(l2panel1);
        panel1.add(tf2panel1);
        panel1.setLayout(new GridLayout(2,2));

        //panel 2
        panel2.add(b1panel2);
        panel2.add(b2panel2);
        panel2.add(b3panel2);
        panel2.setLayout(new GridLayout(1,3));

        //button create
        b1panel2.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent arg0)
            {
                try
                {
                    String txt1 = tf1panel1.getText();
                    String txt2 = tf2panel1.getText();

                    Db_admin db = new Db_admin();
                    db.insert_user(txt1,txt2);

                    DefaultTableModel tableModel = (DefaultTableModel)  tpanel3.getModel();
                    Vector<Vector<Object>> newEntry = new Vector<Vector<Object>>();
                    newEntry =db.select_user();
                    model.setDataVector(newEntry,columnNames);
                    tpanel3.setModel (tableModel);
                    tableModel.fireTableDataChanged();

                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,"nama user tidak boleh sama");
                    System.out.println(e);
                }
            }
        });

        //selectedd row
        tpanel3.addMouseListener(new MouseAdapter ()
        {
            public void mouseClicked (MouseEvent e)
            {
                int i = tpanel3.getSelectedRow();
                if(model.getValueAt(i,0).toString()!= null && !"".equals(model.getValueAt(i,0).toString()))
                {
                    tf1panel1.setText(model.getValueAt(i, 0).toString());
                }
                if(model.getValueAt(i,1).toString()!= null && !"".equals(model.getValueAt(i,0).toString()))
                {
                    tf2panel1.setText(model.getValueAt(i, 1).toString());
                }
            }
        });

        //button delete
        b2panel2.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent arg0)
            {
                try
                {
                    String txt1 = tf1panel1.getText();
                    Db_admin db = new Db_admin();
                    db.delete_user(txt1);
                    DefaultTableModel tableModel = (DefaultTableModel)  tpanel3.getModel();
                    Vector<Vector<Object>> newEntry = new Vector<Vector<Object>>();
                    newEntry =db.select_user();
                    model.setDataVector(newEntry,columnNames);
                    tpanel3.setModel (tableModel);
                    tableModel.fireTableDataChanged();
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        });


        //button update
        b3panel2.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent arg0)
            {
                JTextField tf1 = new JTextField(5);
                JTextField tf2 = new JTextField(5);

                JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("Update Nama User"));
                myPanel.add(tf1);
                myPanel.add(new JLabel("Update Password User"));
                myPanel.add(tf2);
                myPanel.add(new JLabel("Nama User yang diganti"));
                myPanel.add(new JLabel("     "+tf1panel1.getText()));
                myPanel.setLayout(new GridLayout(3,2));

                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Update User", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try
                    {
                        Db_admin db = new Db_admin();
                        db.update_user(tf1.getText(),tf2.getText(),tf1panel1.getText());
                        DefaultTableModel tableModel = (DefaultTableModel)  tpanel3.getModel();
                        Vector<Vector<Object>> newEntry = new Vector<Vector<Object>>();
                        newEntry =db.select_user();
                        model.setDataVector(newEntry,columnNames);
                        tpanel3.setModel (tableModel);
                        tableModel.fireTableDataChanged();
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        });

        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        add (panel1);
        add (panel2);
        add (panel3);
        setVisible(true);

    }
    public static void main (String[] args)
    {
        new Admin();
    }
}
