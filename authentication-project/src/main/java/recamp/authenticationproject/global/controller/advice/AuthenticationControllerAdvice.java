package recamp.authenticationproject.global.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import recamp.authenticationproject.global.dto.GeneralResponseDto;
import recamp.authenticationproject.global.exception.IllegalPasswordException;
import recamp.authenticationproject.global.exception.SuspendedMemberException;
import recamp.authenticationproject.global.exception.UnauthorizedAccessException;
import recamp.authenticationproject.global.exception.UnauthorizedMessageException;

@RestControllerAdvice
public class AuthenticationControllerAdvice {

    private static final int SUSPEND_EXCEPTION_CODE = 10003;
    private static final String SUSPEND_EXCEPTION = "정지된 회원입니다. 이용이 불가합니다";
    private static final int PASSWORD_EXCEPTION_CODE = 10004;
    private static final String PASSWORD_EXCEPTION = "비밀번호가 틀립니다. 다시 시도해주시길 바랍니다";
    private static final int UNAUTHORIZED_EXCEPTION_CODE = 10005;
    private static final int UNAUTHORIZED_MESSAGE_EXCEPTION_CODE = 10006;
    private static final String UNAUTHORIZED_MESSAGE_EXCEPTION = "문자인증이 안되어있습니다. 이용이 불가합니다";

    @ExceptionHandler(SuspendedMemberException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(SuspendedMemberException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(SUSPEND_EXCEPTION_CODE, SUSPEND_EXCEPTION));
    }

    @ExceptionHandler(IllegalPasswordException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(IllegalPasswordException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(PASSWORD_EXCEPTION_CODE, PASSWORD_EXCEPTION));
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(UnauthorizedAccessException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(10005, e.getMessage()));
    }

    @ExceptionHandler(UnauthorizedMessageException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(UnauthorizedMessageException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(UNAUTHORIZED_EXCEPTION_CODE, UNAUTHORIZED_MESSAGE_EXCEPTION));
    }
}
