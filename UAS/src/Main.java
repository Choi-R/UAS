/**
 * Created by Black Feather on 25/11/2016.
 */
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class Main extends JFrame
{

    Main()
    {
        setTitle("Project UAS");
        setSize(1200,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel1 =new JPanel();
        JPanel panel2 =new JPanel();

        JLabel l11panel1 = new JLabel ("Akun ");
        JLabel l12panel1 =new JLabel ("Nama Akun-edit");
        JButton b1panel1 =new JButton("LogOut");

        JLabel l2panel1 = new JLabel ("Kurs yang dipilih");
        JTextField tf2panel1 =new JTextField();
        JComboBox cb2panel1 =new JComboBox ();

        JButton b3panel1 = new JButton("Generate");
        JLabel l3panel1 = new JLabel("Hasil Kurs-edit");

        cb2panel1.addItem("USD->IDR");
        cb2panel1.addItem("USD->EUR");
        cb2panel1.addItem("IDR->USD");
        cb2panel1.addItem("IDR->EUR");
        cb2panel1.addItem("EUR->IDR");
        cb2panel1.addItem("EUR->USD");

        panel1.setLayout(new GridLayout(3,3));
        panel1.add(l11panel1);
        panel1.add(l12panel1);
        panel1.add(b1panel1);

        panel1.add(l2panel1);
        panel1.add(tf2panel1);
        panel1.add(cb2panel1);

        panel1.add(b3panel1);
        panel1.add(l3panel1);

        b1panel1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                setVisible(false);
                dispose(); // close
                new Login().setVisible(true);
            }
        });

        Vector<Vector<Object>> data = null;
        Vector<Object> columnNames = new Vector<Object>();
        columnNames.add("ID User");
        columnNames.add("Nilai Mata uang awal");
        columnNames.add("Kurs");
        columnNames.add("Hasil dari Kurs");
        columnNames.add("Log");
        columnNames.add("Perbandingan");

        JTable tpanel2 = new JTable (data,columnNames);
        JScrollPane sppanel2 = new JScrollPane (tpanel2);
        panel2.setLayout(new GridLayout(1,1));
        tpanel2.setFillsViewportHeight(true);

        DefaultTableModel model = (DefaultTableModel)  tpanel2.getModel();
        model.setDataVector(data,columnNames);
        tpanel2.setModel (model);
        panel2.add(sppanel2);

        panel2.setBorder(BorderFactory.createTitledBorder("Kurs Record"));

        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        add(panel1);
        add(panel2);

        setVisible(true);
    }
    public static void main (String[] args)
    {
        new Main();
    }
}
