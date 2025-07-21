package org.sniffer.GUI;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the panel to hold the query line in which users can enter a query
 *
 */
public class PacketQueryPanel extends JPanel {

    JTextField textField = new JTextField();
    JButton applyFilterButton = new JButton("Apply filter");

    public PacketQueryPanel() {
        this.setPreferredSize(new Dimension(0, 20));
        this.setLayout(new BorderLayout());
        this.add(textField, BorderLayout.CENTER);
        this.add(applyFilterButton, BorderLayout.EAST);

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        applyFilterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });



    }
}
