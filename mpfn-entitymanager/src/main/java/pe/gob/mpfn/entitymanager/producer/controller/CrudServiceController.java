package pe.gob.mpfn.entitymanager.producer.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.transaction.TransactionSynchronizationRegistry;

import pe.gob.mpfn.entitymanager.audit.Auditable;
import pe.gob.mpfn.entitymanager.exception.BusinessException;
import pe.gob.mpfn.entitymanager.producer.DbApplication;
import pe.gob.mpfn.security.audit.RegistryContextHolder;
import pe.gob.mpfn.security.audit.entity.UserTrack;



// TODO: Auto-generated Javadoc
/**
 * <ul>
 * <li>Copyright 2016 Ministerio Publico - Fiscalia de la Nacion. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class CrudServiceController.
 *
 * @author OSIS
 * @version 1.0 , 08/04/2016
 */
@Stateless
public class CrudServiceController {
	
	/** La em. */
	@Inject @DbApplication
	protected EntityManager em;
	
	/** La transaction registry. */
	@Resource
	private TransactionSynchronizationRegistry transactionRegistry;
	
	/**
	 * Creates the.
	 *
	 * @param <T> el tipo generico
	 * @param t el t
	 * @return the t
	 */
	public <T> T create(T t) {
		Object object = transactionRegistry.getResource(RegistryContextHolder.USER_TRACK);
		return create(t, object);
	}
	
	/**
	 * Creates the.
	 *
	 * @param <T> el tipo generico
	 * @param t el t
	 * @param object el object
	 * @return the t
	 */
	public <T> T create(T t, Object object) {
		if (object instanceof UserTrack) {
			if (t instanceof Auditable) {
				UserTrack userTrack = (UserTrack)object;
				//update with real time
				userTrack.setAuditTime(LocalDateTime.now());
				Auditable auditable = (Auditable)t;
				setAuditFields(auditable,userTrack);
			}
		}
		this.em.persist(t);
		return t;
	}
	
