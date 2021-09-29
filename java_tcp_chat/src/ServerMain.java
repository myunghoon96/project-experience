
import java.net.ServerSocket;
import java.net.Socket;
 
public class ServerMain {
    public static void main(String arg[])
    {
        Socket socket = null;                    //server socket to client
        ClientUser user = new ClientUser();                    //user in chat room
        ServerSocket server_socket=null;             
        
        int count = 0;                            
        Thread thread[]= new Thread[10];       
        
        try {
            server_socket = new ServerSocket(1234);

            while(true)
            {
                socket = server_socket.accept();

                thread[count] = new Thread(new ServerAct(user,socket));
                thread[count].start();
                count++;
            }
        }catch(Exception e) {};
    }
}