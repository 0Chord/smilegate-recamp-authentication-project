package recamp.authenticationproject.global.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import recamp.authenticationproject.global.controller.AuthenticationController;
import recamp.authenticationproject.global.dto.GeneralResponseDto;
import recamp.authenticationproject.global.exception.DeleteMemberException;
import recamp.authenticationproject.global.exception.DuplicateEmailException;
import recamp.authenticationproject.global.exception.IllegalPasswordException;
import recamp.authenticationproject.global.exception.SuspendedMemberException;
import recamp.authenticationproject.global.exception.UnauthorizedAccessException;
import recamp.authenticationproject.global.exception.UnauthorizedMessageException;

@RestControllerAdvice(basePackageClasses = AuthenticationController.class)
public class AuthenticationControllerAdvice {
    private static final String SUSPENDED_EXCEPTION_EXCEPTION = "SuspendedMemberException";
    private static final int SUSPENDED_EXCEPTION_EXCEPTION_CODE = 10003;
    private static final String SUSPENDED_EXCEPTION_EXCEPTION_MESSAGE = "정지된 회원입니다. 이용이 불가합니다";
    private static final String PASSWORD_EXCEPTION = "IllegalPasswordException";
    private static final int PASSWORD_EXCEPTION_CODE = 10004;
    private static final String PASSWORD_EXCEPTION_MESSAGE = "비밀번호가 틀립니다. 다시 시도해주시길 바랍니다";
    private static final String UNAUTHORIZED_EXCEPTION = "UnauthorizedAccessException";
    private static final int UNAUTHORIZED_EXCEPTION_CODE = 10005;
    private static final String UNAUTHORIZED_MESSAGE_EXCEPTION = "UnauthorizedMessageException";
    private static final int UNAUTHORIZED_MESSAGE_EXCEPTION_CODE = 10006;
    private static final String UNAUTHORIZED_MESSAGE_EXCEPTION_MESSAGE = "문자인증이 안된 사용자입니다. 이용이 불가합니다";
    private static final String DUPLICATE_EMAIL_EXCEPTION = "DuplicateEmailException";
    private static final int DUPLICATE_EMAIL_EXCEPTION_CODE = 10007;
    private static final int DELETE_MEMBER_EXCEPTION_CODE = 10009;
    private static final String DELETE_MEMBER_EXCEPTION = "DeleteMemberException";


    @ExceptionHandler(SuspendedMemberException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(SuspendedMemberException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(SUSPENDED_EXCEPTION_EXCEPTION, SUSPENDED_EXCEPTION_EXCEPTION_CODE,
                        SUSPENDED_EXCEPTION_EXCEPTION_MESSAGE));
    }

    @ExceptionHandler(IllegalPasswordException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(IllegalPasswordException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(PASSWORD_EXCEPTION, PASSWORD_EXCEPTION_CODE, PASSWORD_EXCEPTION_MESSAGE));
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(UnauthorizedAccessException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(UNAUTHORIZED_EXCEPTION, UNAUTHORIZED_EXCEPTION_CODE, e.getMessage()));
    }

    @ExceptionHandler(UnauthorizedMessageException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(UnauthorizedMessageException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(UNAUTHORIZED_MESSAGE_EXCEPTION, UNAUTHORIZED_MESSAGE_EXCEPTION_CODE,
                        UNAUTHORIZED_MESSAGE_EXCEPTION_MESSAGE));
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(DuplicateEmailException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(DUPLICATE_EMAIL_EXCEPTION, DUPLICATE_EMAIL_EXCEPTION_CODE,
                        e.getMessage()));
    }

    @ExceptionHandler(DeleteMemberException.class)
    public ResponseEntity<GeneralResponseDto> exceptionHandler(DeleteMemberException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new GeneralResponseDto(DELETE_MEMBER_EXCEPTION, DELETE_MEMBER_EXCEPTION_CODE,
                        e.getMessage()));
    }
}
