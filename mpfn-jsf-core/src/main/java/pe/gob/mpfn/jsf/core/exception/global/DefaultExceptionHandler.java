package pe.gob.mpfn.jsf.core.exception.global;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;



/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class DefaultExceptionHandler.
 *
 * @author OSIS
 * @version 1.0 , 23/05/2016
 */
public class DefaultExceptionHandler extends ExceptionHandlerWrapper {

	/** The log. */
	private Logger log = Logger.getLogger(DefaultExceptionHandler.class.getCanonicalName());
	
	/** key for session scoped message detail. */
	public static final String MESSAGE_DETAIL_KEY = "ip.client.jsftoolkit.messageDetail";

	/** The wrapped. */
	private ExceptionHandler wrapped;

	/**
	 * Instantiates a new default exception handler.
	 *
	 * @param wrapped the wrapped
	 */
	public DefaultExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	/* (non-Javadoc)
	 * @see javax.faces.context.ExceptionHandlerWrapper#getWrapped()
	 */
	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	/* (non-Javadoc)
	 * @see javax.faces.context.ExceptionHandlerWrapper#handle()
	 */
	@Override
	public void handle() throws FacesException {
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			Throwable throwable = context.getException();
//			context.getException().printStackTrace();
			log.log(Level.SEVERE,ExceptionUtils.getStackTrace(throwable));
			FacesContext facesContext = FacesContext.getCurrentInstance();
			//call methods clean session and register in DB
			closeSession(facesContext);
			try {
				if (throwable instanceof ViewExpiredException) {
					ViewExpiredException vee = (ViewExpiredException) throwable;
					log.log(Level.SEVERE,"Error expired session in page " + vee.getViewId());
					redirectError(facesContext, "redirectSessionError.xhtml");
				} else {
					// TODO if in future is necesary other way to handler add
					// else if XxxxxException
					// to example ContextNotActiveException
					log.log(Level.SEVERE,"exception",throwable);
					if(context.getComponent() != null){
						log.log(Level.SEVERE,context.getComponent().getFamily() +" :"+context.getComponent().getClientId());
					}
					redirectError(facesContext, "redirectError.xhtml");
				}
			} finally {
				i.remove();
			}

		}
		// At this point, the queue will not contain any ViewExpiredEvents.
		// Therefore, let the parent handle them.
		getWrapped().handle();

	}

	/**
	 * Redirect error page.
	 *
	 * @param facesContext the faces context
	 * @param errorPage the error page
	 */
	private void redirectError(FacesContext facesContext, String errorPage){
		StringBuilder sbJScript = new StringBuilder("");
		if (facesContext.getPartialViewContext().isAjaxRequest()) {
			try {
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
			PrintWriter out = response.getWriter();
			sbJScript.append("/error/");
			sbJScript.append(errorPage);
			sbJScript.append("?page=");
			sbJScript.append("error.xhtml");
//			out.write(JSFUtilities.getXmlAjaxRedirect(sbJScript.toString()));
			facesContext.responseComplete();
			} catch (IOException ioex) {
				log.log(Level.SEVERE,ioex.getMessage());
			}
		} else {
			try {
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			sbJScript.append("window.top.location.href='");
		    sbJScript.append("/error/");
		    sbJScript.append("error.xhtml");
			sbJScript.append("'");
			out.write("<html>");
			out.write("<script>");
			out.write(sbJScript.toString());
			out.write("</script>");
			out.write("</html>");
			facesContext.responseComplete();
			} catch (IOException ioex) {
				log.log(Level.SEVERE,ioex.getMessage());
			}
		}
	}

	/**
	 * Close session.
	 *
	 * @param facesContext el faces context
	 */
	private void closeSession(FacesContext facesContext) {

	}

	/**
	 * Handle unexpected.
	 *
	 * @param facesContext the faces context
	 * @param t the t
	 * @return the string
	 */
	protected String handleUnexpected(FacesContext facesContext,
			final Throwable t) {
		log.log(Level.SEVERE,"An unexpected internal error has occurred");
		return "jsftoolkit.exception.UncheckedException";
	}
}
