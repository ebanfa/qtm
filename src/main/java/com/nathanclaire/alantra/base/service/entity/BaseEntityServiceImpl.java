/**
 * 
 */
package com.nathanclaire.alantra.base.service.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseEntityServiceImpl<T,V> {
	
	public static final char ENTITY_STATUS_ACTIVE = 'A';
	public static final char ENTITY_STATUS_INACTIVE = 'I';
    
    /**
     * Entity
     */
    @Inject
    private EntityManager entityManager;
    
    @Inject
    protected MultivaluedMap<String, String> queryParameters;
    /**
     * Type
     */
    private Class<T> ENTITY_CLASS;
	/**
	 * Id criteria
	 */
	protected static final String ID_CRITERIA = "id";
	/**
	 * Code criteria
	 */
	protected static final String CODE_CRITERIA = "code";
	/**
	 * Name criteria
	 */
	protected static final String NAME_CRITERIA = "name";
	/**
	 * Default system user name
	 */
	protected static final String SYS_USR_NM = "name";

    /**
     * Default constructor
     */
    public BaseEntityServiceImpl() {}

    /**
     * Parameterized constructor
     * @param ENTITY_CLASS
     */
    public BaseEntityServiceImpl(Class<T> entityClass) {
        this.ENTITY_CLASS = entityClass;
    }

    /**
     * Getter for the entity manager
     * @return
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    /**
     * @param id
     * @return
     */
    protected T getSingleInstance(Integer id) {
        return entityManager.createQuery(getSingleInstanceCriteriaQuery(id)).getSingleResult();
    }
    
    /**
     * @param id
     * @return
     */
    private  CriteriaQuery<T> getSingleInstanceCriteriaQuery(Integer id)
    {
    	final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(ENTITY_CLASS);
        
        Root<T> root = criteriaQuery.from(ENTITY_CLASS);
        Predicate condition = criteriaBuilder.equal(root.get("id"), id);
        criteriaQuery.select(criteriaBuilder.createQuery(ENTITY_CLASS).getSelection()).where(condition);
        return criteriaQuery;
    }
    
    /**
     * @param code
     * @return
     */
    protected T findInstanceByCode(String code)
    {
    	queryParameters.clear();
    	queryParameters.add(CODE_CRITERIA, code);
    	return findAllInstances(queryParameters).get(0);
    }
    
    /**
     * @param searchCriteria
     * @return
     */
    protected List<T> findByCriteria(Map<String, String> searchCriteria){
    	queryParameters.clear();
    	for(Entry<String, String> entry: searchCriteria.entrySet())
    	{
    		queryParameters.add(entry.getKey(), entry.getValue());
    	}
    	return findAllInstances(queryParameters);
    }
    
    /**
     * @param code
     * @return
     */
    protected T findInstanceByName(String name)
    {
    	queryParameters.clear();
    	queryParameters.add(NAME_CRITERIA, name);
    	return findAllInstances(queryParameters).get(0);
    }
    
    /**
     * @param queryParameters
     * @return
     */
    public List<T> findAllInstances(MultivaluedMap<String, String> queryParameters)
    {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(ENTITY_CLASS);
        
        Root<T> root = criteriaQuery.from(ENTITY_CLASS);
        Predicate[] predicates = extractPredicates(queryParameters, criteriaBuilder, root);
        
        criteriaQuery.select(criteriaQuery.getSelection()).where(predicates);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        
        if (queryParameters.containsKey("first")) 
        {
        	Integer firstRecord = Integer.parseInt(queryParameters.getFirst("first"))-1;
        	query.setFirstResult(firstRecord);
        }
        if (queryParameters.containsKey("maxResults")) 
        {
        	Integer maxResults = Integer.parseInt(queryParameters.getFirst("maxResults"));
        	query.setMaxResults(maxResults);
        }
		return query.getResultList();
    }
    
    /**
     * @param request
     * @return
     */
    protected T createInstance(V request) {
        try 
        {
        	T instance = this.loadModelFromRequest(request);
        	entityManager.persist(instance);
            return instance;
        } 
        catch (ConstraintViolationException e) 
        {
            // A WebApplicationException can wrap a response
            // Throwing the exception causes an automatic rollback
            throw new WebApplicationException(e);
        } catch (Exception e) {
            // Finally, handle 
            throw new WebApplicationException(e);
        }
    }
    
    /**
     * @param id
     */
    protected void deleteInstance(Integer id)
    {
    	try 
        {
    		T instance = getSingleInstance(id);
    		entityManager.remove(instance);
        } 
        catch (ConstraintViolationException e) 
        {
            // A WebApplicationException can wrap a response
            // Throwing the exception causes an automatic rollback
            throw new WebApplicationException(e);
        } catch (Exception e) {
            // Finally, handle 
            throw new WebApplicationException(e);
        }
    }
    
    /**
     * @param id
     */
    protected T updateInstance(V request)
    {
    	try 
        {
    		T instance = this.loadModelFromRequest(request);
    		entityManager.merge(instance);
    		return instance;
        } 
        catch (ConstraintViolationException e) 
        {
            // A WebApplicationException can wrap a response
            // Throwing the exception causes an automatic rollback
            throw new WebApplicationException(e);
        } catch (Exception e) {
            // Finally, handle 
            throw new WebApplicationException(e);
        }
    }
    
    /**
     * <p>
     *     Subclasses may choose to expand the set of supported query parameters (for adding more filtering
     *     criteria on search and count) by overriding this method.
     * </p>
     * @param queryParameters - the HTTP query parameters received by the endpoint
     * @param criteriaBuilder - @{link CriteriaBuilder} used by the invoker
     * @param root  @{link Root} used by the invoker
     * @return a list of {@link Predicate}s that will added as query parameters
     */
    protected Predicate[] extractPredicates(MultivaluedMap<String, String> queryParameters, 
    		CriteriaBuilder criteriaBuilder, Root<T> root) {
        return new Predicate[]{};
    }
    
    /**
     * <p> A method for counting all entities of a given type </p>
     * @param queryParameters
     * @return
     */
    public Map<String, Long> getCount(MultivaluedMap<String, String> queryParameters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(ENTITY_CLASS);
        criteriaQuery.select(criteriaBuilder.count(root));
        Predicate[] predicates = extractPredicates(queryParameters, criteriaBuilder, root);
        criteriaQuery.where(predicates);
        Map<String, Long> result = new HashMap<String, Long>();
        result.put("count", entityManager.createQuery(criteriaQuery).getSingleResult());
        return result;
    }
    
    /**
     * @param request
     * @return
     */
    protected abstract T loadModelFromRequest(V request);
    
	/**
	 * @return
	 */
	protected Date getCurrentSystemDate(){
    	return new Date();
    }
    /**
     * @param request
     * @return
     */
    protected String getCurrentUserName(V request){
    	return SYS_USR_NM;
    }
    /**
     * @return
     */
    protected String getDefaultUserName(){
    	return SYS_USR_NM;
    }
    
    protected Date stringToDate(String dateString)
    {
    	DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
    	DateTime dt = formatter.parseDateTime(dateString);
    	return dt.toDate();
    }



}
