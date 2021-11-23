import java.awt.*;
import java.util.Comparator;

class FontNameComparator implements Comparator<Font> {

    @Override
    public int compare(Font o1, Font o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
