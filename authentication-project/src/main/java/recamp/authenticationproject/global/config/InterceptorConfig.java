package recamp.authenticationproject.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import recamp.authenticationproject.global.interceptor.AdminInterceptor;
import recamp.authenticationproject.global.interceptor.UserInterceptor;
import recamp.authenticationproject.global.utility.JwtUtils;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final JwtUtils jwtUtils;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor(jwtUtils))
                .excludePathPatterns("/test/**", "/user", "/valid");
        registry.addInterceptor(new UserInterceptor(jwtUtils))
                .excludePathPatterns("/test/**", "/admin", "/valid");
    }

}
