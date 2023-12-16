package recamp.authenticationproject.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import recamp.authenticationproject.global.interceptor.AdminInterceptor;
import recamp.authenticationproject.global.interceptor.PrivateInterceptor;
import recamp.authenticationproject.global.interceptor.UserInterceptor;
import recamp.authenticationproject.global.utility.JwtUtils;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final JwtUtils jwtUtils;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor(jwtUtils))
                .addPathPatterns("/**")
                .excludePathPatterns("/api/v1/verified/**", "/api/v1/message/**", "/api/v1/user/join", "/error",
                        "/api/v1/error");
        registry.addInterceptor(new UserInterceptor(jwtUtils))
                .addPathPatterns("/**")
                .excludePathPatterns("/api/v1/verified/**", "/api/v1/message/**", "/api/v1/user/join",
                        "/api/v1/admin/**", "/error", "/api/v1/error");
        registry.addInterceptor(new PrivateInterceptor(jwtUtils))
                .addPathPatterns("/**")
                .excludePathPatterns("/api/v1/verified/**", "/api/v1/message/**", "/api/v1/user/join",
                        "/api/v1/admin/**", "/error", "/api/v1/error");
    }

}
