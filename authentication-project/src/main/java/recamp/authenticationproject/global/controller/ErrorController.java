package recamp.authenticationproject.global.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recamp.authenticationproject.global.exception.UnauthorizedAccessException;

@RestController
@RequestMapping("/api/v1/error")
public class ErrorController {

    @PostMapping
    public void postError(HttpServletRequest request, HttpServletResponse response) {
        String exception = (String) request.getAttribute("exception");
        if (exception.equals("UnauthorizedAccessException")) {
            throw new UnauthorizedAccessException();
        }
    }

    @GetMapping
    public void getError(HttpServletRequest request, HttpServletResponse response) {
        String exception = (String) request.getAttribute("exception");
        if (exception.equals("UnauthorizedAccessException")) {
            throw new UnauthorizedAccessException();
        }
    }

    @PatchMapping
    public void patchError(HttpServletRequest request, HttpServletResponse response) {
        String exception = (String) request.getAttribute("exception");
        if (exception.equals("UnauthorizedAccessException")) {
            throw new UnauthorizedAccessException();
        }
    }
}
