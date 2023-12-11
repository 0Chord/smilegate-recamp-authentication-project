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

@Entity
@Getter
@NoArgsConstructor
public class Member {

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
}
