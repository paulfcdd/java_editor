package EditorPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    public EditorFrame() {
        frame = this;

        setTitle("Simple editor");

        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        menuBar.add(file);

        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);

        JTextArea inputField = new JTextArea(20, 120);
        add(inputField);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputData = inputField.getText();
                checkInputData(inputData);
            }
        });

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                String inputData = inputField.getText();
                checkInputData(inputData);
            }
        });

        setJMenuBar(menuBar);
        pack();
        setVisible(true);
    }

    private Integer YesNoCancelDialog(String modalMessage, String modalTitle, Integer mesageType, Icon icon, Object[] options, Object initValue) {
        return JOptionPane.showOptionDialog(frame, modalMessage, modalTitle, JOptionPane.YES_NO_CANCEL_OPTION, mesageType, icon, options, initValue);
    }

    private void saveFile(String data) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(frame);
    }

    private void checkInputData(String inputData) {
        if (inputData.length() == 0) {
            System.exit(0);
        } else {
            Object[] options = {"Yes", "No", "Cancel"};
            Integer confirmClose = YesNoCancelDialog("Document was changed. Do you want to save it?", "Do you want to leave?", JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
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