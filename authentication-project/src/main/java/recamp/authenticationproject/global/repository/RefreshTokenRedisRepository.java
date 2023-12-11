package recamp.authenticationproject.global.repository;

import org.springframework.data.repository.CrudRepository;
import recamp.authenticationproject.global.redis.RefreshToken;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, Long> {
}
