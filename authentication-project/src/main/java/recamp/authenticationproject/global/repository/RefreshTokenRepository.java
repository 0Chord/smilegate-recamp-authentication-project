package recamp.authenticationproject.global.repository;

import java.util.Iterator;
import java.util.Optional;
import recamp.authenticationproject.global.redis.RefreshToken;

public interface RefreshTokenRepository {

    void save(RefreshToken refreshToken);

    Optional<RefreshToken> findById(Long id);

    Iterable<RefreshToken> findAll();

    void delete(RefreshToken refreshToken);

}
