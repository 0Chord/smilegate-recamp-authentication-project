package recamp.authenticationproject.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import recamp.authenticationproject.global.utility.JwtUtils;

@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {

    private final JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("AdminInterceptor.preHandle");
        System.out.println(request.getRequestURL() + "-" + request.getRemoteAddr());
        System.out.println("====================");
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
