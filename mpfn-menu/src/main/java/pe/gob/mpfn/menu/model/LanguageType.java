package pe.gob.mpfn.menu.model;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Enum LanguageType.
 *
 * @author OSIS
 * @version 1.0 , 15/07/2016
 */
public enum LanguageType {
	
	/** La spanish. */
	SPANISH("es"),
	
	/** The english. */
	ENGLISH("en");
	
	/** La value. */
	private String value;
	
	/**
	 * Instancia un nuevo language type.
	 *
	 * @param value el value
	 */
	private LanguageType(String value) {
		this.value = value;
	}
	
	/**
	 * Obtiene value.
	 *
	 * @return value
	 */
	public String getValue() {
		return value;
	}
	
	
}
