package pe.gob.mpfn.jsf.core.bean.base;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.ws.rs.client.Client;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import pe.gob.mpfn.jsf.core.client.filter.HeaderTokenFilter;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class BaseBean.
 *
 * @author OSIS
 * @version 1.0 , 23/05/2016
 */
public abstract class BaseBean implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -4904050226282386710L;
	
	/** La header token filter. */
	@Inject
	HeaderTokenFilter headerTokenFilter;
	
	/** La jaxrs client. */
	protected Client jaxrsClient;

	/**
	 * Instancia un nuevo base bean.
	 */
	public BaseBean() {
		
	}
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		//Especifico con wildfly
				jaxrsClient = new ResteasyClientBuilder()
		                .establishConnectionTimeout(2, TimeUnit.SECONDS)
//		                .socketTimeout(2, TimeUnit.SECONDS)
		                .register(headerTokenFilter)
		                .build();
	}
	
	/**
	 * Destroy.
	 */
	@PreDestroy
	public void destroy(){
		jaxrsClient.close();
	}
	
	

}
