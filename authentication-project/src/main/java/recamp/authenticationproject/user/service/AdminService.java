package recamp.authenticationproject.user.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import recamp.authenticationproject.global.annotation.AuthorizeAdmin;
import recamp.authenticationproject.global.dto.MembersResponseDto;
import recamp.authenticationproject.global.dto.SuspendUserDto;

@Validated
@Transactional(readOnly = true)
public interface AdminService {

    @Transactional
    @AuthorizeAdmin
    void updateSuspendAt(SuspendUserDto suspendUserDto);

    MembersResponseDto findMembers();

    @Transactional
    @AuthorizeAdmin
    void deleteMember(Long userId);

}
