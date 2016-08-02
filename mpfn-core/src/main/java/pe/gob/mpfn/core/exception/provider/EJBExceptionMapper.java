package pe.gob.mpfn.core.exception.provider;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import pe.gob.mpfn.core.logging.MpfnLogger;
import pe.gob.mpfn.security.authorization.type.HeaderValuesType;


/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class EJBExceptionMapper.
 *
 * @author OSIS
 * @version 1.0 , 20/04/2016
 */
@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {
	
	@Inject
	MpfnLogger log;

	/* (non-Javadoc)
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
    public Response toResponse(EJBException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof OptimisticLockException) {
            OptimisticLockException actual = (OptimisticLockException) cause;
            log.error(actual.getMessage() + actual.getEntity()!=null?actual.getEntity().toString():"");
            return Response.status(Response.Status.CONFLICT).
                    header(HeaderValuesType.CAUSE.getValue(), "El registro ya fue procesado : ").
//                    header("additional-info", actual.getMessage()).
                    build();

        } else if(cause instanceof EntityNotFoundException){
        	EntityNotFoundException actual = (EntityNotFoundException)cause;
        	log.error(actual.getMessage());
        	return Response.status(Response.Status.NOT_FOUND).
        			header(HeaderValuesType.CAUSE.getValue(), "Entidad no se encuentra registrada ").build();
        }
        else {
            //Otra exception EJB
        	log.error(cause.getMessage());
        	log.error(ex.toString());
        	return Response.serverError().
                    header(HeaderValuesType.CAUSE.getValue(),"Un error inesperado ha ocurrido en el servidor").build();
        }
    }

}
