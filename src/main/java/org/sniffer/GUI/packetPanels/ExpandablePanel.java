package org.sniffer.GUI.packetPanels;

import javax.swing.*;
import java.awt.*;

public class ExpandablePanel extends JPanel {

    JButton expandButton = new JButton("Expand");
    JLabel label;
    JPanel expandableTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JTextArea contentArea = new JTextArea();

    public ExpandablePanel(String label, String content) {
        this.setLayout(new BorderLayout());
        this.label = new JLabel(label);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);

        expandableTitle.add(expandButton);
        expandableTitle.add(this.label);

        this.add(expandableTitle, BorderLayout.NORTH);

        //TODO add content area to the CENTRE and make it not visible until the expand button is pressed



    }



}
