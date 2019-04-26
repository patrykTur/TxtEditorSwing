package tur.patryk;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TxtEditorSwing {

    private JPanel panelMain;

    public static void main(String[] args) {

        JFrame frame = new JFrame("TxtEditor");
        frame.setContentPane(new TxtEditorSwing().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //frame.setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuChooseFolder = new JMenuItem("Choose folder");
        JMenuItem menuExit = new JMenuItem("Exit");
        menu.add(menuChooseFolder);
        menu.add(menuExit);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

        menuExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
