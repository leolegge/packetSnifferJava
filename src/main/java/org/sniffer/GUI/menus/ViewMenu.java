package org.sniffer.GUI.menus;

import org.sniffer.GUI.ApplicationFrame;
import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class allows for different parts of the menu to be toggled and un-toggled for a cleaner look
 *
 */

public class ViewMenu extends JMenu {
    private SnifferDashboard dashboard;
    private JButton applicationView = new JButton("Application view");

    public ViewMenu(SnifferDashboard dashboard) {
        super("View");
        this.dashboard = dashboard;
        this.add(applicationView);


        //listeners
        applicationView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ApplicationFrame applicationFrame = new ApplicationFrame(dashboard);

            }
        });


    }
}
