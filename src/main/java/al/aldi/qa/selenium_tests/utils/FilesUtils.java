package al.aldi.qa.selenium_tests.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FilesUtils {
    public static String readFile(String path) {
        String result = null;
        File fileToRead = new File(path);
        try {
            result = FileUtils.readFileToString(fileToRead, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void writeToFile(String path, String content) {
        try {
            FileUtils.write(new File(path), content, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
