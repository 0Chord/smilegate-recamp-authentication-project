package recamp.authenticationproject.global.dto;

import lombok.Getter;
import recamp.authenticationproject.user.domain.Role;

@Getter
public class JwtDto {
    private Long userId;
    private String role;
    private String name;

    private JwtDto() {
        throw new IllegalStateException();
    }

    private JwtDto(Long userId, Role role, String name) {
        this.userId = userId;
        this.role = makeRole(role);
        this.name = name;
    }

    public static JwtDto make(Long userId, Role role, String name) {
        return new JwtDto(userId, role, name);
    }

    private String makeRole(Role role) {
        if (role.equals(Role.USER)) {
            return "USER";
        }
        return "ADMIN";
    }
}
