import java.io.*;
import java.net.*;
import java.util.*;

class Reversal extends Thread
{
    private Socket socket;
    private String client;

    Reversal(Socket s)
    {
        socket = s;
        client = s.getRemoteSocketAddress().toString();
        System.out.println("Incoming connection from "+ client);
    }
    public void run()
    {
        try //coba
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            while (true)
            {
                String request = in.readLine();
                if (request == null || request.equals("."))
                {
                    break;
                }
                else
                {
                    Random rand =new Random();
                    out.println(rand.nextDouble());
                }

            }
        }
        catch (IOException e)  //bagian kode yang dijalankan jika sukses
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            System.out.println("Close connection from "+client);
        }
    }
}
public class Server
{
    public static void main (String[] args) throws IOException
    {
        try (ServerSocket listener = new ServerSocket(1234))
        {
            System.out.println("The server is running...");
            while (true)
            {
                Socket s = listener.accept();
                Thread t = new Reversal(s);
                t.start();
            }
        }
    }
}
