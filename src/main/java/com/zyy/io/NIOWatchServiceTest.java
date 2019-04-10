package com.zyy.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.junit.Test;

public class NIOWatchServiceTest {

    @Test
    public void canConstructAPersonWithAName() {
        try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path dir = new File("c:/files").toPath();
            System.out.println(Files.probeContentType(dir));
            WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

            while (true) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    System.out.println(kind.name());
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }
                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path filename = ev.context();

                    // Verify that the new
                    // file is a text file.
                    try {
                        // Resolve the filename against the directory.
                        // If the filename is "test" and the directory is "foo",
                        // the resolved name is "test/foo".
                        Path child = dir.resolve(filename);
                        System.out.println(child);
                        String probeContentType = Files.probeContentType(child);
                        System.out.println(probeContentType);
                        if (probeContentType == null || !probeContentType.equals("text/plain")) {
                            System.err.format("New file '%s'" + " is not a plain text file.\n", filename);
                            continue;
                        }
                    } catch (IOException x) {
                        System.err.println(x);
                        continue;
                    }

                    // Email the file to the
                    // specified email alias.
                    System.out.format("Emailing file %s\n", filename);
                    // Details left to reader....
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
