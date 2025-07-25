package org.sniffer.GUI.menus;

import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenu extends JMenu {
   private SnifferDashboard dashboard;


   private JButton saveButton = new JButton("Save");
   private JButton openButton = new JButton("Open");


    public FileMenu(SnifferDashboard dashboard) {
        super("File");
        this.dashboard = dashboard;

        //adding buttons
        this.add(saveButton);
        this.add(openButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO add save functionality
            }
        });

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO add loading functionality
            }
        });

    }


}
