package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AddStudent extends JPanel {
    private MainFrame parent;
    private JLabel labelName;
    private JTextField nameTextField;
    private JLabel labelSurname;
    private JTextField surnameTextField;
    private JLabel labelAge;
    private JTextField ageTextField;
    private JButton add;
    private JButton back;


    public AddStudent(MainFrame parent){
        this.parent = parent;
        setSize(600, 600);
        setLayout(null);

        labelName = new JLabel("NAME: ");
        labelName.setSize(300, 30);
        labelName.setLocation(50, 50);
        add(labelName);

        nameTextField = new JTextField();
        nameTextField.setSize(300, 30);
        nameTextField.setLocation(150, 50);
        add(nameTextField);

        labelSurname = new JLabel("SURNAME: ");
        labelSurname.setSize(300, 30);
        labelSurname.setLocation(50, 100);
        add(labelSurname);

        surnameTextField = new JTextField();
        surnameTextField.setSize(300, 30);
        surnameTextField.setLocation(150, 100);
        add(surnameTextField);

        labelAge = new JLabel("AGE: ");
        labelAge.setSize(300, 30);
        labelAge.setLocation(50, 150);
        add(labelAge);

        ageTextField = new JTextField();
        ageTextField.setSize(300, 30);
        ageTextField.setLocation(150, 150);
        add(ageTextField);

        add = new JButton("ADD");
        add.setSize(150, 30);
        add.setLocation(50, 300);
        add(add);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket socket = new Socket("127.0.0.1", 1998);
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                    Students student = new Students(null, nameTextField.getText(), surnameTextField.getText(), Integer.parseInt(ageTextField.getText()));
                    PackageData packageData = new PackageData("ADD", student);
                    outputStream.writeObject(packageData);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });


        back = new JButton("BACK");
        back.setSize(150, 30);
        back.setLocation(280, 300);
        add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(true);
                parent.getAddStudent().setVisible(false);
            }
        });


    }
}

