package recamp.authenticationproject.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import recamp.authenticationproject.global.exception.UnauthorizedAccessException;
import recamp.authenticationproject.global.utility.JwtUtils;

@Slf4j
@RequiredArgsConstructor
public class PrivateInterceptor implements HandlerInterceptor {
    private final JwtUtils jwtUtils;

    @Override

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("PrivateInterceptor.preHandle");
        log.info(request.getRequestURL() + "-" + request.getRemoteAddr());
        log.info("====================");
        String url = request.getRequestURL().toString();
        String bearerToken = request.getHeader("Authorization");
        try {
            String userId = jwtUtils.getUserPk(bearerToken);
            Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(
                    HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            String pathUserId = pathVariables.get("userId").toString();
            if (!pathUserId.equals(userId)) {
                throw new UnauthorizedAccessException();
            }
        } catch (Exception e) {
            request.setAttribute("exception", "UnauthorizedAccessException");
            request.getRequestDispatcher("/api/v1/error").forward(request, response);
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
