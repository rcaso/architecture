package pe.gob.mpfn.core.configuration.producer;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import pe.gob.mpfn.core.configuration.Configurable;
import pe.gob.mpfn.core.configuration.FilesConfiguration;
import pe.gob.mpfn.core.configuration.StageDependent;
import pe.gob.mpfn.core.configuration.type.StageApplication;
import pe.gob.mpfn.core.logging.MpfnLogger;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class StageParameterProducer.
 *
 * @author OSIS
 * @version 1.0 , 18/07/2016
 */
@ApplicationScoped
public class StageParameterProducer implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 8703349689214383474L;
	
	/** La log. */
	@Inject
	MpfnLogger log;
	
	/** La configuration file. */
	private Properties configurationFile;

	/**
	 * Instancia un nuevo stage parameter producer.
	 */
	public StageParameterProducer() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		try(InputStream is = Configurable.class.getResourceAsStream("/"+FilesConfiguration.FILE_NAME_STAGE_CONFIGURATION)) {
			configurationFile=new Properties();
			configurationFile.load(is);
		} catch(IOException iox) {
			log.error(iox.getMessage());
		}
	}
	
	/**
	 * Obtiene parameter stage.
	 *
	 * @param point el point
	 * @param stage el stage
	 * @return parameter stage
	 */
	@Produces @StageDependent
	public String getParameterStage(InjectionPoint point, StageApplication stage) {
		String fieldName = getNameField(point);
		fieldName = stage.name() + "." + fieldName;
		return getValueForKey(fieldName);
	}
	
	/**
	 * Obtiene name field.
	 *
	 * @param point el point
	 * @return name field
	 */
	private String getNameField(InjectionPoint point) {
		return point.getMember().getName();
	}
	
	/**
	 * Obtiene value for key.
	 *
	 * @param fieldName el field name
	 * @return value for key
	 */
	private String getValueForKey(String fieldName) {
		return configurationFile.getProperty(fieldName);
	}

}
