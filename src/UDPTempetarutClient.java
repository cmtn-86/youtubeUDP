import java.io.IOException;
import java.net.*;
import java.util.Random;

public class UDPTempetarutClient {

    DatagramSocket socket=null;
    byte[] buffer=null;
    Random zahl=new Random();
    double temperatur= 1+zahl.nextDouble()*30;
    public static String line="";

    public static void main(String[] args)
    {
        UDPTempetarutClient client =new UDPTempetarutClient();
        client.initializeVariable();
        client.connecting();

    }

    private void initializeVariable()
    {
        try{

            socket=new DatagramSocket();
        }
        catch (SocketException ex)
        {
            log("InitialiteVariable : " + ex.toString());
        }

    }
    private String temperaturenErzeugt()
    {
        /*if(temperatur>=30.0)
        {
            temperatur=round((temperatur - 0.1),1);
            line="Aktuelle Temperatur : " + temperatur;
        }
        else if(temperatur<5)
        {
            temperatur=round((temperatur + 0.1),1);
            line="Aktuelle Temperatur : " + temperatur;
        }
        else if(temperatur>=5 && temperatur<30)
            {
                Random randBool=new Random();
                boolean boolWert=randBool.nextBoolean();
                if(boolWert == true)
                {
                    temperatur=round((temperatur + 0.1),1);
                    line="Aktuelle Temperatur : " + temperatur;
                }
                else
                {

                    temperatur=round((temperatur - 0.1),1);
                    line="Aktuelle Temperatur : " + temperatur;
                }
            }*/
      for(int i=0;i<10;i++)
      {
          line="Werte " + String.valueOf(i);

      }


        return line;
    }
    private void send(String message)
    {
       try{

           InetAddress ip=InetAddress.getLocalHost();
        //   InetAddress ip=InetAddress.getLocalHost("localhost");
          // InetAddress ip = InetAddress.getByName("192.168.2.115");

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
    {/*
        int a=10;
        while (a>0)
        {
            String line = temperaturenErzeugt();
            send(line);
            if (line.equals(Constants.Stop))
            {
                break;
            }
            a--;
        }*/
        for(int i=0;i<60;i++)
        {
            line="Werte Temperatur" + String.valueOf(i+1);
            send(line);
            if (line.equals(Constants.Stop))
            {
                break;
            }

        }
    }

    private void log(String message)
    {
        System.out.println(message);
    }

    //Nachdem komme wird die decimal angabe eingestellt. bsp--> roud(10.4865621 , 1)--> ergebnis 10.5
    private static double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }


}
