/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom_server;

import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Tran Ba Y
 */
public class FileProcess {
    
    public FileWriter FileCreate(String url) throws Exception
    {
        FileWriter w= new FileWriter(url);
        return w;
    }
    
    public FileReader FileRead(String url) throws Exception
    {
        FileReader read = new FileReader(url);
        return read;
    }
}
