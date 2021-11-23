import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.TreeSet;

class FontSearchListener extends KeyAdapter {

    private Collection<String> fontFamilyNames;
    private FontFamilyPane fontFamilyPane;

    FontSearchListener(FontFamilyPane fontFamilyPane) {
        fontFamilyNames = new TreeSet<>();
        this.fontFamilyPane = fontFamilyPane;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        JTextField searchField = (JTextField) e.getSource();
        String searchString = searchField.getText().toLowerCase(Locale.ENGLISH);
        Optional<String> first = fontFamilyNames.stream()
                .filter(family -> family.toLowerCase(Locale.ENGLISH).contains(searchString))
                .findFirst();
        first.ifPresent(fontFamilyPane::setSelectedFamily);
    }

    void addFamilyName(String name) {
        fontFamilyNames.add(name);
    }
}
