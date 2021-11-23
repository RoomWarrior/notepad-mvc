import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFromFile {

    public String read(File file)  {
        try {
            if (file != null){
                if (!file.exists()){
                    throw new FileNotFoundException("File " + "\"" + file.getName() + "\"" + " doesn't exists!");
                }
                FileInputStream in = new FileInputStream(file);
                int sizeOfFile = in.available();
                int[] arraySymbols = new int[sizeOfFile];
                int index = 0;
                int c;
                while ((c = in.read()) != -1) {
                    arraySymbols[index] = c;
                    index = index + 1;
                }

                String textFromFile = new String(arraySymbols, 0, arraySymbols.length);
                arraySymbols = null;
                in.close();
                return textFromFile;
            }
            return null;

        } catch(IOException ioe)  {
            ioe.printStackTrace();
            return null;
        }

    }
}
