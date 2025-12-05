package cs._final.project;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.*;

public class Note extends WindowAdapter {
    File saveFile;
    JTextArea inputField;
    String text = "";
    
    public Note(File _data) {
        saveFile = _data;
        Scanner Reader;
        try {
            Reader = new Scanner(saveFile);    
        } catch (Exception e) {
            System.err.println(e.toString());
            return;
        }

        while (Reader.hasNextLine()) {
            System.out.println(text);
            text += Reader.nextLine();
            text += System.lineSeparator();
            
        }

        //Create Window
        JFrame frame = new JFrame(_data.getName());
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(this);
        //frame.addWindowStateListener(null);
        frame.setVisible(true);

        inputField = new JTextArea();
        inputField.append(text);
        frame.add(inputField);
        
        Reader.close();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
        
        try {
            text = inputField.getText();
            FileWriter writer = new FileWriter(saveFile.getAbsolutePath());
            writer.write(text);
            writer.close();
        } catch (Exception exception) {
            System.err.println(exception.toString());
        }
        

        super.windowClosing(e);
    }
}
