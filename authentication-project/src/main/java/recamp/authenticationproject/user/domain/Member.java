package recamp.authenticationproject.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import recamp.authenticationproject.global.exception.SuspendedMemberException;
import recamp.authenticationproject.global.exception.UnauthorizedAccessException;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    private static final String EXCEPTION_MESSAGE = "정지된 회원입니다. 이용이 불가합니다.";

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonalInformation personalInformation;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "suspended_at")
    private LocalDateTime suspendedAt;
    private String password;
    @Column(name = "lasted_access_at")
    private LocalDateTime lastedAccessAt;
    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean verified;

    @Builder
    public Member(String password, PersonalInformation information, Role role, boolean verified) {
        this.verified = verified;
        this.password = password;
        this.personalInformation = information;
        this.createdAt = LocalDateTime.now();
        this.lastedAccessAt = LocalDateTime.now();
        this.role = role;
        this.suspendedAt = LocalDateTime.now();
    }

    public void updateLastedAccessAt() {
        this.lastedAccessAt = LocalDateTime.now();
    }

    public String convertRole() {
        if (role.equals(Role.USER)) {
            return "ROLE_USER";
        }
        return "ROLE_ADMIN";
    }

    public void checkVerified() {
        if (!verified) {
            throw new UnauthorizedAccessException();
        }
    }

    public void checkSuspendTime() {
        if (suspendedAt.isAfter(LocalDateTime.now())) {
            throw new SuspendedMemberException(EXCEPTION_MESSAGE);
        }
    }
}
