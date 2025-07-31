package org.sniffer.GUI.packetPanels;

import javax.swing.*;

/**
 * This panel enables the payload panel to become scrollable if the ExpandablePanels become too large to contain
 * within the defined space on the screen
 *
 */
public class PayloadPanelScrollable extends JScrollPane {

    public PayloadPanelScrollable(JPanel layersContainer) {
        super(layersContainer);
    }
}
