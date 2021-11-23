import java.awt.*;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class FontFamily implements Iterable<Font> {

    private String name;
    private Set<Font> styles;

    FontFamily(String name) {
        styles = new TreeSet<>(new FontNameComparator());
        this.name = name;
    }

    void add(Font font) {
        styles.add(font);
    }

    String getName() {
        return name;
    }

    @Override
    public Iterator<Font> iterator() {
        return styles.iterator();
    }
}
