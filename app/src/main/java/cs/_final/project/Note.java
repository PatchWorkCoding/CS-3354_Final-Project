package cs._final.project;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.*;

public class Note extends WindowAdapter implements ActionListener {
    File saveFile;
    JFrame frame;
    JTextArea inputField;
    String text = "";
    boolean isWindowOpen = false;
    

    public Note(File file) {
        //Initializes the saveFile
        saveFile = file;

        //Creates a file reader
        Scanner Reader;
        try {
            Reader = new Scanner(saveFile);    
        } catch (Exception e) {
            System.err.println(e.toString());
            return;
        }

        //Reads in the file data
        while (Reader.hasNextLine()) {
            text += Reader.nextLine();
            text += System.lineSeparator();
        }

        Reader.close();
    }

    public void openEditorWindow() {
        //If the window isn't open Create it
        if(!isWindowOpen) {
            createEditorWindow();
        }

        //If the window is open bring it to the front
        else {

            frame.toFront();
            inputField.requestFocus();
        }
    }

    void createEditorWindow() {
        //Creates Window
        frame = new JFrame(saveFile.getName());
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(this);
        frame.setVisible(true);

        //Creates organizing container
        JPanel container = new JPanel(new BorderLayout());
        frame.add(container);

        //Creates text input field
        inputField = new JTextArea();
        inputField.append(text);
        inputField.setLineWrap(true);
        container.add(inputField, BorderLayout.CENTER);
        
        //Creates Button to save text to a file
        JButton saveFileButton = new JButton("Save File");
        saveFileButton.setActionCommand("save");
        saveFileButton.addActionListener(this);
        container.add(saveFileButton, BorderLayout.PAGE_END);

        isWindowOpen = true;
    }

    public String getFileName() {
        return saveFile.getName();
    }

    void saveTextToFile() {
        //Saves off edited text into a file
        try {
            text = inputField.getText();
            FileWriter writer = new FileWriter(saveFile.getAbsolutePath());
            writer.write(text);
            writer.close();

        } catch (Exception exception) {
            System.err.println(exception.toString());
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        isWindowOpen = false;
        saveTextToFile();
        super.windowClosing(e);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "save") {
            saveTextToFile();
        }

        else if (e.getActionCommand() == "open") {
            openEditorWindow();
        }
    }
}
