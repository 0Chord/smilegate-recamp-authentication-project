package recamp.authenticationproject.global.dto;

import lombok.Getter;

@Getter
public class GeneralResponseDto {
    private String exception;
    private int code;
    private String message;

    public GeneralResponseDto(String exception, int code, String message) {
        this.exception = exception;
        this.code = code;
        this.message = message;
    }
}
