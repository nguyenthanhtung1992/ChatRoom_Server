/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom_server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Tran Ba Y
 */
public class ChatProcess  implements Runnable{

    DataInputStream in;
    Socket clientSocket;
    ServerView mFrame;
    String readStr;
    Thread thread;
    String UserName="Không Tên",RoomName = "Phòng Ý Tưởng";
    int i=0,Index1,Index2;
    ChatProcess(Socket socket, ServerView mainFrame) {
       clientSocket = socket;
        mFrame = mainFrame;
        
        try{
            
            in = new DataInputStream((new BufferedInputStream(clientSocket.getInputStream())));
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, " Lỗi trong Contructor" + e.getMessage());
            thread.stop();
            thread =null;
            socket = null;
            
        }
        thread = new Thread(this);
        thread.start();
        
        
    }

    @Override
    public void run() {
        while(thread != null){
            
            try{
                readStr = in.readUTF();
                System.out.println(readStr);
                if(readStr.startsWith("HELLO")){
                    Index1 = readStr.indexOf("::") +2;
                    Index2 = readStr.indexOf("  ");
                    UserName = readStr.substring(Index1, Index2);
                    mFrame.IncConnectionCount();
                    
                    
                    
                    
                }
                
                
            }
            catch(Exception e){
                
            }
        }
        
        
    }
    
    
    
}
