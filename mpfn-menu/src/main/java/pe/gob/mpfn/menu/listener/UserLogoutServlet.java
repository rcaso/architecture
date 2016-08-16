package pe.gob.mpfn.menu.listener;

import java.io.IOException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.gob.mpfn.core.configuration.Configurable;
import pe.gob.mpfn.core.configuration.StageDependent;


/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class UserLogoutServlet.
 *
 * @author OSIS
 * @version 1.0 , 20/07/2016
 */
@WebServlet("/userLogout")
public class UserLogoutServlet extends HttpServlet {
	
	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La module name. */
	@Resource(lookup="java:module/ModuleName")
	private String moduleName;
	
	/** La service user menu url. */
	@Inject @StageDependent
	private String serviceUserMenuUrl;
	
	/** La url index. */
	@Inject
	@Configurable
	private String urlIndex;
	
	
       
    /**
     * Instancia un nuevo user logout servlet.
     *
     * @see HttpServlet#HttpServlet()
     */
    public UserLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Do get.
	 *
	 * @param request el request
	 * @param response el response
	 * @throws ServletException the servlet exception
	 * @throws IOException Señales de que una excepción de E / S se ha producido.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Do post.
	 *
	 * @param request el request
	 * @param response el response
	 * @throws ServletException the servlet exception
	 * @throws IOException Señales de que una excepción de E / S se ha producido.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	/**
	 * Process request.
	 *
	 * @param request el request
	 * @param response el response
	 * @throws ServletException the servlet exception
	 * @throws IOException Señales de que una excepción de E / S se ha producido.
	 */
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.logout();
		request.getSession().invalidate();
		response.sendRedirect("/"+moduleName+"/"+urlIndex);
	}

}
