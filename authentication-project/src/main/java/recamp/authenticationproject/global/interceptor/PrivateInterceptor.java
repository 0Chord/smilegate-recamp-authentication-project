package recamp.authenticationproject.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import recamp.authenticationproject.global.exception.UnauthorizedAccessException;
import recamp.authenticationproject.global.utility.JwtUtils;
import recamp.authenticationproject.user.service.MemberService;

@Slf4j
@RequiredArgsConstructor
public class PrivateInterceptor implements HandlerInterceptor {
    private final JwtUtils jwtUtils;
    private static final String UNAUTHORIZED_ACCESS = "잘못된 접근입니다. 다시 시도해주세요";

    @Override

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURL().toString();
        String bearerToken = request.getHeader("bearer-token");
        String userId = jwtUtils.getUserPk(bearerToken);
        log.info(userId+"님이 "+url+"로 접속을 시도했습니다.");
        if (!url.equals(userId)) {
            throw new UnauthorizedAccessException(UNAUTHORIZED_ACCESS);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
