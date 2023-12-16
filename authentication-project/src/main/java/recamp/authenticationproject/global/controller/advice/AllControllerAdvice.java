package recamp.authenticationproject.global.controller.advice;

import java.security.SignatureException;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import recamp.authenticationproject.global.dto.GeneralResponseDto;
import recamp.authenticationproject.global.exception.UnauthorizedAccessException;

@RestControllerAdvice
public class AllControllerAdvice {

    private static final String SUSPENDED_EXCEPTION_EXCEPTION = "NoSuchElementException";
    private static final int SUSPENDED_EXCEPTION_EXCEPTION_CODE = 20001;
    private static final String SUSPENDED_EXCEPTION_EXCEPTION_MESSAGE = "잘못된 시도입니다. 처음부터 다시 시도해주시길 바랍니다.";

    private static final String ILLEGAL_INPUT_EXCEPTION = "IllegalInputException";
    private static final int ILLEGAL_INPUT_EXCEPTION_CODE = 20002;
    private static final String ILLEGAL_INPUT_EXCEPTION_MESSAGE = "잘못된 입력입니다. 다시 입력해주시길 바랍니다.";
    private static final String SIGNATURE_EXCEPTION = "SignatureException";
    private static final int SIGNATURE_EXCEPTION_CODE = 20003;
    private static final String SIGNATURE_EXCEPTION_MESSAGE = "잘못된 인증 토큰입니다. 다시 시도해주시길 바랍니다";
    private static final String UNAUTHORIZED_EXCEPTION = "UnauthorizedAccessException";
    private static final int UNAUTHORIZED_EXCEPTION_CODE = 20004;
    private static final String UNAUTHORIZED_EXCEPTION_MESSAGE = "인증에 싪패했습니다. 재발급 받으시길 바랍니다.";

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(SUSPENDED_EXCEPTION_EXCEPTION, SUSPENDED_EXCEPTION_EXCEPTION_CODE,
                        SUSPENDED_EXCEPTION_EXCEPTION_MESSAGE));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(ILLEGAL_INPUT_EXCEPTION, ILLEGAL_INPUT_EXCEPTION_CODE,
                        ILLEGAL_INPUT_EXCEPTION_MESSAGE));
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(SignatureException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(SIGNATURE_EXCEPTION, SIGNATURE_EXCEPTION_CODE,
                        SIGNATURE_EXCEPTION_MESSAGE));
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(UnauthorizedAccessException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(UNAUTHORIZED_EXCEPTION, UNAUTHORIZED_EXCEPTION_CODE,
                        UNAUTHORIZED_EXCEPTION_MESSAGE));
    }
}
