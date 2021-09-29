
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
 
public class ClientUser {
    
    HashMap<String,DataOutputStream> clientmap = new HashMap<String,DataOutputStream>();    //hashmap to manage users
    
    public synchronized void AddUser(String name,Socket socket)            
    {                                                                        
        try {
            sendMsg(name+" come to chat room.","Server");
            clientmap.put(name, new DataOutputStream(socket.getOutputStream()));
            System.out.println("Total number of user in chat room : "+clientmap.size());
        }catch(Exception e){
        	
        }
        
    }
    public synchronized void RemoveUser(String name) 
    {
        try {
            clientmap.remove(name);
            sendMsg(name + " go out the chat room.", "Server");
            System.out.println("Total number of user in chat room : "+clientmap.size());
        }catch(Exception e) {
        	
        }
    }
 
    public synchronized void sendMsg(String msg, String name)throws Exception //send msg or file
    {
        Iterator iterator = clientmap.keySet().iterator();
        while(iterator.hasNext())
        {
            String clientname =(String)iterator.next();
            if(msg.length()>5 && msg.substring(0, 5).equals("file:")) {
            	clientmap.get(clientname).writeUTF(msg);
            	String filename = msg.substring(5);    
            	FileInputStream fin = new FileInputStream(new File(filename)); 

            	byte[] buffer = new byte[1024];       
		        int len;                               
		        int data=0;
		        
		        while((len = fin.read(buffer))>0){     
		            data++;                        
		        }
		        
		        int datas = data;                      
		 
		        fin.close();
		        fin = new FileInputStream(filename);   
		        clientmap.get(clientname).writeInt(data);                  
		        clientmap.get(clientname).writeUTF(filename);               
		        
		        len = 0;
		        
		        for(;data>0;data--){                   
		            len = fin.read(buffer);        
		            clientmap.get(clientname).write(buffer,0,len);       
		        }
		        
		        System.out.println("file completely distributed");	
            }
            else {
            	clientmap.get(clientname).writeUTF(name + ":" + msg);
            }
        }
    }
}