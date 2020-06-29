package com.bancaonline.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancaonline.api.model.ConsortiumToken;

@Repository
public interface ConsortiumTokenRepository extends CrudRepository<ConsortiumToken, Long> {

	Optional<ConsortiumToken> findByTokenAndStatusId(String token, Long statusId);

	Optional<ConsortiumToken> findByShortUrl(String shortUrl);

	Optional<ConsortiumToken> findByShortToken(String shortToken);

	List<ConsortiumToken> findByConsortiumId(Long id);

	List<ConsortiumToken> findByConsortiumName(String name);

	List<ConsortiumToken> findByConsortiumNameAndStatusId(String name, long statusId);

	List<ConsortiumToken> findByStatusId(Long statusId);

	List<ConsortiumToken> findByIsUsedAndConsortiumName(boolean isUsed, String consortiumName);

	List<ConsortiumToken> findByIsUsed(boolean isUsed);

}
