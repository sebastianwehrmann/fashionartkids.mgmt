package fashionartkids.mgmt.repository;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Repository
public class FileSystemRepository {

    private final String RESOURCES_DIR = FileSystemRepository.class.getResource("/").getPath();

    public void save(byte[] content, String filename) throws Exception {
        Path newFile = Paths.get(RESOURCES_DIR + new Date().getTime() + "-" + filename);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);
    }

}
