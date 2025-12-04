package cs._final.project;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;
public class NoteManager implements ActionListener {
    int counter = 0;
    
    public NoteManager() {
        //Create Window
        JFrame frame = new JFrame("FILE CABINET");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        JPanel contentPanel = new JPanel(new BorderLayout());
        frame.add(contentPanel);

        File NotesFolder = new File("Note_Folder");
        File[] noteFiles = NotesFolder.listFiles();

        if (noteFiles != null && noteFiles.length > 0) {
            JPanel NotesPanel = new JPanel(new GridLayout(0,2, 10, 10));

            for (File file : noteFiles) {
                System.out.println("Listing Files");
                JButton OpenNoteButton = new JButton("open: " + file.getName());
                NotesPanel.add(OpenNoteButton);          
            }
            contentPanel.add(NotesPanel, BorderLayout.CENTER);
        }
        else {
            contentPanel.add(new JLabel("No Notes Exist", 0), BorderLayout.CENTER);
        }

        JButton CreateNewNoteButton = new JButton("Create Note");
        CreateNewNoteButton.addActionListener(this);
        contentPanel.add(CreateNewNoteButton, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
        counter++;
        System.out.println("Hello World: " + counter);
    }
}
