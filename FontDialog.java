import javax.swing.*;
import java.awt.*;

public class FontDialog extends JDialog {

    private final FontChooser chooser;
    private final JButton cancelButton;
    private final JButton okButton;
    private boolean cancelSelected;

    public Font showDialog(Font selectedFont) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSelectedFont(selectedFont);
        setVisible(true);
        if (isCancelSelected()) {
            return getSelectedFont();
        }
        return selectedFont;
    }

    public FontDialog() {
        super(new Frame(), "Select font", true);
        chooser = new FontChooser();
        cancelButton = new JButton();
        okButton = new JButton();
        initDialog();
    }

    private void initDialog() {
        JPanel chooserPanel = new JPanel();
        chooserPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 11));
        chooserPanel.setLayout(new BorderLayout(0, 12));
        chooserPanel.add(chooser);
        add(chooserPanel);

        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createEmptyBorder(7, 7, 6, 6));
        controlPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        add(controlPanel, BorderLayout.PAGE_END);

        okButton.setText("OK");
        okButton.addActionListener(event -> dispose());
        controlPanel.add(okButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(event -> {
            cancelSelected = true;
            dispose();
        });
        controlPanel.add(cancelButton);
        pack();
        getRootPane().setDefaultButton(okButton);
    }

    Font getSelectedFont() {
        return chooser.getSelectedFont();
    }

    private void setSelectedFont(Font font) {
        chooser.setSelectedFont(font);
    }

    boolean isCancelSelected() {
        return !cancelSelected;
    }
}


