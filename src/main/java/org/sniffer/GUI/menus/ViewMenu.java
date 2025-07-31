package org.sniffer.GUI.menus;

import org.sniffer.GUI.DetailFrame;
import org.sniffer.GUI.DetailFrameAuthenticator;
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
    private JButton applicationView = new JButton("Detail view");
    private JButton worldView = new JButton("World View");

    private DetailFrameAuthenticator detailFrameAuthenticator;

    /**
     * This is the constructor for the menu and sets up buttons to be able to activate separate windows for
     * better visibility of packets
     *
     * @param dashboard is a reference to the main dashboard in order to load information into new panels
     */
    public ViewMenu(SnifferDashboard dashboard) {
        super("View");
        this.dashboard = dashboard;
        this.add(applicationView);
        this.add(worldView);
        this.detailFrameAuthenticator = new DetailFrameAuthenticator(dashboard);

        //listeners
        applicationView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!detailFrameAuthenticator.isAuthenticated()) {
                    detailFrameAuthenticator.createDetailFrame();
                }
            }
        });

        worldView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WorldViewFrame worldViewFrame = new WorldViewFrame(dashboard);
            }
        });
    }

    //getters
    public DetailFrameAuthenticator getDetailFrameAuthenticator() {
        return detailFrameAuthenticator;
    }

}
