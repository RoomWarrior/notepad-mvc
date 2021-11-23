import java.awt.*;
import java.awt.print.*;
import java.util.HashMap;

public class PrintDocument implements Printable {

    private int textLength;
    private int currentSymbolIndex;
    private int currentPage;
    private Font font;
    private int spaceIndex;
    private int spaceIndexInLine;
    private HashMap<Integer, Integer> pageMap;
    private char[] text;

    public PrintDocument(char[] text, Font font) {
        this.text = text;
        textLength = text.length;
        this.font = font;
        currentSymbolIndex = 0;
        currentPage = -1;
        pageMap = new HashMap<>();
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {

        if (currentPage == pageIndex) {
            currentSymbolIndex = (pageMap.get(pageIndex));
            currentPage--;
        } else {
            pageMap.put(pageIndex, currentSymbolIndex);
        }
        if (currentSymbolIndex < 0) {
            text = null;
            return NO_SUCH_PAGE;
        }

        FontMetrics metrics = graphics.getFontMetrics(font);
        double pageWidth = (pageFormat.getImageableWidth());
        int lineHeight = metrics.getHeight();
        int linesPerPage = (int) ((pageFormat.getImageableHeight()) / lineHeight);

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        graphics.setFont(font);
        graphics.drawRect(0, 0, (int) pageWidth, (int) pageFormat.getImageableHeight());

        int totalSymbolsOnLine = 0;
        int indent = 0;
        String lineToPrint = "";

        for (; currentSymbolIndex < textLength; currentSymbolIndex++) {

            totalSymbolsOnLine++;

            if (text[currentSymbolIndex] == '\t') {
                lineToPrint = lineToPrint + "    ";
                spaceIndex = currentSymbolIndex;
                spaceIndexInLine = totalSymbolsOnLine;
            } else {
                if (text[currentSymbolIndex] == ' ' || text[currentSymbolIndex] == '-') {
                    spaceIndex = currentSymbolIndex;
                    spaceIndexInLine = totalSymbolsOnLine;
                }

                lineToPrint = lineToPrint + text[currentSymbolIndex];
            }

            int stringWidth = metrics.stringWidth(lineToPrint);

            if (text[currentSymbolIndex] == '\n' || stringWidth >= pageWidth) {
                if (!(currentSymbolIndex == ' ' || currentSymbolIndex + 1 == ' '
                        || currentSymbolIndex == '-' || currentSymbolIndex + 1 == '-'
                        || currentSymbolIndex == '\t' || currentSymbolIndex + 1 == '\t')
                        && spaceIndexInLine >= 0 && text[currentSymbolIndex] != '\n') {

                    lineToPrint = lineToPrint.substring(0, spaceIndexInLine);
                    currentSymbolIndex = spaceIndex;
                }

                stringWidth = metrics.stringWidth(lineToPrint);

                if (stringWidth > pageWidth) {
                    lineToPrint = lineToPrint.substring(0, lineToPrint.length() - 1);
                    currentSymbolIndex--;
                }

                indent = indent + lineHeight;
                graphics.drawString(lineToPrint, 0, indent);
                spaceIndexInLine = -1;
                totalSymbolsOnLine = 0;
                lineToPrint = "";
                linesPerPage--;

                if (linesPerPage == 0) {
                    currentSymbolIndex++;
                    currentPage++;
                    return PAGE_EXISTS;
                }
            }

            if (currentSymbolIndex >= textLength - 1) {
                currentSymbolIndex = -1;
                currentPage++;
                indent = indent + lineHeight;
                graphics.drawString(lineToPrint, 0, indent);
                return PAGE_EXISTS;
            }
        }

        currentSymbolIndex++;
        currentPage++;
        return PAGE_EXISTS;
    }

}