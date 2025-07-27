package org.sniffer.GUI.menus;

import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This class is a JMenu which contains everything a user may want to edit data with
 *
 */

public class EditMenu extends JMenu {

    private SnifferDashboard dashboard;

    JButton clearPacketsDisplay = new JButton("Clear");

    public EditMenu(SnifferDashboard dashboard) {
        super("Edit");
        this.dashboard = dashboard;
        //this.add(clearPacketsDisplay);

        clearPacketsDisplay.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dashboard.getDashboardPacketPanelsWrapper().getPacketsDisplayPanel().resetTable();
                if(dashboard.getDashboardMenuBar().getViewMenu().getDetailFrameAuthenticator().isAuthenticated()) {
                    dashboard.getDashboardMenuBar().getViewMenu().getDetailFrameAuthenticator().
                            getDetailFrame().resetMainDetailedPacketTable();
                }
            }
        });
    }


}
