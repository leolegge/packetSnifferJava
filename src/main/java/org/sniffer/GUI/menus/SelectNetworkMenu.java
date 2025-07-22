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
                    dashboard.getDashboardPacketQueryPanel().getStartSnifferButton().setEnabled(true);
                    //TODO add a play button to start reading after selecting network to sniff



                    dashboard.setPacketSniffer(new PacketSniffer(dashboard.getNetworkInterface(),
                            PcapNetworkInterface.PromiscuousMode.PROMISCUOUS));

                    System.out.println(dashboard.getPacketSniffer().toString());
                    //Thread snifferThread = new Thread(sniffer);
                    //snifferThread.start();


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


}
