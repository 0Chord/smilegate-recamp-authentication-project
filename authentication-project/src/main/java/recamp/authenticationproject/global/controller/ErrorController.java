package recamp.authenticationproject.global.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recamp.authenticationproject.global.exception.UnauthorizedAccessException;

@RestController
@RequestMapping("/api/v1/error")
public class ErrorController {

    @GetMapping
    public void error(HttpServletRequest request, HttpServletResponse response) {
        String exception = (String) request.getAttribute("exception");
        if (exception.equals("UnauthorizedAccessException")) {
            throw new UnauthorizedAccessException();
        }
    }
}
