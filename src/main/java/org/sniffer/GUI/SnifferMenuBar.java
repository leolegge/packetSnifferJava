package org.sniffer.GUI;

import org.pcap4j.core.PcapNativeException;
import org.sniffer.GUI.menus.EditMenu;
import org.sniffer.GUI.menus.SelectNetworkMenu;
import org.sniffer.GUI.menus.ViewMenu;

import javax.swing.*;

/**
 * This is the main menu bar class which extends the JMenuBar class this will contain all possible options
 * in the menu bar which is displayed at the top of the frame
 */
public class SnifferMenuBar extends JMenuBar {
    private SnifferDashboard dashboard;

    private SelectNetworkMenu networkMenu;
    private EditMenu editMenu = new EditMenu();
    private ViewMenu viewMenu;




    /**
     *
     * @param dashboard is a reference to the main frame so it can be passed down to other menus and
     *                  changed in them
     * @throws PcapNativeException thrown when Pcap fails
     */
    public SnifferMenuBar(SnifferDashboard dashboard) throws PcapNativeException {
        super();
        this.dashboard = dashboard;

        networkMenu = new SelectNetworkMenu(this.dashboard);
        viewMenu = new ViewMenu(this.dashboard);

        //Adding menus to the menu bar
        this.add(networkMenu);
        this.add(editMenu);
        this.add(viewMenu);


    }


    //getters
    public SelectNetworkMenu getNetworkMenu(){
        return networkMenu;
    }

    public ViewMenu getViewMenu(){
        return viewMenu;
    }


}
