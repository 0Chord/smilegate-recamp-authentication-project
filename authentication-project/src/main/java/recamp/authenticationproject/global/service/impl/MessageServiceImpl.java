package recamp.authenticationproject.global.service.impl;


import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import recamp.authenticationproject.global.service.MessageService;

@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final DefaultMessageService defaultMessageService;
    @Value("${message.phone}")
    private String callerNumber;

    @Override
    public void send(String phone) {
        Message message = new Message();
        message.setFrom(callerNumber);
        message.setTo(phone);
        message.setText("SMS는 한글 45자, 영자 90자까지 입력할 수 있습니다.");
        try {
            defaultMessageService.send(message);
        } catch (NurigoMessageNotReceivedException exception) {
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