	/**
	 * Sets the audit fields.
	 *
	 * @param auditable el auditable
	 * @param userTrack el user track
	 */
	private void setAuditFields(Auditable auditable,UserTrack userTrack) {
		if(auditable != null) {
			auditable.setAudit(userTrack);
			Map<String,List<? extends Auditable>> listAudit = auditable.getListForAudit();
			if (listAudit != null) {
				//if have list for audit
				for(List<? extends Auditable> auditableList :listAudit.values()) {
					if (auditableList != null && isLoadedList(auditableList)){
						//if list is not null
						for (Auditable childrenAudit : auditableList) {
							setAuditFields(childrenAudit,userTrack);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Comprueba si es loaded list.
	 *
	 * @param list el list
	 * @return true, si es loaded list
	 */
	public boolean isLoadedList(List<? extends Auditable> list) {
		boolean returnValue = false;
		PersistenceUnitUtil persistenceUtil = em.getEntityManagerFactory().getPersistenceUnitUtil();
		try {
			if (list != null && list.size() > 0 && persistenceUtil.isLoaded(list)){
				returnValue = true;
			}
		} catch(Exception eix) {
			eix.printStackTrace();
		}
		return returnValue;
	}
	
	/**
	 * Comprueba si es loaded entity.
	 *
	 * @param <T> el tipo generico
	 * @param entity el entity
	 * @param atribute el atribute
	 * @return true, si es loaded entity
	 */
	public <T> boolean isLoadedEntity(T entity,String atribute) {
		boolean valid = false;
		PersistenceUnitUtil persistenceUtil = em.getEntityManagerFactory().getPersistenceUnitUtil();
		try {
			if (entity != null && persistenceUtil.isLoaded(entity,atribute)){
				valid = true;
			}
		} catch(Exception eix) {
			eix.printStackTrace();
		}
		return valid;
	}
	
	/**
	 * Detachment entities.
	 *
	 * @param <T> el tipo generico
	 * @param entities el entities
	 */
	public <T> void detachmentEntities(List<T> entities){
		for(T entity : entities){
			detachmentEntity(entity);
		}
	}
	
	/**
	 * Detachment entity.
	 *
	 * @param <T> el tipo generico
	 * @param entity el entity
	 */
	public <T> void detachmentEntity(T entity){
		if(em.contains(entity)){
			em.detach(entity);
		}
	}
	
	/**
	 * Detachment all.
	 */
	public void detachmentAll(){
		em.clear();
	}
	
	/**
	 * Find.
	 *
	 * @param <T> el tipo generico
	 * @param type el type
	 * @param id el id
	 * @return the t
	 */
	public <T> T find(Class<T> type, Object id) {
		return (T) this.em.find(type, id);
	}
	
	/**
	 * Delete.
	 *
	 * @param type el type
	 * @param id el id
	 */
	public void delete(Class<?> type, Object id) {
		Object ref = this.em.getReference(type, id);
		this.em.remove(ref);
	}
	
	/**
	 * Update.
	 *
	 * @param <T> el tipo generico
	 * @param t el t
	 * @return the t
	 */
	public <T> T update(T t){
		Object object = transactionRegistry.getResource(RegistryContextHolder.USER_TRACK);
		return update(t, object);
	}
	
	/**
	 * Update.
	 *
	 * @param <T> el tipo generico
	 * @param t el t
	 * @param object el object
	 * @return the t
	 */
	public <T> T update(T t, Object object){
		if (object instanceof UserTrack) {
			if (t instanceof Auditable) {
				UserTrack userTrack = (UserTrack)object;
				//update with real time
				userTrack.setAuditTime(LocalDateTime.now());
				Auditable auditable = (Auditable)t;
				setAuditFields(auditable,userTrack);
			}
		}
		T mergeEntity = null;
		mergeEntity = this.em.merge(t);
		this.em.flush();
		return mergeEntity;
	}
	
	/**
	 * Find with named query.
	 *
	 * @param namedQueryName el named query name
	 * @return the list
	 */
	public List findWithNamedQuery(String namedQueryName){
        return this.em.createNamedQuery(namedQueryName).getResultList();
    }
	
	/**
	 * Find with query.
	 *
	 * @param jpaQuery el jpa query
	 * @return the list
	 */
	public List findWithQuery(String jpaQuery){
		return this.em.createQuery(jpaQuery).getResultList();
	}
	
	/**
	 * Find with native query.
	 *
	 * @param nativeQuery el native query
	 * @return the list
	 */
	public List findWithNativeQuery(String nativeQuery){
		return this.em.createNativeQuery(nativeQuery).getResultList();
	}
    
    /**
     * Find with named query.
     *
     * @param namedQueryName el named query name
     * @param parameters el parameters
     * @return the list
     */
    public List findWithNamedQuery(String namedQueryName, Map<String,Object> parameters){
        return findWithNamedQuery(namedQueryName, parameters, 0);
    }
    
    /**
     * Find with query.
     *
     * @param jpaQuery el jpa query
     * @param parameters el parameters
     * @return the list
     */
    public List findWithQuery(String jpaQuery, Map<String,Object> parameters){
        return findWithQuery(jpaQuery, parameters, 0);
    }
    
    /**
     * Find with native query.
     *
     * @param nativeQuery el native query
     * @param parameters el parameters
     * @return the list
     */
    public List findWithNativeQuery(String nativeQuery, Map<String,Object> parameters){
        return findWithNativeQuery(nativeQuery, parameters, 0);
    }

    /**
     * Find with named query.
     *
     * @param queryName el query name
     * @param resultLimit el result limit
     * @return the list
     */
    public List findWithNamedQuery(String queryName, int resultLimit) {
        return this.em.createNamedQuery(queryName).
                setMaxResults(resultLimit).
                getResultList();    
    }
    
    /**
     * Find with query.
     *
     * @param jpaQuery el jpa query
     * @param resultLimit el result limit
     * @return the list
     */
    public List findWithQuery(String jpaQuery, int resultLimit) {
        return this.em.createQuery(jpaQuery).
                setMaxResults(resultLimit).
                getResultList();    
    }
    
    /**
     * Find with native query.
     *
     * @param nativeQuery el native query
     * @param resultLimit el result limit
     * @return the list
     */
    public List findWithNativeQuery(String nativeQuery, int resultLimit) {
        return this.em.createNativeQuery(nativeQuery).
                setMaxResults(resultLimit).
                getResultList();    
    }

    /**
     * Find by native query.
     *
     * @param sql el sql
     * @param type el type
     * @return the list
     */
    public List findByNativeQuery(String sql, Class type) {
        return this.em.createNativeQuery(sql, type).getResultList();
    }
    
   /**
    * Find with named query.
    *
    * @param namedQueryName el named query name
    * @param parameters el parameters
    * @param resultLimit el result limit
    * @return the list
    */
   public List findWithNamedQuery(String namedQueryName, Map<String,Object> parameters,int resultLimit){
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        Query query = this.em.createNamedQuery(namedQueryName);
        if(resultLimit > 0)
            query.setMaxResults(resultLimit);
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
   
   /**
    * Find with query.
    *
    * @param jpaQuery el jpa query
    * @param parameters el parameters
    * @param resultLimit el result limit
    * @return the list
    */
   public List findWithQuery(String jpaQuery,Map<String,Object> parameters,int resultLimit){
	   Set<Entry<String, Object>> rawParameters = parameters.entrySet();
	   Query query = this.em.createQuery(jpaQuery);
	   if(resultLimit > 0)
           query.setMaxResults(resultLimit);
       for (Entry<String, Object> entry : rawParameters) {
           query.setParameter(entry.getKey(), entry.getValue());
       }
       return query.getResultList();
   }
   
   /**
    * Obtiene pagination query.
    *
    * @param jpaQuery el jpa query
    * @param parameters el parameters
    * @param pageIndex el page index
    * @param pageSize el page size
    * @return pagination query
    */
   public List getPaginationQuery(String jpaQuery,Map<String,Object> parameters,int pageIndex,int pageSize){
	   Query query = this.em.createQuery(jpaQuery);
	   if(parameters != null && !parameters.isEmpty()){
		   Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		   for (Entry<String, Object> entry : rawParameters) {
	           query.setParameter(entry.getKey(), entry.getValue());
	       }
	   }
	   query.setFirstResult((pageIndex-1)*pageSize);
	   query.setMaxResults(pageSize);
	   return query.getResultList();
   }
   
   /**
    * Obtiene pagination named query.
    *
    * @param namedQueryName el named query name
    * @param parameters el parameters
    * @param pageIndex el page index
    * @param pageSize el page size
    * @return pagination named query
    */
   public List getPaginationNamedQuery(String namedQueryName,Map<String,Object> parameters,int pageIndex,int pageSize){
	   Query query = this.em.createNamedQuery(namedQueryName);
	   if(parameters != null && !parameters.isEmpty()){
		   Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		   for (Entry<String, Object> entry : rawParameters) {
	           query.setParameter(entry.getKey(), entry.getValue());
	       }
	   }
	   query.setFirstResult((pageIndex-1)*pageSize);
	   query.setMaxResults(pageSize);
	   return query.getResultList();
   }
   
   
   /**
    * Find with native query.
    *
    * @param nativeQuery el native query
    * @param parameters el parameters
    * @param resultLimit el result limit
    * @return the list
    */
   public List findWithNativeQuery(String nativeQuery,Map<String,Object> parameters,int resultLimit){
	   Set<Entry<String, Object>> rawParameters = parameters.entrySet();
	   Query query = this.em.createNativeQuery(nativeQuery);
	   if(resultLimit > 0)
           query.setMaxResults(resultLimit);
       for (Entry<String, Object> entry : rawParameters) {
           query.setParameter(entry.getKey(), entry.getValue());
       }
       return query.getResultList();
   }
   

	

	/**
	 * Instancia un nuevo crud service controller.
	 */
	public CrudServiceController() {
		// TODO Auto-generated constructor stub
	}

}
