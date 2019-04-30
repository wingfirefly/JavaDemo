package com.xxniu.file.FileEncrypter.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SftpFile
{
    public static void main(String[] args)
    {
        // FileService.putFileByPassword("etc/1512704196626.txt", "/home/1512704196626.txt", "192.168.1.253", "3333", "root", "root2013");
        //FileService.getFileByPassword("etc/15127.txt", "/home/1512704196626.txt", "192.168.1.253", "3333", "root", "root2013");
        File file = new File("f:/1.txt");
        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        FileInputStream io = null;
        try
        {
            io = new FileInputStream(file);
            file.delete();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                io.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
//        file.delete();
    }
}
