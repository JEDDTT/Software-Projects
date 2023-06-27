/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vut.gui;

import java.util.ArrayList;
import javax.swing.JFrame;
import vut.data.Student;

/**
 *
 * @author Ngongo 214046087
 */
public class StudentScreenRun {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StudentScreen screen;

        screen = new StudentScreen();

        screen.setTitle("STUDENT SCREEN FORM");
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setVisible(true);
        screen.setSize(500, 500);
        screen.setResizable(false);

        
    }

}
