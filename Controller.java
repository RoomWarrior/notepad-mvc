import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Controller implements ActionListener {

    private Viewer viewer;
    private String fileName;
    private String defaultValueOfSearchBar;
    private boolean statusPanelFlag;
    private int scalePercent;


    public Controller(Viewer viewer) {
        this.viewer = viewer;
        statusPanelFlag = true;
        scalePercent = 100;
        defaultValueOfSearchBar = "";
    }

    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        if (command.equals("Open Document")) {

            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(new JFrame());
            File file = fileChooser.getSelectedFile();
            ReadFromFile rf = new ReadFromFile();
            String textFromFile = rf.read(file);
            if (textFromFile != null) {
                viewer.setTextOnTextArea(textFromFile);
                fileName = file.getName();
                viewer.setFrameTitle(fileName);
            } else {
                throw new NullPointerException("File is not chosen to open!");
            }

        } else if (command.equals("New Document")) {

            String text = viewer.getTextFromTextArea();
            int result = -2;

            if (!text.equals("")) {
                result = showFileSaveOpenDialog();
            } else {
                viewer.setTextOnTextArea("");
                viewer.setFrameTitle("*Untitled*");
            }
            if (result == 0) {
                saveDocument();
            } else if (result == 1) {
                viewer.setTextOnTextArea("");
                viewer.setFrameTitle("*Untitled*");
            }

        } else if (command.equals("Save Document")) {
            saveDocument();

        } else if (command.equals("Save As Document")) {
            saveDocument();

        } else if (command.equals("Word Wrap")) {

            viewer.changeTextAreaLineWrapStatus();
            viewer.changeGoToLineStatus();

        } else if (command.equals("Cut Item")) {
            viewer.cutText();

        } else if (command.equals("Copy Item")) {
            viewer.copyText();

        } else if (command.equals("Paste Item")) {
            viewer.pasteText();

        } else if (command.equals("Clear")) {
            viewer.setTextOnTextArea("");

        } else if (command.equals("Marker All")) {
            viewer.selectAllText();

        } else if (command.equals("Find Value")) {
            findText(false);

        } else if (command.equals("Find More")) {
            findText(true);

        } else if (command.equals("Time and Date")) {

            String date;
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.ENGLISH);
            date = formatter.format(new Date());
            int cursorPosition = viewer.getTextArea().getCaretPosition();

            viewer.pasteDateAndTime(date, cursorPosition);

        } else if (command.equals("Status Panel")) {
            statusPanelFlag = !statusPanelFlag;
            viewer.setStatusPanel(statusPanelFlag);

        } else if (command.equals("Zoom In")) {
            if (scalePercent < 230) {
                scalePercent = scalePercent + 20;
                viewer.updateFontSize(scalePercent);
            }

        } else if (command.equals("Zoom Out")) {
            if (scalePercent > 40) {
                scalePercent = scalePercent - 20;
                viewer.updateFontSize(scalePercent);
            }

        } else if (command.equals("Zoom Restore")) {
            scalePercent = 100;
            viewer.updateFontSize(scalePercent);

        } else if (command.equals("About")) {
            viewer.aboutDialogBox();
//            JFrame aboutFrame = new JFrame();
//            aboutFrame.setSize(260, 130);
//            aboutFrame.setTitle("About");
//            aboutFrame.setVisible(true);
//            JLabel aboutLabel = new JLabel();
//            aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
//            aboutLabel.setText("Notepad MVC by Team 3, 2021 (c)");
//            aboutFrame.add(aboutLabel);
//            aboutFrame.setLocation(500, 200);

        } else if (command.equals("View Help")) {
            viewer.userGuideFrame();
//            helpFrame.setSize(260, 130);
//            helpFrame.setTitle("Help");
//            helpFrame.setVisible(true);
//            JLabel aboutLabel = new JLabel();
//            aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);
//            aboutLabel.setText("Help will arrive soon :)");
//            helpFrame.add(aboutLabel);
//            helpFrame.setLocation(500, 200);

        } else if (command.equals("Exit Program")) {
            String text = viewer.getTextFromTextArea();
            int result = -2;
            if (!text.equals("")) {
                result = showFileSaveOpenDialog();
            }
            if (result == 0) {
                saveDocument();
            }
            viewer.exitProgram();

        } else if (command.equals("Print Document")) {
            String text = viewer.getTextFromTextArea();

            if (text.length() == 0) {
                JOptionPane.showMessageDialog(null,
                        "Text area is empty. Nothing to print!",
                        "Empty text",
                        JOptionPane.PLAIN_MESSAGE);

            } else {
                Font font = viewer.getFont();
                char[] charText = text.toCharArray();

                PrintDocument printDocument = new PrintDocument(charText, font);

                PrinterJob job = PrinterJob.getPrinterJob();

                PageFormat pageFormat = job.defaultPage();
                pageFormat = job.pageDialog(pageFormat);
                job.setPrintable(printDocument, pageFormat);

                boolean ok = job.printDialog();

                if (ok) {
                    try {
                        job.print();
                        JOptionPane.showMessageDialog(null,
                                "The printer job is done!",
                                "Completed",
                                JOptionPane.PLAIN_MESSAGE);
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        } else if (command.equals("Font Set")) {
            FontDialog fd = new FontDialog();
            Font font = fd.showDialog(viewer.getFont());
            viewer.setSelectedFont(font, scalePercent);
        } else if (command.equals("Go To Line")) {

            goToLineNumber();

        }
    }


    private void goToLineNumber() {

        GoToLine goToLine = new GoToLine();

        int lineIndex = goToLine.getLineNumber(viewer.getTextFromTextArea());

        if (lineIndex != -1) {
            viewer.updateCursorPosition(lineIndex);
        }
    }

    private void saveDocument() {

        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showSaveDialog(new JFrame());
        File file = fileChooser.getSelectedFile();
        SaveToFile sf = new SaveToFile();
        String textForFile = viewer.getTextFromTextArea();

        boolean isDone = sf.write(textForFile, file);
        System.out.println("The file has been saved successfully: " + isDone);
        fileName = file.getName();
        viewer.setFrameTitle(fileName);

    }

    private int showFileSaveOpenDialog() {

        Object[] options = {"Save", "Don't save", "Cancel"};

        return JOptionPane.showOptionDialog(new JFrame(),
                "Do you want to save Untitled?",
                "Notepad MVC",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("icons/save.png"),
                options,
                options[2]);
    }

    void findText(boolean isFindMore) {

        SearchText searchText = new SearchText();

        char[] textFromTextArea = viewer.getTextFromTextArea().toCharArray();
        int cursorPosition = viewer.getTextArea().getCaretPosition();
        int[] val = searchText.findEnteredValue(textFromTextArea, defaultValueOfSearchBar, cursorPosition, isFindMore);

        defaultValueOfSearchBar = searchText.getDefaultSearchValue();

        if (val[0] != -1) {

            viewer.findEnteredValue(val[0], val[1]);

        }
    }

}