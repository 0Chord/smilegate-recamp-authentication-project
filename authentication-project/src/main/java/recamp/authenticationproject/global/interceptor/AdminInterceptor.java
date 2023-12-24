package recamp.authenticationproject.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import recamp.authenticationproject.global.utility.JwtUtils;

@Slf4j
@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {

    private final JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("AdminInterceptor.preHandle");
        log.info(request.getRequestURL() + "-" + request.getRemoteAddr());
        log.info("====================");
        String authorization = request.getHeader("Authorization");
        try {
            jwtUtils.validationAdmin(authorization);
        } catch (Exception e) {
            request.setAttribute("exception", "UnauthorizedAccessException");
            request.getRequestDispatcher("/api/v1/error").forward(request, response);
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
