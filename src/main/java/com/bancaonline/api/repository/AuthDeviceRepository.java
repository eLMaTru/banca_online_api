package com.bancaonline.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bancaonline.api.model.AuthDevice;
import com.bancaonline.api.model.Status;

@Repository
public interface AuthDeviceRepository extends CrudRepository<AuthDevice, Long>{
	
	Optional<AuthDevice> findByTokenAndIpAndStatus(String token, String ip, Status status);	
	
	List<AuthDevice> findByTokenAndStatus(String token, Status status);	
	
	List<AuthDevice> findByTokenAndIp(String token, String ip);	
	
	boolean existsByIpAndToken(String ip, String token); 

}
