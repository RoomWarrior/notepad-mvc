import javax.swing.*;
import java.awt.*;

class FontChooser extends JPanel implements FontContainer {

    private JLabel previewLabel;
    private JPanel fontPanel;
    private JPanel previewPanel;
    private FontFamilyPane fontFamilyPane;
    private FontPreviewPane fontPreviewPane;
    private FontStylePane fontStylePane;
    private FontSizePane fontSizePane;
    private FontFamilyListSelectionListener familyPaneListener;
    private FontStyleListSelectionListener stylePaneListener;
    private FontSizeListSelectionListener sizePaneListener;
    private FontSelectionModel selectionModel;

    public FontChooser() {
        previewLabel = new JLabel();
        fontPanel = new JPanel();
        previewPanel = new JPanel();
        fontFamilyPane = new FontFamilyPane();
        fontPreviewPane = new FontPreviewPane();
        fontStylePane = new FontStylePane();
        fontSizePane = new FontSizePane();
        familyPaneListener = new FontFamilyListSelectionListener(this);
        stylePaneListener = new FontStyleListSelectionListener(this);
        sizePaneListener = new FontSizeListSelectionListener(this);
        selectionModel = new DefaultFontSelectionModel(new Font(Font.SERIF, Font.PLAIN, 12));
        setLayout(new BorderLayout());
        addComponents();
        initPanes();
        fontPreviewPane.setPreviewFont(selectionModel.getSelectedFont());
    }

    @Override
    public Font getSelectedFont() {
        return selectionModel.getSelectedFont();
    }

    @Override
    public void setSelectedFont(Font font) {
        fontFamilyPane.removeListSelectionListener(familyPaneListener);
        fontStylePane.removeListSelectionListener(stylePaneListener);
        fontSizePane.removeListSelectionListener(sizePaneListener);

        selectionModel.setSelectedFont(font);

        initPanes();
    }

    private void initPanes() {
        fontFamilyPane.setSelectedFamily(selectionModel.getSelectedFontFamily());
        fontFamilyPane.addListSelectionListener(familyPaneListener);

        fontStylePane.loadFamily(selectionModel.getSelectedFontFamily());
        fontStylePane.setSelectedStyle(selectionModel.getSelectedFontName());
        fontStylePane.addListSelectionListener(stylePaneListener);

        fontSizePane.addListSelectionListener(sizePaneListener);
        fontSizePane.setSelectedSize(selectionModel.getSelectedFontSize());
    }

    private void addComponents() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        // Adding FontPanel
        fontPanel.setLayout(new GridBagLayout());
        add(fontPanel);

        // Adding Family label
        JLabel familyLabel = new JLabel();
        familyLabel.setLabelFor(fontFamilyPane);
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        fontPanel.add(familyLabel, gridBagConstraints);
        familyLabel.setText("Font");

        // Adding Style label
        JLabel styleLabel = new JLabel();
        styleLabel.setLabelFor(fontStylePane);
        styleLabel.setText("Style");
        fontPanel.add(styleLabel, gridBagConstraints);

        // Adding Size label
        JLabel sizeLabel = new JLabel();
        sizeLabel.setLabelFor(fontSizePane);
        sizeLabel.setText("Size");
        fontPanel.add(sizeLabel, gridBagConstraints);


        // Adding Family pane
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 10, 10);
        fontPanel.add(fontFamilyPane, gridBagConstraints);


        // Adding Style pane
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new Insets(0, 0, 10, 10);
        fontPanel.add(fontStylePane, gridBagConstraints);


        // Adding Size pane
        fontPanel.add(fontSizePane, gridBagConstraints);


        // Adding Preview label();
        previewLabel.setText("Sample");

        // Adding Preview panel();
        previewPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        previewPanel.add(previewLabel, gridBagConstraints);
        add(previewPanel, BorderLayout.PAGE_END);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        previewPanel.add(fontPreviewPane, gridBagConstraints);

    }

    @Override
    public String getSelectedStyle() {
        return fontStylePane.getSelectedStyle();
    }

    @Override
    public float getSelectedSize() {
        return fontSizePane.getSelectedSize();
    }

    @Override
    public String getSelectedFamily() {
        return fontFamilyPane.getSelectedFamily();
    }

    @Override
    public void setPreviewFont(Font font) {
        fontPreviewPane.setPreviewFont(font);
    }
}
