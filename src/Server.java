import com.sun.source.tree.WhileLoopTree;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.*;
public class Server {
    DatagramSocket socket=null;
    byte[] receiverBuffer=null;
    DatagramPacket receiverPacket=null;

    public static void main(String[] args) {
        Server server=new Server();
        server.initializeVariable();
        server.connecting();

    }
    private  void initializeVariable()
    {
        try {
        socket =new DatagramSocket(Constants.PORT);
        receiverBuffer=new byte[Constants.BUFFER_SIZE];
        }catch (SocketException ex){
            log("initializeVariable : " + ex.toString());
        }
    }
    private String receiveData()
    {
        String line="";
        try{
            receiverPacket =new DatagramPacket(receiverBuffer,receiverBuffer.length);
            socket.receive(receiverPacket);
            line =new String(receiverPacket.getData(),0,receiverPacket.getLength());
        }
        catch(IOException e)
        {
            log("receiveData: " + line.toString());
        }
        return line;
    }

    private void connecting()
    {
        while(true)
        {
            String data=receiveData();
            log("Client : " +"IPadd " + receiverPacket.getAddress().toString()
                    + " port " + receiverPacket.getPort()
                    + " Data length " + receiverPacket.getLength()
                    + " : " +data);
            if(data.equals(Constants.Stop))
            {
                log("Client say bye... Exiting");
                break;
            }
            receiverBuffer=new byte[Constants.BUFFER_SIZE];
        }
    }
    private void log(String message)
    {
        System.out.println(message);
    }


}
