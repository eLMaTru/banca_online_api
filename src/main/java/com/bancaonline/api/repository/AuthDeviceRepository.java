package com.bancaonline.api.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bancaonline.api.model.AuthDevice;
import com.bancaonline.api.model.Status;

@Repository
public interface AuthDeviceRepository extends CrudRepository<AuthDevice, Long> {

	Optional<AuthDevice> findByTokenAndIpAndStatus(String token, String ip, Status status);

	List<AuthDevice> findByTokenAndStatus(String token, Status status);

	List<AuthDevice> findByTokenAndIp(String token, String ip);

	List<AuthDevice> findByToken(String token);

	boolean existsByIpAndToken(String ip, String token);

	@Transactional
	@Modifying
	@Query("DELETE FROM AuthDevice a where a.token = :token")
	void cleanIpsByToken(@Param("token") String token);

}
