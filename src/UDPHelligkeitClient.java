import java.io.IOException;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class UDPHelligkeitClient {
    Scanner scan=null;
    DatagramSocket socket=null;
    byte[] buffer=null;
    public static String line="";


    public static void main(String[] args) {
        UDPHelligkeitClient client =new UDPHelligkeitClient();
        client.initializeVariable();
        client.connecting();

    }
    private void initializeVariable()
    {
        try{
            scan=new Scanner(System.in);
            socket=new DatagramSocket();
        }
        catch (SocketException ex)
        {
            log("InitialiteVariable : " + ex.toString());
        }

    }
    private String helligkeitErzeugt()
    {/*
        Random  zahlfurhelligkeit= new Random();
        int helligkeit=zahlfurhelligkeit.nextInt();
        int prozent=helligkeit%100;
        line=" helligketi % prozent " + String.valueOf(prozent);*/
        return line;
    }
    private void send(String message)
    {
        try{ //InetAddress ip=InetAddress.getLocalHost();
            InetAddress ip = InetAddress.getByName("192.168.2.115");

            buffer =message.getBytes();
            DatagramPacket packetsend=new DatagramPacket(buffer,buffer.length,ip,Constants.PORT);
            socket.send(packetsend);
        }
        catch (UnknownHostException ex)
        {
            log("send : " + ex.toString());
        }
        catch (IOException ex)
        {
            log("send : " + ex.toString());
        }

    }
    private void connecting()
    {
       // int a=10000000;
        //while (a>0)
        //{
            //String line = helligkeitErzeugt();
            for(int i=0;i<1000000;i++)
            {
                line="Werte  Helligkeit : " + String.valueOf(i+1);
                send(line);
                if (line.equals(Constants.Stop))
                {
                    break;
                }

            }
            //send(line);
            //if (line.equals(Constants.Stop))
           // {
           //     break;
           // }
            //a--;

    }

    private void log(String message)
    {
        System.out.println(message);
    }



}
