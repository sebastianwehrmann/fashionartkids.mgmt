package fashionartkids.mgmt.service;

import fashionartkids.mgmt.entity.MediaEntity;
import fashionartkids.mgmt.repository.FileSystemRepository;
import fashionartkids.mgmt.repository.MediaRepository;
import fashionartkids.mgmt.repository.TalentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Service
public class MediaService {

    @Autowired
    MediaRepository mediaRepository;
    @Autowired
    TalentRepository talentRepository;
    @Autowired
    FileSystemRepository fileSystemRepository;
    public String add(int id, MultipartFile[] files) {
        for (MultipartFile file : files) {
            try {
                String hash = UUID.randomUUID().toString();
                fileSystemRepository.save(file.getBytes(), hash);
                mediaRepository.save(
                        new MediaEntity()
                                .filename(file.getOriginalFilename())
                                .hash(hash)
                                .type(file.getContentType())
                                .talent(talentRepository.findById(id).orElseThrow())
                );
            } catch (Exception e) {
                return "upsi error";
            }

        }
        return "hi";
    }

}
