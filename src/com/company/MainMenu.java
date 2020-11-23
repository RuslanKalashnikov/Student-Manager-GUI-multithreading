package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MainMenu extends JPanel {
    private MainFrame parent;
    private JButton add;
    private JButton list;
    private JButton exit;

    public MainMenu(MainFrame parent){
        this.parent = parent;
        setSize(600, 600);
        setLayout(null);

        add = new JButton("ADD STUDENT");
        add.setLocation(100, 100);
        add.setSize(300, 30);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(false);
                parent.getAddStudent().setVisible(true);

            }
        });
        add(add);

        list = new JButton("LIST STUDENTS");
        list.setLocation(100, 200);
        list.setSize(300, 30);
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(false);
                parent.getListStudents().setVisible(true);
                try{
                    Socket socket = new Socket("127.0.0.1", 1998);
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    ArrayList<Students> students = new ArrayList<>();
                    PackageData packageData = new PackageData("LIST", students);
                    outputStream.writeObject(packageData);
                    if ((packageData = (PackageData) inputStream.readObject()) != null){
                        parent.getListStudents().generateTable(packageData.getStudents());

                    }


                }catch (Exception eex){
                    eex.printStackTrace();
                }
            }
        });
        add(list);

        exit = new JButton("EXIT");
        exit.setLocation(100, 300);
        exit.setSize(300, 30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exit);



    }
}
