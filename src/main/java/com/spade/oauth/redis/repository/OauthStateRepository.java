package com.spade.oauth.redis.repository;

import com.spade.oauth.domain.redis.OauthState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OauthStateRepository extends CrudRepository<OauthState, Long> {

    Optional<OauthState> findByState(String state);

    void deleteById(Long id);
}
