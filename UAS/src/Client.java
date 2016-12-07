import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Created by Black Feather on 07/12/2016.
 */
public class Client
{
    double random;
    public double getrand()
    {
        return  random;
    }

    public void main(String[] args) throws IOException
    {

        try (Socket s = new Socket("127.0.0.1",1234))
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);
            while (true)
            {
                out.println("random");
                random = Double.parseDouble(in.readLine());
                System.out.println(random);
            }

        }

    }
}
