package pe.gob.mpfn.security.audit.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class UserTrack.
 *
 * @author OSIS
 * @version 1.0 , 08/04/2016
 */
public class UserTrack implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 5057887879503553647L;
	
	/** La user name. */
	private String userName;
	
	/** La ip address. */
	private String ipAddress;
	
	/** La audit time. */
	private LocalDateTime auditTime;
	
	/** La menu option. */
	private String menuOption;

	/**
	 * Obtiene user name.
	 *
	 * @return user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Establece el user name.
	 *
	 * @param userName el new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Obtiene ip address.
	 *
	 * @return ip address
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * Establece el ip address.
	 *
	 * @param ipAddress el new ip address
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Obtiene audit time.
	 *
	 * @return audit time
	 */
	public LocalDateTime getAuditTime() {
		return auditTime;
	}

	/**
	 * Establece el audit time.
	 *
	 * @param auditTime el new audit time
	 */
	public void setAuditTime(LocalDateTime auditTime) {
		this.auditTime = auditTime;
	}

	/**
	 * Obtiene menu option.
	 *
	 * @return menu option
	 */
	public String getMenuOption() {
		return menuOption;
	}

	/**
	 * Establece el menu option.
	 *
	 * @param menuOption el new menu option
	 */
	public void setMenuOption(String menuOption) {
		this.menuOption = menuOption;
	}

}
