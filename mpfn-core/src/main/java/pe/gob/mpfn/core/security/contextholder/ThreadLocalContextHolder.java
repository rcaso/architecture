package pe.gob.mpfn.core.security.contextholder;

import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class ThreadLocalContextHolder.
 *
 * @author OSIS
 * @version 1.0 , 21/04/2016
 */
public class ThreadLocalContextHolder {
	
	/** La Constante THREAD_WITH_CONTEXT. */
	private static final ThreadLocal<Map<String,Object>> THREAD_WITH_CONTEXT = new ThreadLocal<Map<String,Object>>();
	
	/**
	 * Put.
	 *
	 * @param key el key
	 * @param payload el payload
	 */
	public static void put(String key, Object payload) {
        if(THREAD_WITH_CONTEXT.get() == null){
            THREAD_WITH_CONTEXT.set(new HashMap<String, Object>());
        }
        THREAD_WITH_CONTEXT.get().put(key, payload);
    }

    /**
     * Gets the.
     *
     * @param key the key
     * @return the object
     */
    public static Object get(String key) {
        return THREAD_WITH_CONTEXT.get().get(key);
    }

    /**
     * Cleanup thread.
     */
    public static void cleanupThread(){
        THREAD_WITH_CONTEXT.remove();
    }
    
    /**
     * Check initialited.
     *
     * @return true, if successful
     */
    public static boolean checkInitialited() {
    	return THREAD_WITH_CONTEXT.get() != null;
    }

	/**
	 * Instantiates a new thread local context holder.
	 */
	private ThreadLocalContextHolder() {
		// TODO Auto-generated constructor stub
	}
}
