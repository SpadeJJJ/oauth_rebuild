package com.spade.oauth.redis.repository;

import com.spade.oauth.domain.redis.OauthState;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OauthStateRepository extends CrudRepository<OauthState, Long> {

    Optional<OauthState> findByState(String state);

    void deleteById(Long id);
}
