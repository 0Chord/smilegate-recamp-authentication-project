package recamp.authenticationproject.global.repository.impl;

import java.util.Iterator;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import recamp.authenticationproject.global.redis.RefreshToken;
import recamp.authenticationproject.global.repository.RefreshTokenRedisRepository;
import recamp.authenticationproject.global.repository.RefreshTokenRepository;

@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenRedisRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findById(Long id) {
        return refreshTokenRedisRepository.findById(id);
    }

    @Override
    public Iterable<RefreshToken> findAll() {
        return refreshTokenRedisRepository.findAll();
    }
}
