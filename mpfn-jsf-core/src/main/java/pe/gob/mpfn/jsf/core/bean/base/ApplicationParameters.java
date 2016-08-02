package pe.gob.mpfn.jsf.core.bean.base;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.gob.mpfn.core.configuration.Configurable;

@Named
@ApplicationScoped
public class ApplicationParameters implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4398059015825404504L;
	
	@Inject @Configurable
	private String urlIndex;
	

	public ApplicationParameters() {
		// TODO Auto-generated constructor stub
	}


	public String getUrlIndex() {
		return urlIndex;
	}

}
