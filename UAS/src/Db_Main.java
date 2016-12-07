import java.sql.*;
import java.util.Vector;

/**
 * Created by Black Feather on 07/12/2016.
 */
public class Db_Main
{
    private Connection conn;

    public Db_Main () throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        this.conn = DriverManager. getConnection("jdbc:mysql://localhost:3306/java","root","");
    }
    public boolean isConnected()
    {
        return (this.conn!=null);
    }

    public void closeConnection () throws SQLException
    {
        this.conn.close();
    }

    public Vector<Vector<Object>> select_kurs() throws SQLException
    {
        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tb_kurs");

        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
        while (rs.next())
        {
            Vector<Object> v = new Vector<Object>();
            v.add(rs.getString("nama_user"));
            v.add(rs.getString("kurs"));
            v.add(rs.getString("hasil_kurs"));
            v.add(rs.getString("perbandingan"));
            data.add(v);
        }
        return data;
    }

    public void insert_kurs (String a,String b, double c, double d) throws SQLException
    {
        String query = "INSERT INTO tb_kurs(nama_user,kurs,hasil_kurs,perbandingan) VALUES (?,?,?,?)"; //PREPARE STATEMENT
        PreparedStatement stmt = conn.prepareStatement(query);
        String nama_user = a;
        String kurs = b;
        double hasil_kurs = c;
        double perbandingan =d;
        d=0;
        stmt.setString(1,nama_user);
        stmt.setString(2,kurs);
        stmt.setDouble(3, hasil_kurs);
        stmt.setDouble(4, perbandingan);
        stmt.execute();
        stmt.close();
    }

    public void delete_kurs () throws SQLException
    {
        String query = "DELETE FROM tb_kurs"; //PREPARE STATEMENT
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.executeUpdate();
        stmt.close();
    }

}


