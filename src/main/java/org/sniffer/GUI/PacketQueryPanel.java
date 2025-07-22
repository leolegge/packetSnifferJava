package org.sniffer.GUI;



import org.sniffer.GUI.snifferDashboardPanels.FilterButtonPanel;
import org.sniffer.GUI.snifferDashboardPanels.StartStopSnifferPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the panel to hold the query line in which users can enter a query as well as start and stop the program
 *
 */
public class PacketQueryPanel extends JPanel {

    private final JTextField queryField = new JTextField();
    private final FilterButtonPanel filterButtonPanel = new FilterButtonPanel();
    private final StartStopSnifferPanel startStopSnifferPanel = new StartStopSnifferPanel();

    public PacketQueryPanel() {
        this.setPreferredSize(new Dimension(0, 20));
        this.setLayout(new BorderLayout());
        this.add(queryField, BorderLayout.CENTER);


        queryField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });


        //setting up filters buttons
        this.add(filterButtonPanel, BorderLayout.EAST);


        //setting up start and stop buttons
        this.add(startStopSnifferPanel, BorderLayout.WEST);


    }

    //getters
    public JTextField getQueryField() {
        return queryField;
    }

    public FilterButtonPanel getFilterButtonPanel() {
        return filterButtonPanel;
    }

    public StartStopSnifferPanel getStartStopSnifferPanel() {
        return startStopSnifferPanel;
    }





}
