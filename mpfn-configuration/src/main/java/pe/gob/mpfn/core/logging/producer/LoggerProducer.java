package pe.gob.mpfn.core.logging.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.mpfn.core.logging.MpfnLogger;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class LoggerProducer.
 *
 * @author OSIS
 * @version 1.0 , 15/04/2016
 */
@ApplicationScoped
public class LoggerProducer {
	
	/**
	 * Obtiene logger.
	 *
	 * @param ip el ip
	 * @return logger
	 */
	@Produces
	public MpfnLogger getLogger(InjectionPoint ip) {
		Class<?> aClass = ip.getMember().getDeclaringClass();
        Logger logger = LoggerFactory.getLogger(aClass.getName());
        return new Slf4Logger(logger);
	}
}
