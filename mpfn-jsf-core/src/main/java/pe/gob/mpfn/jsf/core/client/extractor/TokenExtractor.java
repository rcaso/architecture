package pe.gob.mpfn.jsf.core.client.extractor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.representation.EntitlementResponse;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class TokenExtractor.
 *
 * @author OSIS
 * @version 1.0 , 22/07/2016
 */
@ApplicationScoped
public class TokenExtractor {
	
	/** La request. */
	@Inject
    HttpServletRequest request;
	
	/**
	 * Instancia un nuevo token extractor.
	 */
	public TokenExtractor() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Obtiene access token.
	 *
	 * @param clientId el client id
	 * @param authorizationEnabled el authorization enabled
	 * @return access token
	 */
	public String getAccessToken(String clientId,boolean authorizationEnabled){
		String token = null;
		if(!authorizationEnabled){
		//Solo enviamos token de acceso
			KeycloakPrincipal<KeycloakSecurityContext> keyCloak = (KeycloakPrincipal<KeycloakSecurityContext>) request.getUserPrincipal();
			token = keyCloak.getKeycloakSecurityContext().getTokenString();	
		} else {
		//Tenemos que utilizar el api Entitlement para devolver token RPT con informacion de seguridad de recursos
			KeycloakPrincipal<KeycloakSecurityContext> keyCloak = (KeycloakPrincipal<KeycloakSecurityContext>) request.getUserPrincipal();
			AuthzClient authClient = AuthzClient.create();
			EntitlementResponse accessToken =authClient.entitlement(keyCloak.getKeycloakSecurityContext().getTokenString()).getAll(clientId);
			token = accessToken.getRpt();
		}
		return token;
	}

}
