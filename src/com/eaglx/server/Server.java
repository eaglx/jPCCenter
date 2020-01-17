package com.eaglx.server;

import com.example.jremotecontrol.Network.Package;

import java.net.*;
import java.io.*;

public class Server {
    private ServerSocket serveSock;
    private int serverPort = 8090;
    private Socket client;
    private InputStream input;
    //private OutputStream output;
    private ObjectInputStream objectInputStream;

    public Server()
    {
        try {
            serveSock = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
            serveSock = null;
        }
    }

    public Server(int port)
    {
        try {
            serverPort = port;
            serveSock = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
            serveSock = null;
        }
    }

    public boolean checkIfOk(){
        if(serveSock == null){
            return false;
        }
        else{
            return true;
        }
    }

    public int getServerPort(){
        return serverPort;
    }

    public Package read() {
        try {
            return (Package) objectInputStream.readObject();
//            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(input));
//            return inFromClient.readLine();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR: Failed when read a data from client!");
            return null;
        }
    }

    public boolean start(){
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

    public void stop(){
        try {
            objectInputStream.close();
            client.close();
            serveSock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
