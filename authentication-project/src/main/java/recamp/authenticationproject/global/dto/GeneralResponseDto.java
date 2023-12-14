package recamp.authenticationproject.global.dto;

public class GeneralResponseDto {

    private int code;
    private String message;

    public GeneralResponseDto(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
