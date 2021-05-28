package ar.com.avaco.ws.rest.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ar.com.avaco.ws.rest.security.dto.UpdatePasswordDTO;

public interface ClienteEPPortalService extends UserDetailsService {

	void updatePassword(UpdatePasswordDTO updatePassword);

	void sendMissingPassword(String username);
	
	boolean isSistemaExterno(String username);

}
