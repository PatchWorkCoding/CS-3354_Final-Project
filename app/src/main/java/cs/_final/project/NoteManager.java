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

        //Create a contain to hold all the "Open Note_##" buttons
        notesField = new JPanel(new BorderLayout());
        frame.add(notesField);

        //Adds a button to create a new Note
        JButton CreateNewNoteButton = new JButton("Create Note");
        CreateNewNoteButton.addActionListener(this); //Makes this the listener since it will handle note creations
        notesField.add(CreateNewNoteButton, BorderLayout.PAGE_END);
    }

    void PopulateNotesField() {
        NotesPanel = new JPanel(new GridLayout(0,2, 10, 10));
        notesField.add(NotesPanel, BorderLayout.CENTER);

        //Open up the save directory
        File NotesFolder = new File(saveDirectory);

        //if it doesn't exist, create it
        if(!NotesFolder.exists())
            NotesFolder.mkdir();

        File[] noteFiles = NotesFolder.listFiles();
        if (noteFiles != null && noteFiles.length > 0) {

            numberOfNotes = noteFiles.length; //Log the number of notes for later file naming

            //Create a button for each note in the save directory
            for (Integer i = 0; i < noteFiles.length; i++) {
                AddNoteEntryButton(new Note(noteFiles[i]));
            }

        }
    }

    void AddNoteEntryButton(Note noteToOpen) {
            JButton OpenNoteButton = new JButton(noteToOpen.getFileName());

            OpenNoteButton.setActionCommand("open");
            OpenNoteButton.addActionListener(noteToOpen);
            NotesPanel.add(OpenNoteButton);      
            notesField.updateUI(); 
    }

    //This action is only performed when the "Create New Note" button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        //Create the file to hold the Nth note
        File newNoteFile = new File(saveDirectory, "Note_" + numberOfNotes + ".txt");
        try {
            newNoteFile.createNewFile();
        } catch (Exception exception) {
            System.err.println(exception.toString());
            return;
        }

        //Instantiate the Nth note
        Note createdNote = new Note(newNoteFile);
        AddNoteEntryButton(createdNote);
        createdNote.openEditorWindow();

        numberOfNotes++;
    }
}
