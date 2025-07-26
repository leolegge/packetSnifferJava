package org.sniffer.GUI.packetPanels;

import org.pcap4j.packet.Packet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PayloadPanel extends JPanel {

    private PayloadPanelScrollable payloadPanelScrollable;
    private JPanel layersContainer = new JPanel();

    public PayloadPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.RED);
        this.payloadPanelScrollable = new PayloadPanelScrollable(layersContainer);
        this.add(payloadPanelScrollable, BorderLayout.CENTER);
        this.layersContainer.setLayout(new BoxLayout(layersContainer, BoxLayout.Y_AXIS));

    }

    public void setUpPayloadPanel(Packet packet, int layerCount) {

        layersContainer.removeAll();
        Packet current = packet;
        int layer = 1;

        while (current != null) {
            ExpandablePanel newPanel = new ExpandablePanel("Layer " + layer + ": " + current.getClass().getSimpleName(), current.toString());
            newPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            newPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, newPanel.getPreferredSize().height));
            layersContainer.add(newPanel);
            current = current.getPayload(); // go to next inner layer
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

}
