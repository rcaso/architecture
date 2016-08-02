package pe.gob.mpfn.jsf.core.realm;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade.Request;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class AjaxKeycloakConfigResolver.
 *
 * @author OSIS
 * @version 1.0 , 27/07/2016
 */
public class AjaxKeycloakConfigResolver implements KeycloakConfigResolver {
	
	/** La cache. */
	private final Map<String, KeycloakDeployment> cache = new ConcurrentHashMap<String, KeycloakDeployment>();
	
	/** La regular realm. */
	private final String REGULAR_REALM ="keycloak-app.json";
	
	/** La ajax realm. */
	private final String AJAX_REALM ="keycloak-ajax.json";
	

	/* (non-Javadoc)
	 * @see org.keycloak.adapters.KeycloakConfigResolver#resolve(org.keycloak.adapters.spi.HttpFacade.Request)
	 */
	@Override
	public KeycloakDeployment resolve(Request request) {
		String realmName = AJAX_REALM;
		if(!isAjaxRequest(request)){
			realmName = REGULAR_REALM;
		}
		KeycloakDeployment deployment = cache.get(realmName);
        if (null == deployment) {
            // not found on the simple cache, try to load it from the file system
            InputStream is = getClass().getResourceAsStream("/" + realmName);
            if (is == null) {
                throw new IllegalStateException("Not able to find the file /" + realmName);
            }
            deployment = KeycloakDeploymentBuilder.build(is);
            cache.put(realmName, deployment);
        }
        return deployment;
	}
	
	/**
	 * Comprueba si es ajax request.
	 *
	 * @param request el request
	 * @return true, si es ajax request
	 */
	private boolean isAjaxRequest(Request request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

}
