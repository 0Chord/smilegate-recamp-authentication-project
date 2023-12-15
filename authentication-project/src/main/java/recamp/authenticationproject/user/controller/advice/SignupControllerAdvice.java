package recamp.authenticationproject.user.controller.advice;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import recamp.authenticationproject.global.dto.GeneralResponseDto;
import recamp.authenticationproject.global.exception.IllegalPasswordException;
import recamp.authenticationproject.user.controller.SignupController;

@RestControllerAdvice(basePackageClasses = SignupController.class)
public class SignupControllerAdvice {
    private static final String PASSWORD_EXCEPTION = "IllegalPasswordException";
    private static final int PASSWORD_EXCEPTION_CODE = 10004;
    private static final String CONSTRAINT_VIOLATION_EXCEPTION = "ConstraintViolationException";
    private static final int CONSTRAINT_VIOLATION_EXCEPTION_CODE = 10008;
    private static final String CONSTRAINT_VIOLATION_EXCEPTION_MESSAGE = "중복된 계정입니다. 다른 계정으로 다시 시도해주시길 바랍니다";

    @ExceptionHandler(IllegalPasswordException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(IllegalPasswordException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(PASSWORD_EXCEPTION, PASSWORD_EXCEPTION_CODE,
                        e.getMessage()));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(CONSTRAINT_VIOLATION_EXCEPTION, CONSTRAINT_VIOLATION_EXCEPTION_CODE,
                        CONSTRAINT_VIOLATION_EXCEPTION_MESSAGE));
    }
}
