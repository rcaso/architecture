package pe.gob.mpfn.core.configuration.producer;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import pe.gob.mpfn.core.configuration.Configurable;
import pe.gob.mpfn.core.configuration.FilesConfiguration;
import pe.gob.mpfn.core.configuration.type.StageApplication;
import pe.gob.mpfn.core.logging.MpfnLogger;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class StageProducer.
 *
 * @author OSIS
 * @version 1.0 , 18/07/2016
 */
@ApplicationScoped
public class StageProducer implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 6348463581994618847L;
	
	/** La log. */
	@Inject
	MpfnLogger log;
	
	/** La configuration file. */
	private Properties configurationFile;

	/**
	 * Instancia un nuevo stage producer.
	 */
	public StageProducer() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		try(InputStream is = Configurable.class.getResourceAsStream("/"+FilesConfiguration.FILE_NAME_STAGE)) {
			configurationFile=new Properties();
			configurationFile.load(is);
		} catch(IOException iox) {
			log.info(iox.getMessage());
		}
	}
	
	/**
	 * Obtiene stage.
	 *
	 * @return stage
	 */
	@Produces
	public StageApplication getStage() {
		return StageApplication.valueOf(configurationFile.getProperty("app.stage.key", "Production"));
	}

}
