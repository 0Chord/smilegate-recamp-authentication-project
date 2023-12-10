package recamp.authenticationproject.global.config;

import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import recamp.authenticationproject.global.service.MessageService;
import recamp.authenticationproject.global.service.impl.MessageServiceImpl;

@Configuration
public class AppConfig {

    @Value("${message.api_key}")
    private String apiKey;

    @Value("${message.api_secret}")
    private String apiSecret;

    @Value("${message.domain}")
    private String domain;

    @Bean
    public DefaultMessageService defaultMessageService() {
        return new DefaultMessageService(apiKey, apiSecret, domain);
    }

    @Bean
    public MessageService messageService() {
        return new MessageServiceImpl(defaultMessageService());
    }
}
