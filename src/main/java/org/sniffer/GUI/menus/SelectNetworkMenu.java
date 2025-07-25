package org.sniffer.GUI.menus;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;
import org.sniffer.GUI.SnifferDashboard;
import org.sniffer.backend.PacketSniffer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;


/**
 * This is the menu for the network selection at the top of the program window
 * The class extends JMenu and initializes all possible networks to sniff into the frame
 *
 */
public class SelectNetworkMenu extends JMenu {

    private SnifferDashboard dashboard;
    private ArrayList<JRadioButtonMenuItem> networkItems = new ArrayList<JRadioButtonMenuItem>();
    private ButtonGroup networkGroup = new ButtonGroup();
    private List<PcapNetworkInterface> nif = Pcaps.findAllDevs();

    /**
     *
     * @param dashboard The reference to the main frame so different information can be displayed from this class
     * @throws PcapNativeException thrown when Pcap fails
     */
    public SelectNetworkMenu(SnifferDashboard dashboard) throws PcapNativeException {
        super("Network");
        this.dashboard = dashboard;

        //Getting all possible networks

        if (nif == null) {
            System.out.println("No network interfaces found.");

        }else{
            for (PcapNetworkInterface ni : nif) {
                networkItems.add(new JRadioButtonMenuItem(ni.getDescription()));
            }
        }

        //Grouping all the radio buttons
        for (JRadioButtonMenuItem item : networkItems) {
            networkGroup.add(item);
            this.add(item);
        }


        //Adding listeners so different output is selected when different button is chosen
        for (JRadioButtonMenuItem item : networkItems) {
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dashboard.setNetworkInterface(getNetworkInterface(item.getText()));
                    dashboard.getDashboardPacketQueryPanel().getStartStopSnifferPanel().getStartSnifferButton().setEnabled(true);

                    //Sets up the packet sniffer once a network to sniff is chosen, NOTE - this doesn't run it
                    dashboard.setPacketSniffer(new PacketSniffer(dashboard,dashboard.getNetworkInterface(),
                            PcapNetworkInterface.PromiscuousMode.PROMISCUOUS));
                    dashboard.setSnifferThread(new Thread(dashboard.getPacketSniffer()));

                    dashboard.getDashboardPacketPanelsWrapper().getPacketsDisplayPanel().resetTable();
                    //If a detail frame is on the screen then reset when new network is selected
                    if(dashboard.getDashboardMenuBar().
                            getViewMenu().
                            getDetailFrameAuthenticator().
                            isAuthenticated()
                    ){
                        dashboard.getDashboardMenuBar().
                                getViewMenu().
                                getDetailFrameAuthenticator().
                                getDetailFrame().
                                resetMainDetailedPacketTable();
                    }

                }
            });
        }
    }

    public PcapNetworkInterface getNetworkInterface(String networkName) {
        for(PcapNetworkInterface ni : nif) {
            if (ni.getDescription().equals(networkName)) {
                return ni;
            }
        }
        return null;
    }

    public void disableRadioButtons(){
        for (JRadioButtonMenuItem item : networkItems) {
            item.setEnabled(false);
        }
    }

    public void enableRadioButtons(){
        for (JRadioButtonMenuItem item : networkItems) {
            item.setEnabled(true);
        }
    }


}
