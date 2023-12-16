package recamp.authenticationproject.global.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import recamp.authenticationproject.global.controller.MessageController;
import recamp.authenticationproject.global.dto.GeneralResponseDto;
import recamp.authenticationproject.global.exception.IllegalCodeException;
import recamp.authenticationproject.global.exception.MessageException;

@RestControllerAdvice(basePackageClasses = MessageController.class)
public class MessageControllerAdvice {

    private static final String MESSAGE_EXCEPTION = "MessageException";
    private static final int MESSAGE_EXCEPTION_CODE = 10001;
    private static final String MESSAGE_EXCEPTION_MESSAGE = "메세지 전송에 실패했습니다. 다시 시도해주시길 바랍니다";
    private static final String CODE_EXCEPTION = "IllegalCodeException";
    private static final int CODE_EXCEPTION_CODE = 10002;
    private static final String CODE_EXCEPTION_MESSAGE = "인증번호가 다릅니다. 다시 시도해주시길 바랍니다";

    @ExceptionHandler(value = MessageException.class)
    public ResponseEntity<GeneralResponseDto> messageExceptionHandler(MessageException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new GeneralResponseDto(MESSAGE_EXCEPTION, MESSAGE_EXCEPTION_CODE, MESSAGE_EXCEPTION_MESSAGE));
    }

    @ExceptionHandler(value = IllegalCodeException.class)
    public ResponseEntity<GeneralResponseDto> messageExceptionHandler(IllegalCodeException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new GeneralResponseDto(CODE_EXCEPTION, CODE_EXCEPTION_CODE, CODE_EXCEPTION_MESSAGE));
    }
}
