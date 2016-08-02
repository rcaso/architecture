package pe.gob.mpfn.menu.model;

import java.io.Serializable;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class MenuOption.
 *
 * @author OSIS
 * @version 1.0 , 15/07/2016
 */
public class MenuOption implements Serializable, Comparable<MenuOption> {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -3711751282086234586L;
	
	
	/** La url. */
	private String url;

	/** La name. */
	private String name;
	
	/** La id menu option. */
	private Integer idMenuOption;
	
	/** La alias. */
	private String alias;
	
	/** La order option. */
	private Integer orderOption;
	
	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MenuOption menuOption) {
		// TODO Auto-generated method stub
		return this.orderOption.compareTo(menuOption.getOrderOption());
	}



	/**
	 * Obtiene url.
	 *
	 * @return url
	 */
	public String getUrl() {
		return url;
	}



	/**
	 * Establece el url.
	 *
	 * @param url el new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}



	/**
	 * Obtiene name.
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}



	/**
	 * Sets the name.
	 *
	 * @param name el name
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * Obtiene id menu option.
	 *
	 * @return id menu option
	 */
	public Integer getIdMenuOption() {
		return idMenuOption;
	}



	/**
	 * Establece el id menu option.
	 *
	 * @param idMenuOption el new id menu option
	 */
	public void setIdMenuOption(Integer idMenuOption) {
		this.idMenuOption = idMenuOption;
	}



	/**
	 * Obtiene alias.
	 *
	 * @return alias
	 */
	public String getAlias() {
		return alias;
	}



	/**
	 * Establece el alias.
	 *
	 * @param alias el new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}



	/**
	 * Obtiene order option.
	 *
	 * @return order option
	 */
	public Integer getOrderOption() {
		return orderOption;
	}



	/**
	 * Establece el order option.
	 *
	 * @param orderOption el new order option
	 */
	public void setOrderOption(Integer orderOption) {
		this.orderOption = orderOption;
	}

}
