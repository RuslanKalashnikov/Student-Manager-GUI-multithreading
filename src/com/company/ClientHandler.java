package com.company;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread{
    private Socket socket;

    public ClientHandler(){}

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void run(){
        DBManager manager = new DBManager();
        manager.connect();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            PackageData packageData = null;
            while ((packageData = (PackageData) inputStream.readObject()) != null){
                if ((packageData.getOperationType().equals("ADD"))){
                    manager.addStudent(packageData.getStudent());
                }
                else if ((packageData.getOperationType().equals("LIST"))){
                    ArrayList<Students> students = manager.getAllStudents();
                    PackageData packageData1 = new PackageData("ANSWER", students);
                    outputStream.writeObject(packageData1);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

