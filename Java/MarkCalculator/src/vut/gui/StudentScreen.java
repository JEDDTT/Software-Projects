/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vut.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import vut.data.Student;

/**
 * This class creates a screen, GUI to help get inputs and manipulate data The
 * class extends JFrame class to help create objects
 *
 * @author Bongwala Ncd 214205541 ,Mavula LG 215213696
 */
public class StudentScreen extends JFrame {

    //Declre GUI controls
    JButton btnAdd, btnClear, btnClose, btnDisplay;
    JLabel lblStName, lbltext1, lblT2, lblT3, lblGender, lblStudNo, subCode;
    JTextField txtName, txtTest1, txtT2, txtT3, txtStudNo;
    JTextArea taDisplay;
    JRadioButton rdbMale, rdbFemale;
    JComboBox<String> cmbsubCode;
    Student student;
    private final ArrayList<Student> arStudents = new ArrayList<>();
    private double exam;

    /**
     * This methods create and group controls of the GUI
     */
    public StudentScreen() {

        lblStudNo = new JLabel("Student No:");
        lblStName = new JLabel("Name:");
        lbltext1 = new JLabel("Test1:");
        lblT2 = new JLabel("Test2:");
        lblT3 = new JLabel("Test3:");
        lblGender = new JLabel("Gender");
        subCode = new JLabel("Subject Code:");

        txtStudNo = new JTextField();
        txtName = new JTextField();
        txtTest1 = new JTextField();
        txtT2 = new JTextField();
        txtT3 = new JTextField();
        cmbsubCode = new JComboBox<>();

        rdbMale = new JRadioButton("Male");
        rdbFemale = new JRadioButton("Female");

        btnAdd = new JButton("Add Student");
        btnClear = new JButton("Clear");
        btnDisplay = new JButton("Display");
        btnClose = new JButton("Close");
        taDisplay = new JTextArea();

        cmbsubCode.addItem("ASPRO3E");
        cmbsubCode.addItem("ASBDY3A");
        cmbsubCode.addItem("ASBDX3A");
        cmbsubCode.addItem("AIBAY3A");
        cmbsubCode.addItem("AIWMX2C");
        cmbsubCode.addItem("AIISX2C");

        //Group Created controls in a container
        JPanel pnl = new JPanel();
        pnl.setLayout(null);

        //Add components/Controls
        pnl.add(lblStudNo);
        pnl.add(lblStName);
        pnl.add(lbltext1);
        pnl.add(lblT2);
        pnl.add(lblT3);
        pnl.add(lblGender);
        pnl.add(subCode);
        pnl.add(txtStudNo);
        pnl.add(txtName);
        pnl.add(txtTest1);
        pnl.add(txtT2);
        pnl.add(txtT3);
        pnl.add(rdbFemale);
        pnl.add(rdbMale);
        pnl.add(btnClear);
        pnl.add(btnAdd);
        pnl.add(taDisplay);
        pnl.add(btnDisplay);
        pnl.add(btnClose);
        pnl.add(cmbsubCode);

        //Set Location for my components
        lblStudNo.setBounds(20, 15, 100, 25);
        lblStName.setBounds(20, 45, 100, 25);
        lblGender.setBounds(20, 75, 100, 25);
        subCode.setBounds(20, 110, 100, 25);
        lbltext1.setBounds(20, 150, 100, 25);
        lblT2.setBounds(20, 180, 100, 25);
        lblT3.setBounds(20, 210, 100, 25);

        txtStudNo.setBounds(150, 15, 200, 25);
        txtName.setBounds(150, 45, 200, 25);
        rdbMale.setBounds(150, 75, 90, 25);
        rdbFemale.setBounds(330, 75, 90, 25);
        cmbsubCode.setBounds(150, 110, 200, 25);
        txtTest1.setBounds(150, 150, 200, 25);
        txtT2.setBounds(150, 180, 200, 25);
        txtT3.setBounds(150, 210, 200, 25);

        btnAdd.setBounds(20, 250, 120, 20);
        btnClear.setBounds(20, 290, 120, 20);
        btnDisplay.setBounds(180, 250, 120, 20);
        btnClose.setBounds(180, 290, 120, 20);
        taDisplay.setBounds(20, 320, 500, 350);
        taDisplay.setVisible(false);

        setContentPane(pnl);

        //Add ActionListner
        btnAdd.addActionListener(new buttonAddHandhler());
        btnDisplay.addActionListener(new displayHandler());
        btnClear.addActionListener(new clearHandler());
        btnClose.addActionListener(new closeAction());
        radioGroup();

    }

    private void radioGroup() {
        ButtonGroup grp = new ButtonGroup();

        grp.add(rdbFemale);
        grp.add(rdbMale);
        rdbMale.setSelected(false);
        rdbFemale.setSelected(false);

    }

    private class buttonAddHandhler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            String studNo, studName, subCode, gender;
            int test1, test2, test3;
            double  semMark, finalMark , ex;

            studNo = txtStudNo.getText();
            studName = txtName.getText();
            subCode = (String) cmbsubCode.getSelectedItem();

            if (rdbFemale.isSelected()) {
                gender = rdbFemale.getText();
            } else {
                gender = rdbMale.getText();
            }

            test1 = Integer.parseInt(txtTest1.getText());
            test2 = Integer.parseInt(txtT2.getText());
            test3 = Integer.parseInt(txtT3.getText());

            student = new Student(studNo, studName, gender, subCode, test1, test2, test3);

            semMark = student.calcSemesteMark();

            if (semMark >= 50) {
                ex = Double.parseDouble(JOptionPane.showInputDialog("Your sm: " + semMark + " :Enter Exam mark: "));

                
                exam = student.getExamMark(ex);
                finalMark = student.calcFinalmark(exam);

            } else {
                exam = 0;
                JOptionPane.showMessageDialog(null, "Sorry you dont qualify for exam, u have sem mark of: " + semMark, "Message", JOptionPane.OK_OPTION);
            }

            arStudents.add(new Student(student.getStudNo(), student.getName(), student.getGender(), student.getSubject(), student.getTest1(), student.getTest2(), student.getTest3()));

        }

    }

    private class displayHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            taDisplay.setVisible(true);
            // setResizable(false);
            taDisplay.setText("Stud No:\t" + "Name:\t" + "Subject:\t" + "SM:\t" + "EXM:\t" + "FM:");

            for (Student arStudent : arStudents) {
                taDisplay.append(arStudent.toString());
            }

        }
    }

    private class clearHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            taDisplay.setVisible(false);
            txtStudNo.setText("");
            txtName.setText("");
            txtTest1.setText("");
            txtT2.setText("");
            txtT3.setText("");
            rdbMale.setSelected(true);
            rdbFemale.setSelected(false);
            txtStudNo.requestFocus();

        }

    }

    private class closeAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int confirmExit;

            confirmExit = JOptionPane.showConfirmDialog(null, "Do you want to exit appltn?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirmExit == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else {
                txtStudNo.requestFocus();
            }
        }

    }

}
