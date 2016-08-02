package pe.gob.mpfn.jsf.core.menu;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import pe.gob.mpfn.menu.model.Menu;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class UserMenuBean.
 *
 * @author OSIS
 * @version 1.0 , 18/07/2016
 */
@Named
@SessionScoped
public class UserInfo implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 3335930349054241903L;
	
	/** La menu model. */
	private Menu menuModel;
	
	/** La user name. */
	private String userName;
	
	/** La full name. */
	private String fullName;
	
	/** La ip. */
	private String ip;
	
	
	/** La last login. */
	private LocalDateTime lastLogin;
	
	/** La last login description. */
	private String lastLoginDescription;

	/**
	 * Instancia un nuevo user menu bean.
	 */
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Obtiene menu model.
	 *
	 * @return menu model
	 */
	public Menu getMenuModel() {
		return menuModel;
	}

	/**
	 * Establece el menu model.
	 *
	 * @param menuModel el new menu model
	 */
	public void setMenuModel(Menu menuModel) {
		this.menuModel = menuModel;
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
	 * Establece el user name.
	 *
	 * @param userName el new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Obtiene full name.
	 *
	 * @return full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Establece el full name.
	 *
	 * @param fullName el new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Obtiene ip.
	 *
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Establece el ip.
	 *
	 * @param ip el new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	
	/**
	 * Obtiene last login.
	 *
	 * @return last login
	 */
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	
	/**
	 * Establece el last login.
	 *
	 * @param lastLogin el new last login
	 */
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("h:mm:ss a");
		lastLoginDescription = lastLogin.format(dtFormat);
	}

	/**
	 * Obtiene last login description.
	 *
	 * @return last login description
	 */
	public String getLastLoginDescription() {
		return lastLoginDescription;
	}

}
