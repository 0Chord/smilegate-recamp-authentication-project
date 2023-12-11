package recamp.authenticationproject.global.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import recamp.authenticationproject.global.dto.RefreshTokenDto;
import recamp.authenticationproject.global.redis.RefreshToken;
import recamp.authenticationproject.global.repository.RefreshTokenRepository;
import recamp.authenticationproject.global.service.RefreshTokenService;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Override
    @Transactional
    public void register(RefreshTokenDto refreshTokenDto) {
        RefreshToken refreshToken = RefreshToken.builder()
                .id(refreshTokenDto.getId())
                .token(refreshTokenDto.getToken())
                .expiration(refreshTokenDto.getExpiration())
                .role(refreshTokenDto.getRole())
                .build();
        repository.save(refreshToken);
    }

    @Override
    public RefreshToken findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
