package pe.gob.mpfn.core.configuration.producer;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import pe.gob.mpfn.core.configuration.Configurable;
import pe.gob.mpfn.core.configuration.FilesConfiguration;
import pe.gob.mpfn.core.logging.MpfnLogger;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ConfigurableParameterProducer.
 *
 * @author OSIS
 * @version 1.0 , 15/04/2016
 */
@ApplicationScoped
public class ConfigurableParameterProducer implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 3854336594189260914L;
	
	/** La log. */
	@Inject
	MpfnLogger log;

	/** La configuration file. */
	private Properties configurationFile;

	/**
	 * Instancia un nuevo configurable parameter producer.
	 */
	public ConfigurableParameterProducer() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		try(InputStream is = Configurable.class.getResourceAsStream("/"+FilesConfiguration.FILE_NAME_CONFIGURATION)) {
			configurationFile=new Properties();
			configurationFile.load(is);
		} catch(IOException iox) {
			log.info(iox.getMessage());
		}
	}
	
	/**
	 * Obtiene parameter string.
	 *
	 * @param point el point
	 * @return parameter string
	 */
	@Produces @Configurable
	public String getParameterString(InjectionPoint point){
		return getConfigurableNameField(point);
	}
	
	/**
	 * Obtiene parameter integer.
	 *
	 * @param point el point
	 * @return parameter integer
	 */
	@Produces @Configurable
	public Integer getParameterInteger(InjectionPoint point) {
		String value = getConfigurableNameField(point);
		return (value != null) ? Integer.valueOf(value) : null;
	}
	
	/**
	 * Obtiene parameter long.
	 *
	 * @param point el point
	 * @return parameter long
	 */
	@Produces @Configurable
	public Long getParameterLong(InjectionPoint point) {
		String value = getConfigurableNameField(point);
		return (value != null) ? Long.valueOf(value) : null;
	}
	
	/**
	 * Obtiene parameter boolean.
	 *
	 * @param point el point
	 * @return parameter boolean
	 */
	@Produces @Configurable
	public boolean getParameterBoolean(InjectionPoint point){
		String value = getConfigurableNameField(point);
		return (value != null) ? Boolean.parseBoolean(value) : false;
	}
	
	/**
	 * Obtiene configurable name field.
	 *
	 * @param point el point
	 * @return configurable name field
	 */
	private String getConfigurableNameField(InjectionPoint point) {
		AnnotatedField<Configurable> field = (AnnotatedField)point.getAnnotated();
		Configurable configurable = field.getAnnotation(Configurable.class);
		String value = null;
		if (configurable != null) {
			if (configurable.value()!= null && !configurable.value().isEmpty()) {
				value = getValueForKey(configurable.value());
			} else {
				value = getValueForKey(getNameField(point));
			}
		}
		return value;
	}
	
	/**
	 * Gets the name field.
	 *
	 * @param point the point
	 * @return the name field
	 */
	private String getNameField(InjectionPoint point) {
		return point.getMember().getName();
	}
	
	/**
	 * Gets the value for key.
	 *
	 * @param fieldName the field name
	 * @return the value for key
	 */
	private String getValueForKey(String fieldName) {
		return configurationFile.getProperty(fieldName);
	}

}
