package ui;


import model.Task;
import model.ToDoList;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;


public class ListExample extends JPanel implements ActionListener {

    private JList list;
    private DefaultListModel listModel;

    //ADD and DELETE buttons
    private JButton addTask;
    private JButton deleteTask;

    //User input for new Task's name
    private JTextField input;

    //!!! For now, it will reflect the input change
    private JLabel changed;
    private JLabel selectonChanged;
    

    public ListExample() {
        super(new BorderLayout());

        listModel = new DefaultListModel();
        listModel.addElement("Task1");

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(10);
        list.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        list.getSelectedIndex();
                    }
                });
        JScrollPane sp = new JScrollPane(list);


        addTask = new JButton("ADD");
        addTask.setActionCommand("ADD");
        addTask.addActionListener(this);

        deleteTask = new JButton("DELETE");
        deleteTask.setActionCommand("DELETE");
        deleteTask.addActionListener(this);

        changed = new JLabel("change");
        input = new JTextField(20);

        //create a panel that uses BoxLayout;

        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp,
                BoxLayout.LINE_AXIS));
        bp.add(addTask);
        bp.add(input);
        bp.add(deleteTask);
        bp.add(changed);

        add(sp, BorderLayout.CENTER);
        add(bp, BorderLayout.PAGE_END);


    }

    //The method that's called when ADD button is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD")) {
            listModel.addElement(input.getText());
            input.requestFocusInWindow();
            input.setText("");

            list.setSelectedIndex(listModel.size() - 1);
            list.ensureIndexIsVisible(listModel.size() - 1);


            //changed.setText(input.getText());
        }
        if (e.getActionCommand().equals("DELETE")) {
            int index = list.getSelectedIndex();
            listModel.remove(list.getSelectedIndex());

            input.requestFocusInWindow();
            input.setText("");

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }


    private static void runForMain() {
        JFrame frame = new JFrame("Project Name");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ListExample listExample = new ListExample();
        listExample.setOpaque(true);
        frame.setContentPane(listExample);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                runForMain();
            }
        });
    }

}