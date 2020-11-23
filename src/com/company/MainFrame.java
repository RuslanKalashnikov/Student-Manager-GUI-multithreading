package com.company;

import javax.swing.*;

public class MainFrame extends JFrame {
    private MainMenu mainMenu;
    private AddStudent addStudent;
    private ListStudents listStudents;

    public MainFrame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Students application");
        setSize(600, 600);
        mainMenu = new MainMenu(this);
        mainMenu.setVisible(true);
        add(mainMenu);

        addStudent = new AddStudent(this);
        addStudent.setVisible(false);
        add(addStudent);

        listStudents = new ListStudents(this);
        listStudents.setVisible(false);
        add(listStudents);
    }
    public AddStudent getAddStudent(){
        return addStudent;
    }
    public ListStudents getListStudents(){
        return listStudents;
    }
    public MainMenu getMainMenu(){
        return mainMenu;
    }
}
