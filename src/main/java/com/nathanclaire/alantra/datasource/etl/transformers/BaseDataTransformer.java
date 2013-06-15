/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformers;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseDataTransformer {
	
	private Logger logger = LoggerFactory.getLogger(BaseDataTransformer.class);
	public static final String RELATED_ENTITY_NOT_FOUND = "NoOperationDataTransformer.RELATED_ENTITY_NOT_FOUND";
	private static final String INVALID_RELATIONSHIP_FIELD = null;

	
    /**
     * Entity
     */
    @Inject
    private EntityManager entityManager;

    
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	/**
	 * @param field
	 * @param code
	 * @return
	 */
	protected Integer getRelatedIDFromRelatedCode(DataField field, String code) throws ApplicationException
	{
		String entityName = field.getTargetEntityCd();
		String relatedEntityName = field.getRelTargetEntityCd();
		if(relatedEntityName == null) throw new ApplicationException(INVALID_RELATIONSHIP_FIELD);
		String queryString = "select e.id from " + relatedEntityName + " e where e.code ='" + code + "'";
		logger.info("Using the follow query string: {}", queryString); 
		try {
			Query query = this.getEntityManager().createQuery(queryString);
			return (Integer) query.getSingleResult();
		} catch (NoResultException e) {
			throw new ApplicationException(RELATED_ENTITY_NOT_FOUND);
		} catch (Exception e) {
			throw new ApplicationException(ApplicationException.UNKNOWN_EXCEPTION);
		}
	}

}
