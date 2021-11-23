import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class GoToLine {

    int getLineNumber(String text) {
        int enteredLineNumber = getEnteredValue();

        if (enteredLineNumber != -1) {

            int totalLineCount = 1;

            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == '\n') {
                    totalLineCount++;
                }
            }

            if (enteredLineNumber > totalLineCount || enteredLineNumber == 0) {

                JOptionPane.showMessageDialog(null,
                        "Entered number is bigger than the total number of lines!",
                        "Error",
                        JOptionPane.PLAIN_MESSAGE);

            } else if (enteredLineNumber > 0) {

                JTextArea textArea = new JTextArea();
                textArea.setText(text);

                return (getLineStartIndex(textArea, enteredLineNumber));
            }
        }

        return -1;
    }

    private int getEnteredValue() {
        int index = -1;
        JTextField textField = new JTextField(10);
        JLabel label = new JLabel("Go");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(textField, BorderLayout.CENTER);
        panel.add(label, BorderLayout.NORTH);
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new CustomDocumentFilter());

        Object[] options = {"Go", "Cancel"};
        JOptionPane.showOptionDialog(null,
                panel, "Go to line",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[1]);
        try {
            index = Integer.parseInt(textField.getText());
        } catch (NumberFormatException ex) {
            System.out.println("User has cancelled. Nothing to parse!");
            ex.printStackTrace();
        }

        return index;
    }


    private int getLineStartIndex(JTextArea textComp, int lineNumber) {
        try {
            return textComp.getLineStartOffset(lineNumber - 1);
        } catch (BadLocationException ex) {
            return -1;
        }
    }

}
