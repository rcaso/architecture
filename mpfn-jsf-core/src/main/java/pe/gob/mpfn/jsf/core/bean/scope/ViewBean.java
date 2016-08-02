package pe.gob.mpfn.jsf.core.bean.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Interface ViewBean.
 *
 * @author OSIS
 * @version 1.0 , 23/05/2016
 */
@Stereotype
@Inherited
@ViewScoped
@Named
@Target({ TYPE })
@Retention(RUNTIME)
@Documented
public @interface ViewBean {

}
