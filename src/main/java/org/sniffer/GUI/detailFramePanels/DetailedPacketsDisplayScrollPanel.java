package org.sniffer.GUI.detailFramePanels;

import javax.swing.*;

/**
 * Scrollable container for the detailed packets table
 *
 */
public class DetailedPacketsDisplayScrollPanel extends JScrollPane {

    public DetailedPacketsDisplayScrollPanel(JTable table) {
        super(table);
    }
}
