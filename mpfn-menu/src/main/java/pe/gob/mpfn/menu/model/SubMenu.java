package pe.gob.mpfn.menu.model;

import java.io.Serializable;
import java.util.List;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class SubMenu.
 *
 * @author OSIS
 * @version 1.0 , 15/07/2016
 */
public class SubMenu implements Serializable, Comparable<SubMenu> {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -1264434713627911709L;
	
	/** La options. */
	private List<MenuOption> options;
	
	/** La sub menus. */
	private List<SubMenu> subMenus;
	
	/** La name. */
	private String name;
	
	/** La order option. */
	private Integer orderOption;
	
	/** La id menu option. */
	private Integer idMenuOption;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(SubMenu subMenu) {
		// TODO Auto-generated method stub
		return this.orderOption.compareTo(subMenu.getOrderOption());
	}

	/**
	 * Obtiene options.
	 *
	 * @return options
	 */
	public List<MenuOption> getOptions() {
		return options;
	}

	/**
	 * Establece el options.
	 *
	 * @param options el new options
	 */
	public void setOptions(List<MenuOption> options) {
		this.options = options;
	}

	/**
	 * Obtiene sub menus.
	 *
	 * @return sub menus
	 */
	public List<SubMenu> getSubMenus() {
		return subMenus;
	}

	/**
	 * Establece el sub menus.
	 *
	 * @param subMenus el new sub menus
	 */
	public void setSubMenus(List<SubMenu> subMenus) {
		this.subMenus = subMenus;
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

}
