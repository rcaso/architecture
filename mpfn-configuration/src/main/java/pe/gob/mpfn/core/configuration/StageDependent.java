package pe.gob.mpfn.core.configuration;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Interface StageDependent.
 *
 * @author OSIS
 * @version 1.0 , 18/07/2016
 */
@Qualifier
@Inherited
@Target({PARAMETER, FIELD,METHOD })
@Retention(RUNTIME)
@Documented
public @interface StageDependent {

}
