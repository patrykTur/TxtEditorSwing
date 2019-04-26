package tur.patryk;

import javax.swing.*;

public class TxtEditorSwing {

    private JPanel panelMain;

    public static void main(String[] args) {

        JFrame frame = new JFrame("TxtEditor");
        frame.setContentPane(new TxtEditorSwing().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
