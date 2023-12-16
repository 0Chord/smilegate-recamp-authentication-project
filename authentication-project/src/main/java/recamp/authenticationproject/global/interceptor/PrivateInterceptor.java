package recamp.authenticationproject.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import recamp.authenticationproject.global.exception.UnauthorizedAccessException;
import recamp.authenticationproject.global.utility.JwtUtils;

@Slf4j
@RequiredArgsConstructor
public class PrivateInterceptor implements HandlerInterceptor {
    private final JwtUtils jwtUtils;

    @Override

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("PrivateInterceptor.preHandle");
        System.out.println(request.getRequestURL() + "-" + request.getRemoteAddr());
        System.out.println("====================");
        String url = request.getRequestURL().toString();
        String bearerToken = request.getHeader("bearer-token");
        try {
            String userId = jwtUtils.getUserPk(bearerToken);
            log.info(userId + "님이 " + url + "로 접속을 시도했습니다.");
            if (!url.equals(userId)) {
                throw new UnauthorizedAccessException();
            }
        } catch (Exception e) {
            request.setAttribute("exception", "UnauthorizedAccessException");
            request.getRequestDispatcher("/api/v1/error").forward(request, response);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
