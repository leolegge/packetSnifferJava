package org.sniffer.GUI;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class is used to contain the DetailFrame and ensure that it exists preventing a handful of errors also proving
 * helpful methods that allow for prevention of opening multiple windows
 *
 */
public class DetailFrameAuthenticator {
    private SnifferDashboard dashboard;

    private DetailFrame frame;
    private boolean authenticated = false;

    /**
     * Set up the authenticator in its initial state
     *
     * @param dashboard is a reference to the SnifferDashboard in order to access packet information to display
     */
    public DetailFrameAuthenticator(SnifferDashboard dashboard) {
        this.dashboard = dashboard;
    }

    //general methods
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
