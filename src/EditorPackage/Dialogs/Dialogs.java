package EditorPackage.Dialogs;

import javax.swing.*;

public class Dialogs {

    public Integer YesNoCancelDialog(JFrame frame, String modalMessage, String modalTitle, Integer messageType, Icon icon, Object[] options, Object initValue) {
        return JOptionPane.showOptionDialog(frame, modalMessage, modalTitle, JOptionPane.YES_NO_CANCEL_OPTION, messageType, icon, options, initValue);
    }

    public static void main(String[] args) {

    }

}
