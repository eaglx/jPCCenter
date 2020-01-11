package com.eaglx.server;

import java.net.*;
import java.io.*;

public class Server {
    private ServerSocket serveSock;
    private Socket client;
    private InputStream input;
    private OutputStream output;
    private ObjectInputStream objectInputStream;

    public Server()
    {
        try {
            serveSock = new ServerSocket(8090);
        } catch (IOException e) {
            e.printStackTrace();
            serveSock = null;
        }
    }

    public Server(int port)
    {
        try {
            serveSock = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            serveSock = null;
        }
    }

    public boolean CheckIfOk(){
        if(serveSock == null){
            return false;
        }
        else{
            return true;
        }
    }

    public Package Read(){
        try {
            return (Package) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean Start(){
       try {
           client = serveSock.accept();
           input = client.getInputStream();
           objectInputStream = new ObjectInputStream(input);
           return true;
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }

    }

    public boolean Stop(){
        try {
            objectInputStream.close();
            client.close();
            serveSock.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
