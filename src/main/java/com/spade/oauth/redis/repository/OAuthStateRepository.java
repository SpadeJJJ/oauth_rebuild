package com.spade.oauth.redis.repository;

import com.spade.oauth.domain.redis.OAuthState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Redis State 관리용 Repository
 */
@Repository
//@ConditionalOnProperty(prefix = "spring.data.redis.repositories", name = "enabled", havingValue = "true",
//        matchIfMissing = true)
public interface OAuthStateRepository extends CrudRepository<OAuthState, Long> {

    Optional<OAuthState> findByState(String state);

    void deleteById(Long id);
}
