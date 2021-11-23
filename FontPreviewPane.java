import javax.swing.*;
import java.awt.*;

class FontPreviewPane extends JScrollPane {

    private JTextArea previewText;

    FontPreviewPane() {
        previewText = new JTextArea();
        previewText.setText("AaBbCcDdEeYyZz");
        previewText.setBorder(BorderFactory.createCompoundBorder(previewText.getBorder(), BorderFactory.createEmptyBorder(6, 6, 6, 6)));
        setPreferredSize(new Dimension(200, 80));
        setViewportView(previewText);
    }

    void setPreviewFont(Font font) {
        previewText.setFont(font);
    }

}
