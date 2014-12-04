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
                   
                    mFrame.addUser(clientSocket, UserName, RoomName);
                    mFrame.SendMessToClient(clientSocket, "Đăng nhập vào hệ thống thành công!");
                    
                    
                    
                    
                    
                }
                if(readStr.startsWith("LOGROOM"))
                {
                    
                    System.out.println(readStr);
                    Index1 = readStr.indexOf("::") + 2; //xác định khoảng cách - USERNAME
                    //Index2 = readStr.indexOf("--"); //khoảng cách 2 - ROOMNAME
                   
                    RoomName = readStr.substring(Index1);
                    System.out.println("RoomName: " + RoomName);
                    mFrame.SendMessToClient(clientSocket, "WELLCOME::<font color = \"red\"><b>Chat Application </b><br/>Chào mừng " + UserName + " đã vào " + RoomName + ".... </font>");
                    mFrame.setRoomName(UserName, RoomName);
                    
                    mFrame.sendRoomListToClient(UserName, RoomName);
                    
                }
                if(readStr.startsWith("ROOMCHAT"))
                {
                    Index1 = readStr.indexOf("::") + 2; //xác định vị trí chứa Message
                
                    mFrame.SendMessageToRoom(RoomName, UserName, "ROOMCHAT::" + readStr.substring(Index1));
                }
                if(readStr.startsWith("LOGOUT"))
                {
                    //khi client thoat hoac dong ung dung
       
                    mFrame.UserExit(UserName, RoomName);
                    mFrame.DecConnectionCount();
                    //giải phóng
                    clientSocket.close();
                    clientSocket=null;
                    thread.stop();
                    thread = null;
                }
                
            }
            catch(Exception e){
                
            }
        }
        
        
    }
    
    
    
}
