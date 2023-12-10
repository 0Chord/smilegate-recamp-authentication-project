package recamp.authenticationproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AuthenticationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationProjectApplication.class, args);
	}

}
