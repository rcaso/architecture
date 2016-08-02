package pe.gob.mpfn.menu.model;

import java.io.Serializable;
import java.util.List;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class Menu.
 *
 * @author OSIS
 * @version 1.0 , 15/07/2016
 */
public class Menu implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = -4818385039128537308L;

	/** La sub menus. */
	private List<SubMenu> subMenus;
	
	/** La nombre. */
	private String nombre;

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
	 * Obtiene nombre.
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre el nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
