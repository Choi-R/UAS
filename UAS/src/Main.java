/**
 * Created by Black Feather on 25/11/2016.
 */
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.Timer;

class Main extends JFrame
{
    JPanel panel1 =new JPanel();
    JPanel panel2 =new JPanel();

    JLabel l11panel1 = new JLabel ("Akun ");
    JLabel l12panel1 =new JLabel ();
    JButton b1panel1 =new JButton("LogOut");

    JLabel l2panel1 = new JLabel ("Kurs yang dipilih");
    JComboBox cb2panel1 =new JComboBox ();
    JButton b2panel1 = new JButton("Generate");

    JLabel l31panel1 = new JLabel();
    JLabel l32panel1 = new JLabel();
    JButton b3panel1 = new JButton("Stop");

    double min,max,c,d,temp,random;
    Timer timer;
    int counter;
    public void getname(String a)
    {
        l12panel1.setText(a);
    }

    Main()
    {
        setTitle("Project UAS");
        setSize(1200,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
        panel1.add(cb2panel1);
        panel1.add(b2panel1);

        panel1.add(l31panel1);
        panel1.add(l32panel1);
        panel1.add(b3panel1);

        Vector<Vector<Object>> data = null;
        Vector<Object> columnNames = new Vector<Object>();
        columnNames.add("Nama User");
        columnNames.add("Kurs");
        columnNames.add("Hasil Kurs");
        columnNames.add("Perbandingan (%)");

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

        //button generate
        b2panel1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                /*
                try
                {
                    String addr = JOptionPane.showInputDialog("Enter IP address of the server: ");
                    Socket s = new Socket (addr,1234);
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    answer = Double.parseDouble(in.readLine());
                    System.out.println(answer);
                }
                catch (Exception ioe)
                {
                    ioe.printStackTrace();
                }
                */
                timer.start();
                l31panel1.setText("1 "+cb2panel1.getSelectedItem().toString());
                b2panel1.setEnabled(false);
            }
        });

        counter =1000;
        temp=0;
        timer = new Timer(counter, new ActionListener()
        {
            int count=0;
            public void actionPerformed(ActionEvent e)
            {
                if (cb2panel1.getSelectedItem().toString().equals("USD->IDR"))
                {
                    min =13200;
                    max =13500;
                }
                else if(cb2panel1.getSelectedItem().toString().equals("USD->EUR"))
                {
                    min =0.90;
                    max =0.95;
                }
                else if(cb2panel1.getSelectedItem().toString().equals("IDR->USD"))
                {
                    min =0.000072;
                    max =0.000087;
                }
                else if(cb2panel1.getSelectedItem().toString().equals("IDR->EUR"))
                {
                    min =0.000068;
                    max =0.000072;
                }
                else if(cb2panel1.getSelectedItem().toString().equals("EUR->IDR"))
                {
                    min =14200;
                    max =14500;
                }
                else if(cb2panel1.getSelectedItem().toString().equals("EUR->USD"))
                {
                    min =1.10;
                    max =1.05;
                }
                try
                {
                    try (Socket s = new Socket("127.0.0.1",1234))
                    {
                        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                        PrintWriter out = new PrintWriter(s.getOutputStream(),true);
                        int son = count%counter;
                        if (son==0)
                        {
                            out.println("random");
                            random = Double.parseDouble(in.readLine());
                            System.out.println(random);
                        }
                    }
                    catch (Exception wk)
                    {
                        wk.printStackTrace();
                    }
                    String a = l12panel1.getText();
                    String b =cb2panel1.getSelectedItem().toString();
                    c = min + random * (max - min);
                    if (count <counter)
                    {
                        d=0;
                    }
                    else
                    {
                        d=((Double.parseDouble(l32panel1.getText())-temp)/Double.parseDouble(l32panel1.getText()))*100;
                    }

                    Db_Main db = new Db_Main();
                    db.insert_kurs(a,b,c,d);
                    Vector<Vector<Object>> newEntry = new Vector<Vector<Object>>();
                    newEntry =db.select_kurs();
                    model.setDataVector(newEntry,columnNames);
                    tpanel2.setModel (model);
                    model.fireTableDataChanged();
                    l32panel1.setText(Double.toString(c));
                    Rectangle cellBounds = tpanel2.getCellRect(tpanel2.getRowCount() - 1, 0, true);
                    tpanel2.scrollRectToVisible(cellBounds);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                count =count+counter;
                temp = d;
            }
        });

        //button stop
        b3panel1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                timer.stop();
                b2panel1.setEnabled(true);
            }
        });

        //button log out
        b1panel1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    Db_Main db = new Db_Main();
                    db.delete_kurs();
                    Vector<Vector<Object>> newEntry = new Vector<Vector<Object>>();
                    newEntry =db.select_kurs();
                    model.setDataVector(newEntry,columnNames);
                    tpanel2.setModel (model);
                    model.fireTableDataChanged();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                timer.stop();
                setVisible(false);
                dispose(); // close
                new Login().setVisible(true);
            }
        });

        cb2panel1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
               b2panel1.setEnabled(true);
            }
        });

    }


    public static void main (String[] args)
    {
        new Main();
    }
}
