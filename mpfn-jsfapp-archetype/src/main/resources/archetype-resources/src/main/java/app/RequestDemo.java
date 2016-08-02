package ${package}.app;

import javax.enterprise.context.RequestScoped;
import java.time.LocalDateTime;
import javax.inject.Named;


/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class RequestDemo.
 * 
 * @author OSIS
 * @version 1.0 , 21/07/2016
 */
@RequestScoped
@Named
public class RequestDemo {
	
	/**
	 * Instancia un nuevo producer data base.
	 */
	public RequestDemo() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Obtiene time now.
	 *
	 * @return time now
	 */
	public LocalDateTime getTimeNow(){
		return LocalDateTime.now();
	}

}
