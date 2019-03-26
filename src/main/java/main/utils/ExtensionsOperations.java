package main.utils;

public class ExtensionsOperations {
    public static String removeExtension(String filename) {
        if (filename == null) {
            return null;
        }

        int index = indexOfExtension(filename);

        if (index == -1) {
            return filename;
        } else {
            return filename.substring(0, index);
        }
    }

    private static int indexOfExtension(String filename) {

        if (filename == null) {
            return -1;
        }
        int extensionPos = filename.lastIndexOf(".");

        int lastDirSeparator = filename.lastIndexOf(System.getProperty("file.separator"));

        if (lastDirSeparator > extensionPos) {
            return -1;
        }

        return extensionPos;
    }
}
