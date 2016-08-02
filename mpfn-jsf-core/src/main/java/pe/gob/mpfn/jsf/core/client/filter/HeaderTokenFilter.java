package pe.gob.mpfn.jsf.core.client.filter;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;

import pe.gob.mpfn.core.configuration.Configurable;
import pe.gob.mpfn.jsf.core.client.extractor.TokenExtractor;
import pe.gob.mpfn.security.authorization.type.HeaderValuesType;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class HeaderTokenFilter.
 *
 * @author OSIS
 * @version 1.0 , 22/07/2016
 */
@ApplicationScoped
@Provider
public class HeaderTokenFilter implements ClientRequestFilter {
	
	/** La token extractor. */
	@Inject
	TokenExtractor tokenExtractor;
	
	/** La client id services app. */
	@Inject @Configurable
	String clientIdServicesApp;
	
	/** La authorization enabled. */
	@Inject @Configurable
	boolean authorizationEnabled;
	/**
	 * Filter.
	 *
	 * @param requestContext el request context
	 * @throws IOException Señales de que una excepción de E / S se ha producido.
	 */
	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		requestContext.getHeaders().
		putSingle(HeaderValuesType.AUTHORIZATION.getValue(),HeaderValuesType.BEARER.getValue()+" "+tokenExtractor.getAccessToken(clientIdServicesApp,authorizationEnabled));
	}

}
