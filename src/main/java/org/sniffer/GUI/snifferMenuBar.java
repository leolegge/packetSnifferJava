package org.sniffer.GUI;

import org.pcap4j.core.PcapNativeException;

import javax.swing.*;

/**
 * This is the main menu bar class which extends the JMenuBar class this will contain all possible options
 * in the menu bar which is displayed at the top of the frame
 */
public class snifferMenuBar extends JMenuBar {
    private selectNetworkMenu networkMenu = new selectNetworkMenu("Network", this.dashboard);
    private SnifferDashboard dashboard;

    /**
     *
     * @param dashboard is a reference to the main frame so it can be passed down to other menus and
     *                  changed in them
     * @throws PcapNativeException thrown when Pcap fails
     */
    public snifferMenuBar(SnifferDashboard dashboard) throws PcapNativeException {
        super();
        this.add(networkMenu);
        this.dashboard = dashboard;
    }

}
