package recamp.authenticationproject.global.service.impl;


import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import recamp.authenticationproject.global.exception.MessageException;
import recamp.authenticationproject.global.service.MessageService;

@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final DefaultMessageService defaultMessageService;
    private static final String form = "회원가입 인증번호 입니다.\n외부에 노출되지 않도록 조심해주세요\n";
    @Value("${message.phone}")
    private String callerNumber;

    @Override
    public void send(String phone, String code) {
        Message message = new Message();
        message.setFrom(callerNumber);
        message.setTo(phone);
        message.setText(form + code);
        try {
            defaultMessageService.send(message);
        } catch (Exception exception) {
            throw new MessageException(exception);
        }
    }
}
