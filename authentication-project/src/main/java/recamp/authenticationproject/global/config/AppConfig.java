package recamp.authenticationproject.global.config;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import recamp.authenticationproject.global.repository.PhoneRedisRepository;
import recamp.authenticationproject.global.repository.PhoneRepository;
import recamp.authenticationproject.global.repository.impl.PhoneRepositoryImpl;
import recamp.authenticationproject.global.service.IdentityVerificationService;
import recamp.authenticationproject.global.service.LoginService;
import recamp.authenticationproject.global.service.MessageService;
import recamp.authenticationproject.global.service.PhoneService;
import recamp.authenticationproject.global.service.impl.IdentityVerificationServiceImpl;
import recamp.authenticationproject.global.service.impl.LoginServiceImpl;
import recamp.authenticationproject.global.service.impl.MessageServiceImpl;
import recamp.authenticationproject.global.service.impl.PhoneServiceImpl;
import recamp.authenticationproject.global.utility.JwtUtils;
import recamp.authenticationproject.user.repository.JpaMemberRepository;
import recamp.authenticationproject.user.repository.MemberRepository;
import recamp.authenticationproject.user.repository.impl.MemberRepositoryImpl;
import recamp.authenticationproject.user.service.MemberService;
import recamp.authenticationproject.user.service.impl.MemberServiceImpl;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final PhoneRedisRepository phoneRedisRepository;
    private final JpaMemberRepository jpaMemberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;
    @Value("${message.api_key}")
    private String apiKey;
    @Value("${message.api_secret}")
    private String apiSecret;
    @Value("${message.domain}")
    private String domain;

    @Bean
    public PhoneRepository phoneRepository() {
        return new PhoneRepositoryImpl(phoneRedisRepository);
    }

    @Bean
    public PhoneService phoneService() {
        return new PhoneServiceImpl(phoneRepository());
    }

    @Bean
    public IdentityVerificationService identityVerificationService() {
        return new IdentityVerificationServiceImpl(messageService(), phoneService());
    }

    @Bean
    public DefaultMessageService defaultMessageService() {
        return new DefaultMessageService(apiKey, apiSecret, domain);
    }

    @Bean
    public MessageService messageService() {
        return new MessageServiceImpl(defaultMessageService());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryImpl(jpaMemberRepository);
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository(), bCryptPasswordEncoder);
    }

    @Bean
    public LoginService loginService() {
        return new LoginServiceImpl(bCryptPasswordEncoder, memberService(), jwtUtils);
    }
}
