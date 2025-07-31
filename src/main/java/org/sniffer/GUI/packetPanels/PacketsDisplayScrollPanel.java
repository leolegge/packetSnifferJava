package org.sniffer.GUI.packetPanels;

import javax.swing.*;
import java.awt.*;

/**
 * This class is a scroll panel which allows the JTable to be scrolled though instead of just trailing off of the screen
 *
 */
public class PacketsDisplayScrollPanel extends JScrollPane {

    public PacketsDisplayScrollPanel(JTable table) {
        super(table);
    }
}
