package pe.gob.mpfn.core.security.provider;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

import io.jsonwebtoken.Claims;
import pe.gob.mpfn.core.security.TokenAuthenticated;
import pe.gob.mpfn.core.security.contextholder.ThreadLocalContextHolder;
import pe.gob.mpfn.security.audit.RegistryContextHolder;
import pe.gob.mpfn.security.audit.entity.UserTrack;
import pe.gob.mpfn.security.authorization.type.HeaderValuesType;
import pe.gob.mpfn.security.configuration.SignatureParameter;
import pe.gob.mpfn.security.generation.controller.TokenGeneratorController;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class BearerTokenFilter.
 *
 * @author OSIS
 * @version 1.0 , 21/04/2016
 */
@Provider
@Priority(Priorities.AUTHORIZATION)
@TokenAuthenticated
public class BearerTokenFilter implements ContainerRequestFilter {
	
	/** La token generator. */
	@Inject
	TokenGeneratorController tokenGenerator;

	/* (non-Javadoc)
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {
		KeycloakPrincipal<KeycloakSecurityContext> keyCloak = (KeycloakPrincipal<KeycloakSecurityContext>)ctx.getSecurityContext().getUserPrincipal();
		if(keyCloak != null){
			createUserTrack(keyCloak.getKeycloakSecurityContext().getToken());
		}
	}
	
	/**
	 * Creates the user track.
	 *
	 * @param token el token
	 */
	private void createUserTrack(AccessToken token){
		UserTrack userTrack = new UserTrack();
		userTrack.setUserName(token.getPreferredUsername());
		userTrack.setIpAddress("255.255.255.255");
		userTrack.setAuditTime(LocalDateTime.now());
		ThreadLocalContextHolder.put(RegistryContextHolder.USER_TRACK, userTrack);
	}
}
