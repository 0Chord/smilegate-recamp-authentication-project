package recamp.authenticationproject.global.service;

import org.springframework.transaction.annotation.Transactional;
import recamp.authenticationproject.global.dto.MembersResponseDto;

@Transactional(readOnly = true)
public interface ConvertService {

    MembersResponseDto membersResponseDto();
}
