package pe.gob.mpfn.security.authorization.type;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Enum HeaderValuesType.
 *
 * @author OSIS
 * @version 1.0 , 21/04/2016
 */
public enum HeaderValuesType {
	
	/** La cause. */
	CAUSE("cause"),
	AUTHORIZATION("Authorization"),
	BEARER("Bearer");
	
	/** La value. */
	private String value;
	
	/**
	 * Instancia un nuevo header values type.
	 *
	 * @param value el value
	 */
	private HeaderValuesType(String value){
		this.value=value;
	}
	
	/**
	 * Obtiene value.
	 *
	 * @return value
	 */
	public String getValue(){
		return this.value;
	}
}
