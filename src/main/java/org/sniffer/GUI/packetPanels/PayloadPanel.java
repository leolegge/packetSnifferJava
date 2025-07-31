package org.sniffer.GUI.packetPanels;

import org.pcap4j.packet.Packet;
import org.sniffer.GUI.SnifferDashboard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class contains all information relating to the payload of a panel and sets up the GUI to display it
 *
 */
public class PayloadPanel extends JPanel {

    private PayloadPanelScrollable payloadPanelScrollable;
    private JPanel layersContainer = new JPanel();

    /**
     * Constructor for this class used to set up initial state of the payload panel
     *
     */
    public PayloadPanel() {
        this.setLayout(new BorderLayout());
        this.payloadPanelScrollable = new PayloadPanelScrollable(layersContainer);
        this.add(payloadPanelScrollable, BorderLayout.CENTER);
        this.layersContainer.setLayout(new BoxLayout(layersContainer, BoxLayout.Y_AXIS));

    }

    //general methods
    public void setUpPayloadPanel(Packet packet, SnifferDashboard dashboard) {

        layersContainer.removeAll();
        Packet current = packet;
        int layer = 1;

        while (current != null) {
            String headerText = (current.getHeader() != null)
                    ? current.getHeader().toString()
                    : "[No Header Available]";
            ExpandablePanel newPanel =
                    new ExpandablePanel("Layer " + layer + ": " + current.getClass().getSimpleName(),
                            headerText, dashboard);
            newPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            newPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, newPanel.getPreferredSize().height));
            layersContainer.add(newPanel);
            current = current.getPayload();
            layer++;
        }

        this.revalidate();
        this.repaint();

    }

    public int findPacketLayersCount(Packet packet) {
        Packet current = packet;
        int layers = 1;
        while (current != null) {
            current = current.getPayload(); // go to next inner layer
            layers++;
        }
        return layers;
    }

    //getters
    public JPanel getLayersContainer() {
        return layersContainer;
    }

}
