package pe.gob.mpfn.security.configuration;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class SignatureParameter.
 *
 * @author OSIS
 * @version 1.0 , 22/04/2016
 */
public class SignatureParameter {
	
	/** La log. */
	private static Logger LOG = Logger.getLogger(SignatureParameter.class.getName());
	
	/** La issuer signature. */
	private String issuerSignature;
	
	/** La key signature. */
	private String keySignature;
	
	/** La user name. */
	private String userName="nombre";
	
	/** La user dni. */
	private String userDNI="dni";
	
	/** La user ip. */
	private String userIP="ip";
	
	/** La signature parameter. */
	private static SignatureParameter signatureParameter;
	
	/**
	 * Obtiene la instancia de SignatureParameter.
	 *
	 * @return instancia de SignatureParameter
	 */
	public static SignatureParameter getInstance(){
		if(signatureParameter==null){
			signatureParameter = new SignatureParameter();
		}
		return signatureParameter;
	}
	
	/**
	 * Instancia un nuevo signature parameter.
	 */
	private SignatureParameter(){
		try(InputStream file = SignatureParameter.class.getResourceAsStream("/signature.properties")){
			Properties keys=new Properties();
			keys.load(file);
			issuerSignature=keys.getProperty("issuer.signature");
			keySignature=keys.getProperty("key.signature");
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "No pudo cargar configuracion del cifrado Json");
		}
	}
	
	/**
	 * Obtiene issuer signature.
	 *
	 * @return issuer signature
	 */
	public String getIssuerSignature() {
		return issuerSignature;
	}

	/**
	 * Obtiene key signature.
	 *
	 * @return key signature
	 */
	public String getKeySignature() {
		return keySignature;
	}

	/**
	 * Obtiene user name.
	 *
	 * @return user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Obtiene user dni.
	 *
	 * @return user dni
	 */
	public String getUserDNI() {
		return userDNI;
	}

	/**
	 * Obtiene user ip.
	 *
	 * @return user ip
	 */
	public String getUserIP() {
		return userIP;
	}
	
}
