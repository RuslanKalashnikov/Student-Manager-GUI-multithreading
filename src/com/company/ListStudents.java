package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListStudents extends JPanel{
    private JFrame parent;
    private String[] header = {"NAME", "SURNAME", "AGE"};
    private JTable table;
    private JScrollPane scrollPane;
    private JButton back;

    public ListStudents(MainFrame parent){
        this.parent = parent;
        setSize(600, 600);
        setLayout(null);

        table = new JTable();
        table.setFont(new Font("Calibri", Font.PLAIN, 12));
        table.setRowHeight(30);
        scrollPane = new JScrollPane(table);
        scrollPane.setSize(300, 200);
        scrollPane.setLocation(100, 100);
        add(scrollPane);

        back = new JButton("Back");
        back.setSize(300, 30);
        back.setLocation(100, 350);
        add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenu().setVisible(true);
                parent.getListStudents().setVisible(false);
            }
        });

    }
    public void generateTable(ArrayList<Students> students){
        Object data[][] = new Object[students.size()][3];

        for (int i = 0; i < students.size(); i++){
            data[i][0] = students.get(i).getName();
            data[i][1] = students.get(i).getSurname();
            data[i][2] = students.get(i).getAge();
        }
        DefaultTableModel model = new DefaultTableModel(data, header);
        table.setModel(model);
    }

}
