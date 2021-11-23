import javax.swing.*;

public class SearchText {

    private String defaultSearchValue;

    int[] findEnteredValue(char[] text, String defaultValue, int cursorPosition, boolean isFindMore) {

        String searchValue;

        if (!isFindMore || defaultValue.length() == 0) {
            searchValue = JOptionPane.showInputDialog("Find:", defaultValue);
            defaultSearchValue = searchValue;

        } else {
            defaultSearchValue = defaultValue;
            searchValue = defaultValue;

        }


        if (searchValue == null) {
            throw new NullPointerException("Nothing entered!");

        } else if (searchValue.equals("")) {

            JOptionPane.showMessageDialog(null, "Empty value. Please type something!");
            return new int[]{-1, -1};

        }

        else if (searchValue.length() > text.length) {
            JOptionPane.showMessageDialog(null, "\"" + searchValue + "\"" + " not found. Value is longer than entire text");

        } else {
            int lastSymbolPosition = findText(text, searchValue.toCharArray(), cursorPosition);

            if (lastSymbolPosition == -1) {
                JOptionPane.showMessageDialog(null, "\"" + searchValue + "\"" + " not found");

            } else {
                return new int[]{(lastSymbolPosition - searchValue.length()), lastSymbolPosition};
            }

        }

        return new int[]{-1, -1};
    }

    private int findText(char[] text, char[] value, int cursorPosition) {
        int textLength, valueLength, len = 0;

        textLength = text.length;
        valueLength = value.length;

        for (int i = cursorPosition; i < textLength; i++) {
            if (text[i] == value[len]) {
                len++;
            } else {
                len = 0;
            }
            if (len == valueLength) {
                return i + 1;
            }
        }
        return -1;
    }

    String getDefaultSearchValue() {
        return defaultSearchValue;
    }
}

