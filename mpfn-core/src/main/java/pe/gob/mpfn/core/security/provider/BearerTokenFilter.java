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
		String authHeader = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authHeader == null || !authHeader.startsWith(HeaderValuesType.BEARER.getValue())) {
			throw new NotAuthorizedException(HeaderValuesType.BEARER.getValue());
		}
		Claims token = parseToken(authHeader);
		if(token==null){
			throw new NotAuthorizedException(HeaderValuesType.BEARER.getValue()+" error=\"invalid_token\"");
		}
//		validar expiracion
//		if (verifyToken(token) == false) {
//			throw new NotAuthorizedException(HeaderValuesType.BEARER.getValue()+" error=\"invalid_token\"");
//		}
		//Todo OK coloco los datos en ThreadLocal
		createUserTrack(token);
	}
	
	/**
	 * Parses the token.
	 *
	 * @param header el header
	 * @return the claims
	 */
	private Claims parseToken(String header) {
		String token = header.substring(HeaderValuesType.BEARER.getValue().length()+1);
		return tokenGenerator.getPayLoadToken(token);
	}
	
	/**
	 * Creates the user track.
	 *
	 * @param token el token
	 */
	private void createUserTrack(Claims token){
		UserTrack userTrack = new UserTrack();
		userTrack.setUserName(token.getSubject());
		userTrack.setIpAddress((String)token.get(SignatureParameter.getInstance().getUserIP()));
		userTrack.setAuditTime(LocalDateTime.now());
		ThreadLocalContextHolder.put(RegistryContextHolder.USER_TRACK, userTrack);
	}
	
	/**
	 * Verify token.
	 *
	 * @param token el token
	 * @return true, en caso de exito
	 */
	private boolean verifyToken(String token) {
		return false;
	}


}
