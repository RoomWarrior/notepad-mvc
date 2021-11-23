import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class SaveToFile {

    public boolean write(String text, File file) {
        try {
            if (file != null) {
                FileWriter fileWriter = new FileWriter(file);
                PrintWriter outputStream = new PrintWriter(fileWriter);
                outputStream.println(text);
                outputStream.close();
                fileWriter.close();
                return true;
            } else {
                throw new NullPointerException("No file chosen to save!");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
            return false;
        }
    }
}
