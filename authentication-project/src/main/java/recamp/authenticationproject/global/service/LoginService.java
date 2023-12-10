package recamp.authenticationproject.global.service;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import recamp.authenticationproject.global.dto.LoginDto;

@Validated
public interface LoginService {

    List<String> login(LoginDto loginDto);
}
