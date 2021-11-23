import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Objects;

class FontStylePane extends JScrollPane implements ChangeListener {

    private JList<String> styleList;
    private DefaultListModel<String> styleListModel;
    private String family;

    FontStylePane() {

        styleListModel = new DefaultListModel<>();
        styleList = new JList<>();
        styleList.setModel(styleListModel);
        styleList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setPreferredSize(new Dimension(160, 100));
        setViewportView(styleList);

    }

    void addListSelectionListener(ListSelectionListener listener) {
        styleList.addListSelectionListener(listener);
    }

    void removeListSelectionListener(ListSelectionListener listener) {
        styleList.removeListSelectionListener(listener);
    }

    void setSelectedStyle(String name) {
        styleList.setSelectedValue(name, true);
    }

    String getSelectedStyle() {
        return styleList.getSelectedValue();
    }

    @Override
    public void stateChanged(ChangeEvent e) {


    }

    void loadFamily(String family) {
        if (Objects.equals(this.family, family)) {
            return;
        }

        this.family = family;

        FontFamilies fontFamilies = new FontFamilies();
        fontFamilies = fontFamilies.getFontFamilies();
        FontFamily fontFamily = fontFamilies.get(family);

        if (fontFamily != null) {
            ListSelectionListener[] selectionListeners = styleList.getListSelectionListeners();
            removeSelectionListeners(selectionListeners);
            updateListModel(fontFamily);
            addSelectionListeners(selectionListeners);
        }
    }

    private void updateListModel(Iterable<Font> fonts) {
        styleListModel.clear();

        for (Font font : fonts) {
            styleListModel.addElement(font.getName());
        }
    }

    private void addSelectionListeners(ListSelectionListener[] selectionListeners) {
        for (ListSelectionListener listener : selectionListeners) {
            styleList.addListSelectionListener(listener);
        }
    }

    private void removeSelectionListeners(ListSelectionListener[] selectionListeners) {
        for (ListSelectionListener listener : selectionListeners) {
            styleList.removeListSelectionListener(listener);
        }
    }
}
