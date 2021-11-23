import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

class FontStyleListSelectionListener implements ListSelectionListener {

    private FontContainer fontContainer;

    FontStyleListSelectionListener(FontContainer fontContainer) {
        this.fontContainer = fontContainer;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String selectedStyle = fontContainer.getSelectedStyle();
            Font oldFont = fontContainer.getSelectedFont();
            Font newFont = new Font(selectedStyle, oldFont.getStyle(), oldFont.getSize());
            fontContainer.setSelectedFont(newFont);
            fontContainer.setPreviewFont(newFont);
        }
    }

}
