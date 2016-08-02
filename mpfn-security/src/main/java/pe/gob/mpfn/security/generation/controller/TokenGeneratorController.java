package pe.gob.mpfn.security.generation.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import pe.gob.mpfn.security.configuration.SignatureParameter;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class TokenGenerationController.
 *
 * @author OSIS
 * @version 1.0 , 22/04/2016
 */
@ApplicationScoped
public class TokenGeneratorController implements Serializable {

	/** La Constante serialVersionUID. */
	private static final long serialVersionUID = 1258988546234841487L;
	
	/** La Constante log. */
	private static  final Logger log = Logger.getLogger(TokenGeneratorController.class.getName());
	
	/**
	 * Generate token.
	 *
	 * @param parameters el parameters
	 * @return the string
	 */
	public String generateToken(Map<String, Object> parameters){
		JwtBuilder builder = Jwts.builder().setClaims(parameters)
										.setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.of("GMT-5")).toInstant()))
		                                .setSubject((String)parameters.get(SignatureParameter.getInstance().getUserDNI()))
		                                .setIssuer(SignatureParameter.getInstance().getIssuerSignature())
		                                .signWith(SignatureAlgorithm.HS256, SignatureParameter.getInstance().getKeySignature());
		/* token expiracion
		if (duracionMilisegundos >= 0) {
		    long expiracionMilisegundos = ahoraMilisegundos + duracionMilisegundos;
		    Date exp = new Date(expiracionMilisegundos);
		    builder.setExpiration(exp);
		}
		 */
		return builder.compact();
	}
	
	/**
	 * Obtiene pay load token.
	 *
	 * @param token el token
	 * @return pay load token
	 */
	public Claims getPayLoadToken(String token){
		Claims claims = null;
		try {
            claims = Jwts.parser().setSigningKey(SignatureParameter.getInstance().getKeySignature()).parseClaimsJws(token).getBody();
        }catch (final SignatureException e) {
            log.log(Level.SEVERE,"Error token no pudo ser descifrado");
        } catch (MalformedJwtException me) {
        	log.log(Level.SEVERE,"Error formato de token no valido");
		}
		return claims;
	}

}
