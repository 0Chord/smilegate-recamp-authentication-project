package recamp.authenticationproject.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import recamp.authenticationproject.global.exception.DeleteMemberException;
import recamp.authenticationproject.global.exception.SuspendedMemberException;
import recamp.authenticationproject.global.exception.UnauthorizedAccessException;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;
    @NotBlank
    private String image;

    private boolean verified;

    @Builder
    public Member(String password, PersonalInformation information, Role role, boolean verified,String imageUrl) {
        this.verified = verified;
        this.password = password;
        this.personalInformation = information;
        this.createdAt = LocalDateTime.now();
        this.lastedAccessAt = LocalDateTime.now();
        this.role = role;
        this.suspendedAt = LocalDateTime.now();
        this.status = UserStatus.WELL;
        this.image = imageUrl;
    }

    public void updateLastedAccessAt() {
        this.lastedAccessAt = LocalDateTime.now();
    }

    public String convertRole() {
        return String.format("ROLE_%s", role.name().toUpperCase());
    }

    public void checkVerified() {
        if (!verified) {
            throw new UnauthorizedAccessException("인증이 안된 회원입니다. 다시 가입해주시길 바랍니다");
        }
    }

    public void checkSuspendTime() {
        if (suspendedAt.isAfter(LocalDateTime.now())) {
            throw new SuspendedMemberException("정지된 회원입니다. 이용이 불가합니다.");
        }
    }

    public void updateSuspendTime(int days) {
        this.suspendedAt = suspendedAt.plusDays(days);
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateUserStatus(UserStatus status) {
        this.status = status;
    }

    public void checkDeleteUser() {
        if (status.equals(UserStatus.DELETE)) {
            throw new DeleteMemberException("운영자로부터 삭제된 회원입니다. 이용이 불가합니다");
        }
    }

    public void updateImage(String image) {
        this.image = image;
    }
}
