import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Viewer {

    private JFrame frame;
    private JTextArea textArea;
    private JCheckBoxMenuItem wordWrap;
    private JPanel statusPanel;
    private JMenuItem goToLine;
    private String scaleOfFont;
    private JLabel scaleLabel;
    private Font currentFont;

    public Viewer() {
        Controller controller = new Controller(this);

        textArea = new JTextArea();
        textArea.setTabSize(2);
        currentFont = new Font("Arial", Font.PLAIN, 18);
        textArea.setFont(currentFont);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JMenuItem createNewDocument = new JMenuItem("New", new ImageIcon("icons/new.png"));
        createNewDocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        createNewDocument.addActionListener(controller);
        createNewDocument.setActionCommand("New Document");

        JMenuItem openDocument = new JMenuItem("Open ... ", new ImageIcon("icons/open.png"));
        openDocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openDocument.addActionListener(controller);
        openDocument.setActionCommand("Open Document");

        JMenuItem saveDocument = new JMenuItem("Save", new ImageIcon("icons/save.png"));
        saveDocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveDocument.addActionListener(controller);
        saveDocument.setActionCommand("Save Document");

        JMenuItem saveAsDocument = new JMenuItem("Save as...", new ImageIcon("icons/save_as.png"));
        saveAsDocument.addActionListener(controller);
        saveAsDocument.setActionCommand("Save As Document");

        JMenuItem printDocument = new JMenuItem("Print...", new ImageIcon("icons/print.png"));
        printDocument.addActionListener(controller);
        printDocument.setActionCommand("Print Document");

        JMenuItem exitProgram = new JMenuItem("Exit");
        exitProgram.addActionListener(controller);
        exitProgram.setActionCommand("Exit Program");

        JMenu menuFile = new JMenu("File");
        menuFile.add(createNewDocument);
        menuFile.add(openDocument);
        menuFile.add(saveDocument);
        menuFile.add(saveAsDocument);
        menuFile.addSeparator();
        menuFile.add(printDocument);
        menuFile.addSeparator();
        menuFile.add(exitProgram);

        JMenuItem cutItem = new JMenuItem("Cut", new ImageIcon("icons/cut.png"));
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cutItem.addActionListener(controller);
        cutItem.setActionCommand("Cut Item");

        JMenuItem copyItem = new JMenuItem("Copy", new ImageIcon("icons/copy.png"));
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copyItem.addActionListener(controller);
        copyItem.setActionCommand("Copy Item");

        JMenuItem pasteItem = new JMenuItem("Paste", new ImageIcon("icons/paste.png"));
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        pasteItem.addActionListener(controller);
        pasteItem.setActionCommand("Paste Item");

        JMenuItem clearItem = new JMenuItem("Clear", new ImageIcon("icons/delete.png"));
        clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        clearItem.addActionListener(controller);
        clearItem.setActionCommand("Clear");

        JMenuItem findItem = new JMenuItem("Find", new ImageIcon("icons/find.png"));
        findItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        findItem.addActionListener(controller);
        findItem.setActionCommand("Find Value");

        JMenuItem findMoreItem = new JMenuItem("Find more...", new ImageIcon("icons/findMore.png"));
        findMoreItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.CTRL_MASK));
        findMoreItem.addActionListener(controller);
        findMoreItem.setActionCommand("Find More");

        goToLine = new JMenuItem("Go to line", new ImageIcon("icons/goTo.png"));
        goToLine.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        goToLine.addActionListener(controller);
        goToLine.setActionCommand("Go To Line");

        JMenuItem markerAll = new JMenuItem("Marker all", new ImageIcon("icons/selectAll.png"));
        markerAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        markerAll.addActionListener(controller);
        markerAll.setActionCommand("Marker All");

        JMenuItem timeAndDate = new JMenuItem("Time and date", new ImageIcon("icons/timeAndDate.png"));
        timeAndDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        timeAndDate.addActionListener(controller);
        timeAndDate.setActionCommand("Time and Date");

        JMenu menuEdit = new JMenu("Edit");
        menuEdit.add(cutItem);
        menuEdit.add(copyItem);
        menuEdit.add(pasteItem);
        menuEdit.add(clearItem);
        menuEdit.addSeparator();
        menuEdit.add(findItem);
        menuEdit.add(findMoreItem);
        menuEdit.add(goToLine);
        menuEdit.addSeparator();
        menuEdit.add(markerAll);
        menuEdit.add(timeAndDate);

        wordWrap = new JCheckBoxMenuItem("Word wrap", new ImageIcon("icons/wordWrap.png"), false);
        wordWrap.addActionListener(controller);
        wordWrap.setActionCommand("Word Wrap");
        textArea.setWrapStyleWord(false);
        changeTextAreaLineWrapStatus();

        JMenuItem fontSet = new JMenuItem("Font", new ImageIcon("icons/fonts.png"));
        fontSet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        fontSet.addActionListener(controller);
        fontSet.setActionCommand("Font Set");

        JMenu menuFormat = new JMenu("Format");
        menuFormat.add(wordWrap);
        menuFormat.add(fontSet);

        JCheckBoxMenuItem statusSpace = new JCheckBoxMenuItem("Status panel", new ImageIcon("icons/status.png"));
        statusSpace.addActionListener(controller);
        statusSpace.setActionCommand("Status Panel");
        statusSpace.setSelected(true);


        JMenu menuView = new JMenu("View");
        menuView.add(statusSpace);

        JMenu zoom = new JMenu("Zoom");
        menuView.add(zoom);
        JMenuItem zoomIn = new JMenuItem("Zoom +");
        zoomIn.addActionListener(controller);
        zoomIn.setActionCommand("Zoom In");
        zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, ActionEvent.CTRL_MASK));

        JMenuItem zoomOut = new JMenuItem("Zoom -");
        zoomOut.addActionListener(controller);
        zoomOut.setActionCommand("Zoom Out");
        zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, ActionEvent.CTRL_MASK));
        zoom.add(zoomIn);
        zoom.add(zoomOut);

        JMenuItem restoreZoom = new JMenuItem("Restore");
        restoreZoom.addActionListener(controller);
        restoreZoom.setActionCommand("Zoom Restore");
        restoreZoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        zoom.add(restoreZoom);

        JMenuItem viewHelp = new JMenuItem("View Help");
        viewHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        viewHelp.addActionListener(controller);
        viewHelp.setActionCommand("View Help");

        JMenuItem aboutInfo = new JMenuItem("About");
        aboutInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        aboutInfo.addActionListener(controller);
        aboutInfo.setActionCommand("About");

        JMenu menuHelp = new JMenu("Help");
        menuHelp.add(viewHelp);
        menuHelp.add(aboutInfo);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFormat);
        menuBar.add(menuView);
        menuBar.add(menuHelp);


        JButton buttonNewDocument = new JButton(new ImageIcon("icons/new.png"));
        buttonNewDocument.addActionListener(controller);
        buttonNewDocument.setActionCommand("New Document");

        JButton buttonOpenDocument = new JButton(new ImageIcon("icons/open.png"));
        buttonOpenDocument.addActionListener(controller);
        buttonOpenDocument.setActionCommand("Open Document");

        JButton buttonSaveDocument = new JButton(new ImageIcon("icons/save.png"));
        buttonSaveDocument.addActionListener(controller);
        buttonSaveDocument.setActionCommand("Save Document");

        JButton buttonCutDocument = new JButton(new ImageIcon("icons/cut.png"));
        buttonCutDocument.addActionListener(controller);
        buttonCutDocument.setActionCommand("Cut Item");

        JButton buttonCopyDocument = new JButton(new ImageIcon("icons/copy.png"));
        buttonCopyDocument.addActionListener(controller);
        buttonCopyDocument.setActionCommand("Copy Item");

        JButton buttonPasteDocument = new JButton(new ImageIcon("icons/paste.png"));
        buttonPasteDocument.addActionListener(controller);
        buttonPasteDocument.setActionCommand("Paste Item");

        JButton buttonPrintDocument = new JButton(new ImageIcon("icons/print.png"));
        buttonPrintDocument.addActionListener(controller);
        buttonPrintDocument.setActionCommand("Print Document");

        JToolBar toolBar = new JToolBar();
        toolBar.add(buttonNewDocument);
        toolBar.add(buttonOpenDocument);
        toolBar.add(buttonSaveDocument);
        toolBar.addSeparator();
        toolBar.add(buttonCutDocument);
        toolBar.add(buttonCopyDocument);
        toolBar.add(buttonPasteDocument);
        toolBar.add(buttonPrintDocument);

        frame = new JFrame("Notepad MVC");
        frame.setSize(700, 550);
        frame.setLocation(350, 100);
        frame.add("Center", scrollPane);
        frame.add("North", toolBar);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Image icon = new ImageIcon("icons/icon.png").getImage();
        frame.setIconImage(icon);

        statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        frame.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
        JLabel statusLabel = new JLabel("     Ln 1 , Col 1 ", JLabel.LEFT);
        JLabel encoding = new JLabel("|       UTF-8");
        scaleOfFont = "|       100%       ";
        scaleLabel = new JLabel(scaleOfFont);
        statusPanel.add(statusLabel);
        statusPanel.add(scaleLabel);
        statusPanel.add(encoding);
        statusPanel.setVisible(true);
        updateCursorPoint(statusLabel);

    }


    public void setStatusPanel(boolean visible) {
        statusPanel.setVisible(visible);
    }

    public String getTextFromTextArea() {
        return textArea.getText();
    }

    public void setTextOnTextArea(String value) {
        textArea.setText(value);
    }

    public void changeTextAreaLineWrapStatus() {
        textArea.setLineWrap(wordWrap.isSelected());
        textArea.setWrapStyleWord(true);
    }

    public void cutText() {
        textArea.cut();
    }

    public void copyText() {
        textArea.copy();
    }

    public void pasteText() {
        textArea.paste();
    }

    public void selectAllText() {
        textArea.selectAll();
    }

    public void setFrameTitle(String frameTitle) {
        this.frame.setTitle(frameTitle);
    }

    public Font getFont() {
        return currentFont;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void changeGoToLineStatus() {
        goToLine.setEnabled(!textArea.getLineWrap());
    }

    public void updateCursorPosition(int index) {

        textArea.setCaretPosition(index);
        textArea.requestFocus();

    }

    public void pasteDateAndTime(String date, int cursorPosition) {

        textArea.insert(date, cursorPosition);
    }


    public void setSelectedFont(Font font, int scale) {

        currentFont = font;
        int fontScale = (currentFont.getSize() * (scale - 100)) / 100;
        textArea.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), currentFont.getSize() + fontScale));
    }


    public void updateFontSize(int fontScalePercent) {

        int fontScale = (currentFont.getSize() * (fontScalePercent - 100)) / 100;
        textArea.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), currentFont.getSize() + fontScale));
        scaleOfFont = "|       " + fontScalePercent + "%       ";
        scaleLabel.setText(scaleOfFont);
    }


    public void findEnteredValue(int firstIndex, int lastIndex) {

        textArea.select(firstIndex, lastIndex);
        textArea.requestFocus();
    }

    public void updateCursorPoint(JLabel label) {
        getTextArea().addCaretListener
                (new CaretListener() {
                     public void caretUpdate(CaretEvent e) {
                         int linePositionNum = 1;
                         int columnPosition = 1;
                         int position;
                         try {
                             position = getTextArea().getCaretPosition();
                             linePositionNum = getTextArea().getLineOfOffset(position);
                             columnPosition = position - getTextArea().getLineStartOffset(linePositionNum);
                             linePositionNum = linePositionNum + 1;
                             columnPosition = columnPosition + 1;
                         } catch (Exception excp) {
                             excp.printStackTrace();
                         }
                         label.setText("     Ln " + linePositionNum + " ," + " Col " + columnPosition + "    ");
                     }
                 }
                );
    }

    public void aboutDialogBox() {
        AboutDialog aboutDialog = new AboutDialog(frame, "About the Notepad MVC Pattern");
        aboutDialog.createShowGUI();
    }

    public void userGuideFrame() {
        UserGuide userGuide = new UserGuide();
        userGuide.createShowGUI();
    }

    public void exitProgram() {
        System.exit(0);
    }

}