package pe.gob.mpfn.jsf.core.menu.controller;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.IDToken;

import pe.gob.mpfn.core.logging.MpfnLogger;
import pe.gob.mpfn.jsf.core.menu.UserInfo;
import pe.gob.mpfn.menu.model.Menu;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class MenuProducerController.
 *
 * @author OSIS
 * @version 1.0 , 18/07/2016
 */
@RequestScoped
public class MenuProducerController {
	
	/** La user menu. */
	@Inject
	Instance<UserInfo> userMenu;
	
	/** La request. */
	@Inject
    HttpServletRequest request;
	
	/** La menu separator. */
//	private String menuSeparator="-";
	
	/** La log. */
	@Inject
	MpfnLogger log;

	/**
	 * Instancia un nuevo menu producer controller.
	 */
	public MenuProducerController() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Sets the menu.
	 *
	 * @param menu el menu
	 * @param keyCloak el key cloak
	 */
	public void setMenu(Menu menu,KeycloakPrincipal<KeycloakSecurityContext> keyCloak ){
		UserInfo currentMenu = userMenu.get();
		currentMenu.setMenuModel(menu);
		IDToken idToken = keyCloak.getKeycloakSecurityContext().getIdToken();
		//Para Pruebas
		currentMenu.setUserName(idToken.getPreferredUsername());
		currentMenu.setFullName(idToken.getFamilyName()+", "+idToken.getGivenName());
		currentMenu.setIp(idToken.getOtherClaims().get("clientAddress")!=null?idToken.getOtherClaims().get("clientAddress")+"":request.getRemoteAddr());
		currentMenu.setLastLogin(LocalDateTime.now());
	}
	
	/**
	 * Show menu.
	 * to generate menu primefaces 
	 *
	 * @param menu el menu
	 * @return the menu model
	 
 	public void showMenu(Menu menu) {
		try{
			MenuModel menuPrincipal = null;
			if (menu != null) {
				menuPrincipal = new DefaultMenuModel();
				for(SubMenu subMenus : menu.getSubMenus()) {
					DefaultSubMenu subMenuPrime = new DefaultSubMenu();
					subMenuPrime.setLabel(subMenus.getName());
					if(subMenus.getIdMenuOption() != null){
						subMenuPrime.setId("idSubMenu" + subMenus.getIdMenuOption());
					}
					menuPrincipal.addElement(subMenuPrime);
					//Childrens
					//submenus
					if (subMenus.getSubMenus() != null && subMenus.getSubMenus().size() > 0) {
						for(SubMenu subMenusChildrens: subMenus.getSubMenus()) {
							DefaultSubMenu subMenuChildren = new DefaultSubMenu();
							subMenuChildren.setLabel(subMenusChildrens.getName());
							if(subMenusChildrens.getIdMenuOption() != null){
								subMenuChildren.setId("idSubMenuChild" + subMenusChildrens.getIdMenuOption());
							}
							subMenuPrime.addElement(subMenuChildren);
							//recursive Childrens
							getChildren(subMenuChildren, subMenusChildrens);
						}
					}
					for (MenuOption menuOption : subMenus.getOptions()) {
						DefaultMenuItem menuItem = new DefaultMenuItem(menuOption.getName());
						if(menuOption.getIdMenuOption() != null){
							menuItem.setId("idMenuItem" + menuOption.getIdMenuOption());
						}
						String url = menuOption.getUrl();
						if(!url.equals(menuSeparator)) {
							menuItem.setOutcome(url.replaceAll(".xhtml",""));
							menuItem.setIcon("ui-icon-seek-next");
						}else {
							menuItem.setIcon("ui-icon-folder-collapsed");
						}
						menuItem.setAjax(false);						
						subMenuPrime.addElement(menuItem);
					}
				}
			}
			UserMenuBean currentMenu = userMenu.get();
			currentMenu.setMenu(menuPrincipal);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}
	
	*/

	/**
	 * Gets the children.
	 *
	 * @param subMenu the sub menu
	 * @param modelSubMenu the arkin sub menu
	 * @return the children
	 * @throws Exception the exception
	
 	
	private void getChildren(DefaultSubMenu subMenu,SubMenu modelSubMenu) throws Exception {
		//options
		for(MenuOption opcionChildren : modelSubMenu.getOptions()) {
			DefaultMenuItem menuItem = new DefaultMenuItem();
			menuItem.setValue(opcionChildren.getName());
			if(opcionChildren.getIdMenuOption() != null){
				menuItem.setId("idMenuItem" + opcionChildren.getIdMenuOption());
			}
			String url = opcionChildren.getUrl();
			if(!url.equals(menuSeparator)) {
			menuItem.setOutcome(url.replaceAll(".xhtml",""));
			menuItem.setIcon("ui-icon-seek-next");
			}else{
				menuItem.setIcon("ui-icon-folder-collapsed");
			}
			menuItem.setAjax(false);						
			subMenu.addElement(menuItem);
		}
		if (modelSubMenu.getSubMenus() != null) {
			for(SubMenu menuChildrenArkin : modelSubMenu.getSubMenus()) {
				//submenus
				DefaultSubMenu subMenuChildren = new DefaultSubMenu();
				subMenuChildren.setLabel(menuChildrenArkin.getName());
				if(menuChildrenArkin.getIdMenuOption() != null){
					subMenuChildren.setId("idSubMenuChild" + menuChildrenArkin.getIdMenuOption());
				}
				subMenu.addElement(subMenuChildren);
				getChildren(subMenuChildren, menuChildrenArkin);
			}
		}
	}
	 */
}
