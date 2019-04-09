package main.fileUtils;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FilenameUtils;

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
        assert fileInputStream != null;
//            input = new ArchiveStreamFactory()
//                    .createArchiveInputStream(fileInputStream);
        input = getArchiveInputStream(fileInputStream);

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
                    String name = pathToUnzip.getPath() + System.getProperty("file.separator") + entry.getName();
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

    private ArchiveInputStream getArchiveInputStream(InputStream fileInputStream) {
        switch (FilenameUtils.getExtension(fileToUnzip.getName())) {
            case "gz":
                try {
                    return new TarArchiveInputStream(new GzipCompressorInputStream(fileInputStream));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "zip":
                return new ZipArchiveInputStream(fileInputStream);
                default:return null;
        }

    }
}
