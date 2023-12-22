package recamp.authenticationproject.global.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ImageDto {

    @NotNull
    private Long userId;
    @NotNull
    private MultipartFile image;
}
