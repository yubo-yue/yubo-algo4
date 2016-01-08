package jvm.nio;

import org.junit.Test;

import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by yubo on 12/16/15.
 */
public class PathTest {

    @Test
    public void testBasic() {
        Path path = Paths.get(System.getProperty("user.home"), "Downloads");
        assertNotNull(path);

        int cnt = path.getNameCount();
        assertEquals(3, cnt);

        for (Path p : path) {
            System.out.println(p);
        }
        System.out.println();
    }

    @Test
    public void testSupportFSView() {
        FileSystem fs = FileSystems.getDefault();

        Set<String> fsViews = fs.supportedFileAttributeViews();
        assertTrue(fsViews.size() > 0);

        fsViews.forEach(System.out::println);
        Iterable<FileStore> fileStores = fs.getFileStores();

        for (FileStore fileStore : fileStores) {
            System.out.println(String.format(
                    "FileStore %s supports (%s): %s",
                    fileStore,
                    BasicFileAttributeView.class.getCanonicalName(),
                    fileStore.supportsFileAttributeView(BasicFileAttributeView.class))
            );

            // Test if supports DosFileAttributeView
            System.out.println(String.format(
                    "Filestore %s supports (%s) : %s",
                    fileStore,
                    DosFileAttributeView.class.getSimpleName(),
                    fileStore.supportsFileAttributeView(DosFileAttributeView.class)));

            // Test if supports PosixFileAttributeView
            System.out.println(String.format(
                    "Filestore %s supports (%s) : %s",
                    fileStore,
                    PosixFileAttributeView.class.getSimpleName(),
                    fileStore.supportsFileAttributeView(PosixFileAttributeView.class)));

            // Test if supports AclFileAttributeView
            System.out.println(String.format(
                    "Filestore %s supports (%s) : %s",
                    fileStore,
                    AclFileAttributeView.class.getSimpleName(),
                    fileStore.supportsFileAttributeView(AclFileAttributeView.class)));

            // Test if supports FileOwnerAttributeView
            System.out.println(String.format(
                    "Filestore %s supports (%s) : %s",
                    fileStore,
                    FileOwnerAttributeView.class.getSimpleName(),
                    fileStore.supportsFileAttributeView(FileOwnerAttributeView.class)));

            System.out.println();
        }
    }
}
