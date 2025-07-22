package org.sniffer.GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the panel to hold the query line in which users can enter a query as well as start and stop the program
 *
 */
public class PacketQueryPanel extends JPanel {

    private JTextField queryField = new JTextField();

    //TODO refcator filterButtonPanel and startStopSnifferPanel into two separate classes to increase maintainability
    private JPanel filterButtonPanel = new JPanel(new BorderLayout());
    private JButton applyFilterButton = new JButton("Apply filter");
    private JButton clearFilterButton = new JButton("Clear filter");

    private JPanel startStopSnifferPanel = new JPanel(new BorderLayout());
    private JButton startSnifferButton = new JButton("Start sniffer");
    private JButton stopSnifferButton = new JButton("Stop sniffer");

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
        filterButtonPanel.add(applyFilterButton, BorderLayout.WEST);
        filterButtonPanel.add(clearFilterButton, BorderLayout.EAST);

        applyFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        clearFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        //setting up start and stop buttons
        this.add(startStopSnifferPanel, BorderLayout.WEST);
        startStopSnifferPanel.add(startSnifferButton, BorderLayout.WEST);
        startStopSnifferPanel.add(stopSnifferButton, BorderLayout.EAST);
        startSnifferButton.setEnabled(false);
        stopSnifferButton.setEnabled(false);

        startSnifferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSnifferButton.setEnabled(false);
                stopSnifferButton.setEnabled(true);
            }
        });

        stopSnifferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startSnifferButton.setEnabled(true);
                stopSnifferButton.setEnabled(false);
            }
        });



    }

    //getters
    public JTextField getQueryField() {
        return queryField;
    }
    public JButton getApplyFilterButton() {
        return applyFilterButton;
    }
    public JButton getClearFilterButton() {
        return clearFilterButton;
    }
    public JButton getStartSnifferButton() {
        return startSnifferButton;
    }
    public JButton getStopSnifferButton() {
        return stopSnifferButton;
    }


}
