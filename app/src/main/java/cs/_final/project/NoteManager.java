package cs._final.project;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class NoteManager implements ActionListener {
    int numberOfNotes = 0;
    String saveDirectory = "Note_Folder";
    JPanel notesField;
    JPanel NotesPanel;

    

    public NoteManager() {
        CreateWindow();
        PopulateNotesField();
    }

    void CreateWindow() {
        //Create Window
        JFrame frame = new JFrame("FILE CABINET");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        notesField = new JPanel(new BorderLayout());
        frame.add(notesField);


        JButton CreateNewNoteButton = new JButton("Create Note");
        CreateNewNoteButton.setActionCommand("Create");
        CreateNewNoteButton.addActionListener(this);
        notesField.add(CreateNewNoteButton, BorderLayout.PAGE_END);
    }

    void PopulateNotesField() {
        NotesPanel = new JPanel(new GridLayout(0,2, 10, 10));
        notesField.add(NotesPanel, BorderLayout.CENTER);

        File NotesFolder = new File(saveDirectory);
        if(!NotesFolder.exists())
            NotesFolder.mkdir();

        File[] noteFiles = NotesFolder.listFiles();
        
        
        if (noteFiles != null && noteFiles.length > 0) {
            numberOfNotes = noteFiles.length;

            for (File file : noteFiles) {
                AddNoteEntryButton(file);
            }

        }
    }

    void AddNoteEntryButton(File file) {
            JButton OpenNoteButton = new JButton("open: " + file.getName());

            OpenNoteButton.setActionCommand(file.getName());
            OpenNoteButton.addActionListener(this);
            NotesPanel.add(OpenNoteButton);      
            notesField.updateUI(); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Create") {
            File NewNote = new File(saveDirectory, "Note_" + numberOfNotes + ".txt");

            try {
                NewNote.createNewFile();
            } catch (Exception exception) {
                System.err.println(exception.toString());
                return;
            }

            new Note(NewNote);
            numberOfNotes++;
            AddNoteEntryButton(NewNote);
        }

        else {
            File NoteToOpen = new File(saveDirectory, e.getActionCommand());
            new Note(NoteToOpen);
        }
    }
}
