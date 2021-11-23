import java.awt.*;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

class FontFamilies implements Iterable<FontFamily> {

    private Map<String, FontFamily> families;

    public FontFamilies() {
        families = new TreeMap<>();
    }

    public FontFamilies getFontFamilies() {
        FontFamilies fontFamilies = new FontFamilies();

        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] allFonts = graphicsEnvironment.getAllFonts();

        for (Font font : allFonts) {
            fontFamilies.add(new Font(font.getFontName(Locale.US), font.getStyle(), font.getSize()));
        }

        return fontFamilies;
    }

    private void add(Font font) {
        String family = font.getFamily();
        FontFamily fontFamily = families.computeIfAbsent(family, FontFamily::new);
        fontFamily.add(font);
    }

    @Override
    public Iterator<FontFamily> iterator() {
        return families.values().iterator();
    }

    FontFamily get(String name) {
        return families.get(name);
    }
}
