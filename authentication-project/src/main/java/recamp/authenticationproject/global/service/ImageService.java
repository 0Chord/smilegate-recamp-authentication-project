package recamp.authenticationproject.global.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String convertImage(MultipartFile image);
}
