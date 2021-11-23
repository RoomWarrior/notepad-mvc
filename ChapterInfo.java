import java.net.URL;

public class ChapterInfo {

    private final String chapter;
    private final URL chapterURL;

    public ChapterInfo(String chapter, String filename) {
        this.chapter = chapter;
        chapterURL = getClass().getResource(filename);
        if (chapterURL == null) {
            System.err.println("Couldn't find file: " + filename);
        }
    }

    public String toString() {
        return chapter;
    }

    public URL getChapterURL() {
        return chapterURL;
    }

}
