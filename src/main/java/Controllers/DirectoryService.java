package Controllers;

import java.io.File;

public class DirectoryService {

    private static File[] files;

    public static File getFile(String dirPath) {

        files = new File(dirPath).listFiles();

        for (File file : files) {
            if (!file.isDirectory() && file != null) {
                return file;
            }
        }

        return null;
    }

}
