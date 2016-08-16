package pe.gob.mpfn.jsf.core.menu.filter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.omnifaces.util.Beans;

import com.google.gson.Gson;

import pe.gob.mpfn.core.configuration.StageDependent;
import pe.gob.mpfn.core.logging.MpfnLogger;
import pe.gob.mpfn.jsf.core.menu.UserInfo;
import pe.gob.mpfn.jsf.core.menu.controller.MenuProducerController;
import pe.gob.mpfn.menu.model.Menu;


/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class MenuFilter.
 *
 * @author OSIS
 * @version 1.0 , 20/07/2016
 */
@WebFilter("/*")
public class MenuFilter implements Filter {
	
	/** La log. */
	@Inject
	MpfnLogger log;
	
	/** La user menu. */
	@Inject
	Instance<UserInfo> userMenu;
	
	/** La module name. */
	@Resource(lookup="java:module/ModuleName")
	String moduleName;
	
	/** La service user menu url. */
	@Inject @StageDependent
	String serviceUserMenuUrl;
	
	/** La menu producer. */
	@Inject
	MenuProducerController menuProducer;

    /**
     * Default constructor. 
     */
    public MenuFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * Destroy.
	 *
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * Do filter.
	 *
	 * @param request el request
	 * @param response el response
	 * @param chain el chain
	 * @throws IOException Señales de que una excepción de E / S se ha producido.
	 * @throws ServletException the servlet exception
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if(req.getServletPath().endsWith("xhtml") && Beans.getInstance(UserInfo.class, false) == null){
			log.debug("path:"+req.getServletPath()+" No hay session creada se pedira menu de aplicacion");
			KeycloakPrincipal<KeycloakSecurityContext> keyCloak = (KeycloakPrincipal<KeycloakSecurityContext>) req.getUserPrincipal();
			if(keyCloak != null){
				Client jaxrsClient =  new ResteasyClientBuilder()
		                .establishConnectionTimeout(2, TimeUnit.SECONDS)
		                .build();
				String userName=keyCloak.getKeycloakSecurityContext().getIdToken().getPreferredUsername();
				//format url service menu is
				// http://portalprueba.mpfn.gob.pe/sad/opciones/l/{user}/s/{app}
				Invocation invocation = jaxrsClient.target(serviceUserMenuUrl+userName+"/s/"+moduleName).request(MediaType.APPLICATION_JSON).buildGet();
				try{
				String menu = invocation.invoke(String.class);
				Menu menuUser = new Gson().fromJson(menu, Menu.class);
				menuProducer.setMenu(menuUser, keyCloak);
				} catch(Exception ex){
					log.error("Error procesando menu :"+ex.getMessage());
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * Inits the.
	 *
	 * @param fConfig el f config
	 * @throws ServletException the servlet exception
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
