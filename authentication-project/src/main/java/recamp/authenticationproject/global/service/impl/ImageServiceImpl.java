package recamp.authenticationproject.global.service.impl;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import recamp.authenticationproject.global.service.ImageService;

@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final Storage storage;
    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Override
    public String convertImage(MultipartFile image) {
        try {
            String uuid = UUID.randomUUID().toString();
            String ext = image.getContentType();
            BlobInfo blobInfo = storage.create(
                    BlobInfo.newBuilder(bucketName, uuid)
                            .setContentType(ext)
                            .build(),
                    image.getInputStream()
            );
            return uuid;
        } catch (IOException e) {
            throw new IllegalStateException();
        }

    }
}
