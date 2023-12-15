package recamp.authenticationproject.global.service;

import recamp.authenticationproject.global.dto.RefreshTokenDto;
import recamp.authenticationproject.global.redis.RefreshToken;

public interface RefreshTokenService {

    void register(RefreshTokenDto refreshTokenDto);

    RefreshToken findById(Long id);

    void deleteToken(RefreshToken refreshToken);
}
