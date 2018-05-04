package EditorPackage;

import EditorPackage.Dialogs.Dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Editor {

    public Editor() {

    }

    public EventQueue run() {

        EventQueue eventQueue = new EventQueue();

        eventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                EditorFrame editorFrame = new EditorFrame();
                editorFrame.setSize(new Dimension(400, 400));
                editorFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

        return eventQueue;
    }

    public static void main(String[] args) {

    }

}

class EditorFrame extends JFrame {

    private JFrame frame;
    private Dialogs dialogs;
    private JFileChooser fileChooser;
    private JTextArea inputField;
    private Integer inputCharsNum;
    static private final String newline = "\n";

    public EditorFrame() {
        frame = this;
        dialogs = new Dialogs();
        fileChooser = new JFileChooser();
        inputField = new JTextArea(20, 120);
        inputCharsNum = 0;

        setTitle("Simple editor");

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        menuBar.add(file);

        JMenuItem newFile = new JMenuItem("New");
        file.add(newFile);

        JMenuItem open = new JMenuItem("Open");
        file.add(open);

        JMenuItem save = new JMenuItem("Save");
        file.add(save);

        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);

        inputField = new JTextArea(20, 120);
        add(inputField);

        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFile();
            }
        });

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile(inputField.getText());
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputData = inputField.getText();
                checkInputDataOnExit(inputData);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String inputData = inputField.getText();
                checkInputDataOnExit(inputData);
            }
        });

        setJMenuBar(menuBar);
        pack();
        countInputChars();
        setVisible(true);
    }

    private void newFile() {

        if (inputField.getText().length() != inputCharsNum) {
            saveFile(inputField.getText());
        } else {
            inputField.setText("");
            countInputChars();
        }

    }

    private void openFile() {
        Integer openDialog = fileChooser.showOpenDialog(frame);
        StringBuilder fileContent = new StringBuilder();

        if (openDialog == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String sCurrentLine;
                while ((sCurrentLine = bufferedReader.readLine()) != null) {
                    fileContent.append(sCurrentLine).append(newline);
                }
                inputField.setText(fileContent.toString());
                countInputChars();
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }
        }
    }

    private void saveFile(String data) {
        Integer saveDialog = fileChooser.showSaveDialog(frame);

        if (saveDialog == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            try (FileWriter writer = new FileWriter(filePath, false)) {
                writer.write(data);
                writer.flush();
                countInputChars();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    private void countInputChars() {
        inputCharsNum = inputField.getText().length();
    }

    private void checkInputDataOnExit(String inputData) {
        if (inputData.length() == inputCharsNum) {
            System.exit(0);
        } else {
            Object[] options = {"Yes", "No", "Cancel"};
            Integer confirmClose = dialogs.YesNoCancelDialog(frame, "Document was changed. Do you want to save it?", "Do you want to leave?", JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
            //0 - yes, 1 - no
            switch (confirmClose) {
                case 0:
                    saveFile(inputData);
                    break;
                case 1:
                    frame.dispose();
                    break;
                case 2:
                    break;
            }
        }
    }


}