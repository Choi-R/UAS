import java.sql.*;
import java.util.Vector;

/**
 * Created by Black Feather on 25/11/2016.
 */
public class Db_admin
{
    private Connection conn;

    public Db_admin () throws Exception
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

    public Vector<Vector<Object>> select_user() throws SQLException
    {
        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM login");

        Vector<Vector<Object>> data = new Vector<Vector<Object>>(); // return the resultset as Vector
        while (rs.next())
        {
            Vector<Object> v = new Vector<Object>();
            v.add(rs.getString("nama_user"));
            v.add(rs.getString("pass_user"));
            data.add(v);
        }
        return data;
    }

    public void insert_user (String a,String b) throws SQLException
    {
        String query = "INSERT INTO login(nama_user,pass_user) VALUES (?,?)"; //PREPARE STATEMENT
        PreparedStatement stmt = conn.prepareStatement(query);
        String nama_user = a;
        String pass_user = b;
        stmt.setString(1,nama_user);
        stmt.setString(2,pass_user);
        stmt.execute();
        stmt.close();
    }

    public void delete_user (String a) throws SQLException
    {
        String query = "DELETE FROM login WHERE nama_user = ?"; //PREPARE STATEMENT
        PreparedStatement stmt = conn.prepareStatement(query);
        String nama_user = a;
        stmt.setString(1,nama_user);
        stmt.executeUpdate();
        stmt.close();
    }

    public void update_user (String a,String b,String c) throws SQLException
    {
        String query = "UPDATE login set nama_user = ?, pass_user = ? WHERE nama_user  = ?"; //PREPARE STATEMENT
        PreparedStatement stmt = conn.prepareStatement(query);
        String nama_useru = a;
        String pass_useru = b;
        String nama_user = c;
        stmt.setString(1,nama_useru);
        stmt.setString(2,pass_useru);
        stmt.setString(3,nama_user);
        stmt.executeUpdate();
        stmt.close();
    }
}
