package org.sniffer.GUI.menus;

import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
                    System.out.println("Selected network item: " + item.getText());
                    dashboard.setNetworkInterface(getNetworkInterface(item.getText()));
                    System.out.println("Dashboard current network interface: " + dashboard.getNetworkInterface());

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
