/**
 * Created by Black Feather on 25/11/2016.
 */
import java.util.*;
import java.sql.*;

public class Db_login
{
    private Connection conn;

    public Db_login () throws Exception
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

    /*
    public Vector<Vector<Object>> select_perpus() throws SQLException
    {
        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tb_perpus");

        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
        while (rs.next())
        {
            Vector<Object> v = new Vector<Object>();

            v.add(rs.getString("id_buku"));
            v.add(rs.getString("judul_buku"));
            v.add(rs.getString("penulis"));
            v.add(rs.getString("penerbit"));
            v.add(rs.getString("jml_buku"));
            v.add(rs.getString("no_rak"));
            data.add(v);
        }
        return data;
    }

    public void insert_perpus (String a,String b,String c,String d,String e,String f) throws SQLException
    {
        String query = "INSERT INTO tb_perpus(id_buku,judul_buku,penulis,penerbit,jml_buku,no_rak) VALUES (?,?,?,?,?,?)"; //PREPARE STATEMENT
        PreparedStatement stmt = conn.prepareStatement(query);
        int id_buku = Integer.parseInt(a);
        String judul_buku = b;
        String penulis = c;
        String penerbit = d;
        int jml_buku = Integer.parseInt(e);
        int no_rak = Integer.parseInt(f);

        stmt.setInt(1,id_buku);
        stmt.setString(2,judul_buku);
        stmt.setString(3,penulis);
        stmt.setString(4,penerbit);
        stmt.setInt(5,jml_buku);
        stmt.setInt(6,no_rak);

        stmt.execute();
        stmt.close();
    }

    public void delete_perpus (String a) throws SQLException
    {
        String query = "DELETE FROM tb_perpus WHERE id_buku = ?"; //PREPARE STATEMENT
        PreparedStatement stmt = conn.prepareStatement(query);
        int id_buku = Integer.parseInt(a);

        stmt.setInt(1,id_buku);
        stmt.executeUpdate();
        stmt.close();
    }

    //function update
    public void update_perpus (String a,String b,String c,String d,String e,String f) throws SQLException
    {
        String query = "UPDATE tb_perpus set id_buku = ?, judul_buku = ?, penulis = ?, penerbit = ?, jml_buku = ?,no_rak = ? WHERE id_buku = ?"; //PREPARE STATEMENT
        PreparedStatement stmt = conn.prepareStatement(query);

        int id_buku = Integer.parseInt(a);
        String judul_buku = b;
        String penulis = c;
        String penerbit = d;
        int jml_buku = Integer.parseInt(e);
        int no_rak = Integer.parseInt(f);

        stmt.setInt(1,id_buku);
        stmt.setString(2,judul_buku);
        stmt.setString(3,penulis);
        stmt.setString(4,penerbit);
        stmt.setInt(5,jml_buku);
        stmt.setInt(6,no_rak);
        stmt.setInt(7,id_buku);
        stmt.executeUpdate();
        stmt.close();
    }
    */
}
