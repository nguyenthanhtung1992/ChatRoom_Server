/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom_server;

import java.net.Socket;

/**
 *
 * @author Tran Ba Y
 */
public class UserClient {
     public UserClient(Socket socket,String UserName,String RoomName)
    {
        //khởi tạo giá trị ban đầu của lớp
        clientSocket = socket;
        usrName = UserName;
        roomName = RoomName;
    }

    //các hàm thiết lập thuộc tính cho lớp
    public void setUserName(String UserName)
    {
        //thiết lập user name cho object
        usrName = UserName;
    }

    public void setSocket(Socket socket)
    {
        //thiết lập clientsocket cho object
        clientSocket = socket;
    }

    public void setRoomName(String RoomName)
    {
        roomName = RoomName;
    }

    //Các hàm nhận thông tin từ class

    public String getUserName()
    {
        return usrName;
    }

    public Socket getSocket()
    {
        return clientSocket;
    }

    public String getRoomName()
    {
        return roomName;
    }


private String usrName;
private String roomName;
private Socket clientSocket;
    
}
