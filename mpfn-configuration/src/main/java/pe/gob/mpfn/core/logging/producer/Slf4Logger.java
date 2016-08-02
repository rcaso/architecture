package pe.gob.mpfn.core.logging.producer;

import org.slf4j.Logger;

import pe.gob.mpfn.core.logging.MpfnLogger;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class Slf4Logger.
 *
 * @author OSIS
 * @version 1.0 , 15/04/2016
 */
public class Slf4Logger implements MpfnLogger {
	
	/** La logger. */
	private Logger logger; 

	/**
	 * Instancia un nuevo slf4 logger.
	 *
	 * @param logger el logger
	 */
	public Slf4Logger(Logger logger) {
		this.logger = logger;
	}

	/* (non-Javadoc)
	 * @see pe.gob.mpfn.core.logging.MpfnLogger#debug(java.lang.String)
	 */
	@Override
	public void debug(String mensaje) {
		logger.debug(mensaje);
	}

	/* (non-Javadoc)
	 * @see pe.gob.mpfn.core.logging.MpfnLogger#info(java.lang.String)
	 */
	@Override
	public void info(String mensaje) {
		logger.info(mensaje);
		
	}

	/* (non-Javadoc)
	 * @see pe.gob.mpfn.core.logging.MpfnLogger#warn(java.lang.String)
	 */
	@Override
	public void warn(String mensaje) {
		logger.warn(mensaje);
	}

	/* (non-Javadoc)
	 * @see pe.gob.mpfn.core.logging.MpfnLogger#error(java.lang.String)
	 */
	@Override
	public void error(String mensaje) {
		logger.error(mensaje);
	}

}
