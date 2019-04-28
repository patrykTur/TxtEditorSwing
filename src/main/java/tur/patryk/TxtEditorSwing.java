package tur.patryk;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;

public class TxtEditorSwing {

    private JPanel panelMain;
    private JLabel filesListLabel;
    private JPanel panelButtons;
    private JButton removeTextFromFilesButton;
    private JButton cancelButton;
    private JTextArea filesListTextArea;
    private JPanel menuPanel;
    private JPanel labelPanel;

    private JMenu menu;
    private JMenuItem menuChooseFolder;
    private JMenuItem menuExit;

    private static Collection files;
    private static JMenuBar menuBar = new JMenuBar();

    public TxtEditorSwing() {

        menu = new JMenu("File");
        menuChooseFolder = new JMenuItem("Choose folder");
        menuExit = new JMenuItem("Exit");
        menu.add(menuChooseFolder);
        menu.add(menuExit);
        menuBar.add(menu);

        menuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuChooseFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filesListTextArea.setText("");

                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                // set the selection mode to directories only
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                // invoke the showsOpenDialog function to show the save dialog
                int r = fileChooser.showOpenDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {

                    File path = new File(fileChooser.getSelectedFile().getAbsolutePath());

                    try {
                        String[] extensions = {"txt"};
                        boolean recursive = false;      //check only path provided

                        files = FileUtils.listFiles(path, extensions, recursive);

                        for (Iterator iterator = files.iterator(); iterator.hasNext(); ) {
                            File file = (File) iterator.next();
                            filesListTextArea.append(file.getAbsolutePath() + "\n");              //lists all .txt files from specified folder
                        }
                        removeTextFromFilesButton.setEnabled(true);
                        cancelButton.setEnabled(true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filesListTextArea.setText("");
                cancelButton.setEnabled(false);
                removeTextFromFilesButton.setEnabled(false);
            }
        });

        removeTextFromFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (filesListTextArea.getText().length() != 0) {
                        for (Iterator iterator = files.iterator(); iterator.hasNext(); ) {
                            File file = (File) iterator.next();
                            FileUtils.writeStringToFile(file, "", false);
                        }
                        JOptionPane.showMessageDialog(null, "Success");
                        filesListTextArea.setText("");
                        cancelButton.setEnabled(false);
                        removeTextFromFilesButton.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "No files");
                        removeTextFromFilesButton.setEnabled(false);
                        cancelButton.setEnabled(false);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("TxtEditor");
        frame.setContentPane(new TxtEditorSwing().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}
