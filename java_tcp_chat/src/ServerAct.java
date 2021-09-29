
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
 
public class ServerAct implements Runnable{
     
    Socket socket;
    InputStream iin;
    DataInputStream in;
    String name;
    ClientUser user = new ClientUser();

    DataOutputStream dout;
    
    public ServerAct(ClientUser user,Socket socket) throws Exception //server receive from client
    {
        this.user = user;
        this.socket = socket;

        iin = socket.getInputStream();

        in = new DataInputStream(iin);
        dout = new DataOutputStream(socket.getOutputStream());  
        

        this.name = in.readUTF(); //read client user name

        user.AddUser(name, socket);
    }
 
    public void run()
    {
    	
        try
        {

            while(true)//recieve and send
            {
                String msg = in.readUTF();
                if(msg.length()>5 && msg.substring(0, 5).equals("file:")) {		//if it is a file
                	
                	int data = in.readInt();           
                    String filename = in.readUTF();          
                    File file = new File(filename);          
                    
                    FileOutputStream out = new FileOutputStream(file);    

                    System.out.println("Upload file: " + filename );
                    int datas = data;                   
                    byte[] buffer = new byte[1024];      
                    int len;

                    for(;data>0;data--){              
                        len = in.read(buffer);
                        out.write(buffer,0,len);
                    }


                    out.flush();
                    out.close();
                    
                    user.sendMsg(msg , name);
                }
                else {
                	user.sendMsg(msg , name);
                }
            }
        }catch(Exception e) {
            user.RemoveUser(this.name);
        }        
    }
}

