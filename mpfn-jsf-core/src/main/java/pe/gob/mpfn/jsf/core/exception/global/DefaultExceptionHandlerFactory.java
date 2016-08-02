package pe.gob.mpfn.jsf.core.exception.global;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;



/**
 * A factory for creating DefaultExceptionHandler objects.
 */
public class DefaultExceptionHandlerFactory extends ExceptionHandlerFactory {


	/** The parent. */
	private ExceptionHandlerFactory parent;

	/**
	 * Instantiates a new default exception handler factory.
	 *
	 * @param parent the parent
	 */
	public DefaultExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
	 */
	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler eh = parent.getExceptionHandler();
		eh = new DefaultExceptionHandler(eh);
		return eh;
	}
}
