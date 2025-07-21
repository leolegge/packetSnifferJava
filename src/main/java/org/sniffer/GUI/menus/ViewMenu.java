package org.sniffer.GUI.menus;

import org.sniffer.GUI.ApplicationFrame;
import org.sniffer.GUI.SnifferDashboard;
import org.sniffer.GUI.WorldViewFrame;

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
    private JButton worldView = new JButton("World View");

    public ViewMenu(SnifferDashboard dashboard) {
        super("View");
        this.dashboard = dashboard;
        this.add(applicationView);
        this.add(worldView);

        //listeners
        applicationView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO make this a user friendly way to see what applications use what packets
                ApplicationFrame applicationFrame = new ApplicationFrame(dashboard);
            }
        });

        worldView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WorldViewFrame worldViewFrame = new WorldViewFrame(dashboard);
            }
        });
    }
}
