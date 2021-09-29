import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
 
public class ClientSend implements Runnable{
    DataOutputStream out;
    BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
    public ClientSend(DataOutputStream out)
    {
        this.out = out;
    }
    public void run()
    {
        while(true)
        {
            try		//get input and send to server
            {
                String msg = in2.readLine();    
                if(msg.length()>5 && msg.substring(0, 5).equals("file:"))	//if want to upload file
                {
                	out.writeUTF(msg);  
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
    		        out.writeInt(data);                 
    		        out.writeUTF(filename);               
    		        
    		        len = 0;
    		        
    		        for(;data>0;data--){                  
    		            len = fin.read(buffer);        
    		            out.write(buffer,0,len);   
    		        }
    		        
    		        System.out.println("file completely uploaded");
                }
                else
                {
                	out.writeUTF(msg);            
                }
            }catch(Exception e) {
            	
            }
        }
    }
}