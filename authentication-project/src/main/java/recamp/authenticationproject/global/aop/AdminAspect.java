package recamp.authenticationproject.global.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import recamp.authenticationproject.global.utility.JwtUtils;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AdminAspect {

    private final JwtUtils jwtUtils;

    @Around("@annotation(recamp.authenticationproject.global.annotation.AuthorizeAdmin)")
    public Object validToken(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String methodName = joinPoint.getSignature().getName();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        try {
            String bearerToken = request.getHeader("Authorization");
            jwtUtils.validationAdmin(bearerToken);
            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("{}에서 접속한 {} API의 {} 메서드가 걸린 시간 = {}", ip, url, methodName, endTime - startTime);
        }
    }
}
