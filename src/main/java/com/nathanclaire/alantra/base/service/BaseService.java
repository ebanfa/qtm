/**
 * 
 */
package com.nathanclaire.alantra.base.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.party.model.Organization;

/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseService<T> {
    
    /**
     * Entity
     */
    @Inject
    private EntityManager entityManager;

    /**
     * Type
     */
    private Class<T> entityClass;

	/**
	 * Id criteria
	 */
	protected static final String ID_CRITERIA = "id";
	/**
	 * Code criteria
	 */
	protected static final String CODE_CRITERIA = "code";

    /**
     * Default constructor
     */
    public BaseService() {}

    /**
     * Parameterized constructor
     * @param entityClass
     */
    public BaseService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Getter for the entity manager
     * @return
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
    /**
     * @param queryParameters
     * @return
     */
    public List<T> getAll(MultivaluedMap<String, String> queryParameters) 
    {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        
        Root<T> root = criteriaQuery.from(entityClass);
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



}
