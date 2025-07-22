package org.sniffer.GUI;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DetailFrameAuthenticator {
    private SnifferDashboard dashboard;

    private DetailFrame frame;
    private boolean authenticated = false;


    public DetailFrameAuthenticator(SnifferDashboard dashboard) {
        this.dashboard = dashboard;
    }

    public void createDetailFrame(){
        this.frame = new DetailFrame(dashboard);
        this.authenticated = true;
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                authenticated = false;
            }
        });
    }


    //getters
    public DetailFrame getDetailFrame(){
        if(authenticated){
            return this.frame;
        }
        return null;

    }
    public boolean isAuthenticated() {
        return authenticated;
    }

    //setters
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

}
