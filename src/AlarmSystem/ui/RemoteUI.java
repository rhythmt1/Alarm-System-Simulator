package AlarmSystem.ui;

import AlarmSystem.model.Remote;
import AlarmSystem.model.exception.SystemNotReadyException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class RemoteUI extends JInternalFrame {
    private static final int WIDTH = 175;
    private static final int HEIGHT = 75;
    private Remote theRemote;
    private Component theParent;

    //Constructor
    public RemoteUI(Remote r, Component parent) {
        super("Remote", false, false, false, false);
        theRemote = r;
        theParent = parent;
        JButton armBtn = new JButton(new ArmAction());
        add(armBtn);
        setSize(WIDTH, HEIGHT);
        setPosition(parent);
        setVisible(true);
    }

    //Sets the position of this remote control UI relative to parent component
    private void setPosition(Component parent) {
        setLocation(parent.getWidth() - getWidth(), 0);
    }

    //Represents the action to be taken when the system is armed using the button on the remote control
    private class ArmAction extends AbstractAction {
        ArmAction() {
            super("Arm");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                theRemote.activate();
            } catch (SystemNotReadyException e) {
                JOptionPane.showMessageDialog(theParent, e.getMessage());
            }
        }
    }}