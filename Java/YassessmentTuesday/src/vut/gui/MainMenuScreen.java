/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vut.gui;

import java.util.ArrayList;
import javax.swing.*;
import vut.data.Student;

/**
 *
 * @author bongwala ncd
 */
public class MainMenuScreen extends JFrame
{

    JMenuItem miExit, miHighest, miSearch, miAdd, miView;
    JMenu mnFile, mnSt;
    private final ArrayList<Student> arStudents = new ArrayList<>();

    public MainMenuScreen()
    {

        miExit = new JMenuItem("Exit");
        miSearch = new JMenuItem("Search Student");
        miHighest = new JMenuItem("Highest Final Mark");
        miAdd = new JMenuItem("Add new ");
        miView = new JMenuItem("View All Students");

        mnFile = new JMenu("File");
        mnSt = new JMenu("Employee");

        mnFile.add(miExit);
        mnFile.add(miSearch);
        mnFile.add(miHighest);
        
        mnSt.add(miAdd);
        mnSt.add(miView);
        
        JMenuBar jmBar = new JMenuBar();
        
        jmBar.add(mnFile);
        jmBar.add(mnSt);
        
        setJMenuBar(jmBar);

    }
}
