/**
 * 
 */
package com.nathanclaire.alantra.base.service.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.persistence.EntityExistsException;
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

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseEntityServiceImpl<M,T,V> {
	
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
    private Class<M> ENTITY_CLASS;
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
	private static final String INSTANCE_NOT_FOUND = null;
	private static final String INSTANCE_ALREADY_EXIST = null;
	private static final String INSTANCE_CONTRAINT_VIOLATION = null;

    /**
     * Default constructor
     */
    public BaseEntityServiceImpl() {}

    /**
     * Parameterized constructor
     * @param ENTITY_CLASS
     */
    public BaseEntityServiceImpl(Class<M> entityClass) {
        this.ENTITY_CLASS = entityClass;
    }

    /**
     * Getter for the entity manager
     * @return
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
    /**
     * @param id
     * @return
     */
    protected M getSingleInstance(Integer id)  throws ApplicationException{
        return entityManager.find(ENTITY_CLASS, id);
    }
    
    /**
     * @param code
     * @return
     */
    protected M findInstanceByCode(String code) throws ApplicationException
    {
    	queryParameters.clear();
    	queryParameters.add(CODE_CRITERIA, code);
    	List<M> instances = findAllInstances(queryParameters);
    	if(instances.isEmpty()) return null;
    	return instances.get(0);
    }
    
    /**
     * @param searchCriteria
     * @return
     */
    public List<M> findByCriteria(Map<String, String> searchCriteria) throws ApplicationException{
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
    protected M findInstanceByName(String name) throws ApplicationException
    {
    	queryParameters.clear();
    	queryParameters.add(NAME_CRITERIA, name);
    	List<M> instances = findAllInstances(queryParameters);
    	if(instances.isEmpty()) return null;
    	return instances.get(0);
    }
    
    /**
     * @param queryParameters
     * @return
     */
    public List<M> findAllInstances(MultivaluedMap<String, String> queryParameters) throws ApplicationException
    {
    	if(queryParameters == null){
    		queryParameters = this.queryParameters; 
    		queryParameters.clear();
    	}
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<M> criteriaQuery = criteriaBuilder.createQuery(ENTITY_CLASS);
        
        TypedQuery<M> query = 
        		initCriteria(queryParameters, criteriaBuilder, criteriaQuery);
        initQuery(queryParameters, query);
        //query.setMaxResults(50);
		return query.getResultList();
    }
    
    /**
     * @param request
     * @return
     */
    protected M createInstance(V request) throws ApplicationException {
        try 
        {
        	M instance = convertRequestToModel(request);
        	entityManager.persist(instance);
            return instance;
        } 
        catch (EntityExistsException e) 
        {
            throw new ApplicationException(INSTANCE_ALREADY_EXIST);
        }
        catch (ConstraintViolationException e) 
        {
            throw new ApplicationException(INSTANCE_CONTRAINT_VIOLATION);
        }
    }
    
    /**
     * @param id
     */
    protected void deleteInstance(Integer id) throws ApplicationException
    {
		M instance = getSingleInstance(id);
		if(instance == null)
			throw new ApplicationException(INSTANCE_NOT_FOUND);
		entityManager.remove(instance);
    }
    
    /**
     * @param id
     */
    protected M updateInstance(V request) throws ApplicationException
    {
    	try 
        {
    		M instance = this.convertRequestToModel(request);
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
	 * @param queryParameters
	 * @param criteriaBuilder
	 * @param criteriaQuery
	 * @return
	 */
	private TypedQuery<M> initCriteria(
			MultivaluedMap<String, String> queryParameters,
			final CriteriaBuilder criteriaBuilder,
			final CriteriaQuery<M> criteriaQuery) {
		Root<M> root = criteriaQuery.from(ENTITY_CLASS);
        Predicate[] predicates = extractPredicates(queryParameters, criteriaBuilder, root);
        
        criteriaQuery.select(criteriaQuery.getSelection()).where(predicates);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
        TypedQuery<M> query = entityManager.createQuery(criteriaQuery);
		return query;
	}

	/**
	 * @param queryParameters
	 * @param query
	 * @throws NumberFormatException
	 */
	private void initQuery(MultivaluedMap<String, String> queryParameters,
			TypedQuery<M> query) throws NumberFormatException {
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
    		CriteriaBuilder criteriaBuilder, Root<M> root) {
    	List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey(CODE_CRITERIA)) {
            String code = queryParameters.getFirst(CODE_CRITERIA);
            //predicates.add(criteriaBuilder.equal(root.get(CODE_CRITERIA), code));
            predicates.add(criteriaBuilder.like(root.<String>get(CODE_CRITERIA), code));
        }
        if (queryParameters.containsKey(NAME_CRITERIA)) {
            String name = queryParameters.getFirst(NAME_CRITERIA);
            predicates.add(criteriaBuilder.equal(root.get(NAME_CRITERIA), name));
        }
        return predicates.toArray(new Predicate[]{});
    }
    
    /**
     * <p> A method for counting all entities of a given type </p>
     * @param queryParameters
     * @return
     */
    public Map<String, Long> getCount(MultivaluedMap<String, String> queryParameters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<M> root = criteriaQuery.from(ENTITY_CLASS);
        criteriaQuery.select(criteriaBuilder.count(root));
        Predicate[] predicates = extractPredicates(queryParameters, criteriaBuilder, root);
        criteriaQuery.where(predicates);
        Map<String, Long> result = new HashMap<String, Long>();
        result.put("count", entityManager.createQuery(criteriaQuery).getSingleResult());
        return result;
    }
    
    public M loadDefaultFieldsFromRequest(M instance, V request)
    {
    	BaseEntity baseInstance = (BaseEntity) instance;
    	BaseRequest baseRequest = (BaseRequest) request;
    	Integer applicationActivityId = baseRequest.getId();
    	// Are we editing a ApplicationActivity
    	if(applicationActivityId != null) 
    	{
    		baseInstance = (BaseEntity) getEntityManager().find(ENTITY_CLASS, applicationActivityId);
    		baseInstance.setLastModifiedDt(baseRequest.getLastModifiedDt());
    		baseInstance.setLastModifiedUsr(getCurrentUserName(request)); 
    		baseInstance.setEffectiveDt(baseRequest.getEffectiveDt()); 
    	}
    	else
    	{
    		baseInstance.setCreatedDt(getCurrentSystemDate());
    		baseInstance.setCreatedByUsr(getCurrentUserName(request));
    		baseInstance.setEffectiveDt(getCurrentSystemDate());
    	}
    	return instance;
    }
    
    protected List<ListItemResponse> convertEntitiesToListItem(List<BaseEntity> entities)
    {
    	return null;
    }
    
    /**
     * @param from
     * @param to
     */
    protected void copyProperties(Object from, Object to)
    {
    	PropertyUtils.copyProperties(from, to, null);
    }
    
    /**
     * @param request
     * @return
     */
    public abstract M convertRequestToModel(V request) throws ApplicationException;
    
    /**
     * @param model
     * @return
     */
    public abstract T convertModelToResponse(M model) throws ApplicationException;
    
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