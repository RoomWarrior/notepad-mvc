import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

class FontFamilyPane extends JPanel {

    private JList<String> familyList;
    private FontSearchListener fontSearchListener;

    FontFamilyPane() {
        familyList = new JList<>();

        DefaultListModel<String> familyListModel = new DefaultListModel<>();
        FontFamilies fontFamilies = new FontFamilies();
        fontFamilies = fontFamilies.getFontFamilies();
        fontSearchListener = new FontSearchListener(this);
        for (FontFamily fontFamily : fontFamilies) {
            String name = fontFamily.getName();
            familyListModel.addElement(name);
            fontSearchListener.addFamilyName(name);
        }

        initializeList(familyListModel);

        setPreferredSize(new Dimension(240, 100));

        setLayout(new GridBagLayout());
        addSearchField();
        addScrollPane();
    }

    private void initializeList(ListModel<String> familyListModel) {
        familyList.setModel(familyListModel);
        familyList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void addSearchField() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.weightx = 1.0;

        JTextField searchField = new JTextField();
        searchField.setBorder(BorderFactory.createEmptyBorder());
        searchField.requestFocus();
        searchField.addKeyListener(fontSearchListener);
        add(new JScrollPane(searchField), gridBagConstraints);
    }

    private void addScrollPane() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;

        JScrollPane scrollPane = new JScrollPane(familyList);
        add(scrollPane, gridBagConstraints);
    }

    void setSelectedFamily(String family) {
        familyList.setSelectedValue(family, true);
    }

    void addListSelectionListener(ListSelectionListener listener) {
        familyList.addListSelectionListener(listener);
    }

    void removeListSelectionListener(ListSelectionListener listener) {
        familyList.removeListSelectionListener(listener);
    }

    String getSelectedFamily() {
        return familyList.getSelectedValue();
    }

}
