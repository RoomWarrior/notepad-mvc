import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

class FontFamilyListSelectionListener implements ListSelectionListener {

    private FontContainer fontContainer;

    FontFamilyListSelectionListener(FontContainer fontContainer) {
        this.fontContainer = fontContainer;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            Font oldFont = fontContainer.getSelectedFont();
            Font newFont = new Font(
                    fontContainer.getSelectedFamily(),
                    oldFont.getStyle(),
                    (int) fontContainer.getSelectedSize());

            fontContainer.setSelectedFont(newFont);
            fontContainer.setPreviewFont(newFont);
        }
    }
}
