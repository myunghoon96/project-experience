import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
 
public class ClientMain {
    public static void main(String[] arg)
    {
        Socket socket = null;            //client socket to Server
        DataInputStream in = null;        //stream from server
        BufferedReader in2 = null;        //client input msg
        DataOutputStream out = null;    //client stream to server
        
        try {
 
            socket = new Socket("127.0.0.1",1234);    //connect server
            
            in = new DataInputStream(socket.getInputStream());            
            in2 = new BufferedReader(new InputStreamReader(System.in)); 
            out = new DataOutputStream(socket.getOutputStream());        
 
            System.out.print("User Name : ");
            String data = in2.readLine();
            
            
            out.writeUTF(data);		//send user name to server

            Thread th = new Thread(new ClientSend(out));
            th.start();
        }catch(IOException e) {}
        try {
            while(true)		//receive from server
            {
                String str1 = in.readUTF();
                if(str1.length()>5 && str1.substring(0, 5).equals("file:")) {		//download file
                	
                	int data = in.readInt();           
                    String filename = in.readUTF();            
                    File file = new File(filename);             
                    
                    FileOutputStream fout = new FileOutputStream(file);           

                    System.out.println("Upload file: " + filename );
                    int datas = data;                        
                    byte[] buffer = new byte[1024];      
                    int len;

                    for(;data>0;data--){           
                        len = in.read(buffer);
                    }


                    fout.flush();
                    fout.close();
                    System.out.println("file completely downloaded");
                }
                else {
                	System.out.println(str1);
                }
            }
        }catch(IOException e) {
        	
        }
    }
}