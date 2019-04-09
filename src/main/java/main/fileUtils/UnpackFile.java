package main.fileUtils;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import java.io.*;
import java.nio.file.Files;

public class UnpackFile {
    private final File fileToUnzip;
    private final File pathToUnzip;

    public UnpackFile(File fileToUnzip, File pathToUnzip) {
        this.fileToUnzip = fileToUnzip;
        this.pathToUnzip = pathToUnzip;
    }

    private ArchiveInputStream initDecompressStream() {
        BufferedInputStream fileInputStream = null;
        try {
            fileInputStream = new BufferedInputStream(Files.newInputStream(fileToUnzip.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArchiveInputStream input = null;
        try {
            assert fileInputStream != null;
            input = new ArchiveStreamFactory()
                    .createArchiveInputStream(fileInputStream);
        } catch (ArchiveException e) {
            e.printStackTrace();
        }
        return input;
    }

    public void decompress() {
        try {
            try (ArchiveInputStream i = initDecompressStream()) {
                ArchiveEntry entry;
                while ((entry = i.getNextEntry()) != null) {
                    if (!i.canReadEntryData(entry)) {
                        // log something?
                        continue;
                    }
                    String name = pathToUnzip.getPath() +System.getProperty("file.separator")+ entry;
                    File f = new File(name);
                    if (entry.isDirectory()) {
                        if (!f.isDirectory() && !f.mkdirs()) {
                            throw new IOException("failed to create directory " + f);
                        }
                    } else {
                        File parent = f.getParentFile();
                        if (!parent.isDirectory() && !parent.mkdirs()) {
                            throw new IOException("failed to create directory " + parent);
                        }
                        try (OutputStream o = Files.newOutputStream(f.toPath())) {
                            IOUtils.copy(i, o);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
