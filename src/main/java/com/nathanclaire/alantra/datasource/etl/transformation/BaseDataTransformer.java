/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.transformation;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.model.DataField;

/**
 * @author Edward Banfa 
 *
 */
public abstract class BaseDataTransformer extends BaseProcessService {
	
	private Logger logger = LoggerFactory.getLogger(BaseDataTransformer.class);
	public static final String RELATED_ENTITY_NOT_FOUND = "NoOperationDataTransformer.RELATED_ENTITY_NOT_FOUND";
	private static final String INVALID_RELATIONSHIP_FIELD = "BaseDataTransformer.INVALID_RELATIONSHIP_FIELD";
	private static final String INVALID_CODE_VALUE_PROVIDED = "BaseDataTransformer.INVALID_CODE_VALUE_PROVIDED";
	
	/**
	 * @param field
	 * @param code
	 * @return
	 */
	protected Integer getRelatedIDFromRelatedCode(DataField field, String code) throws ApplicationException
	{
		if(field.getRelTargetEntityCd() == null) 
			throw new ApplicationException(INVALID_RELATIONSHIP_FIELD);
		if(!StringUtil.isValidString(code))
			throw new ApplicationException(INVALID_CODE_VALUE_PROVIDED);
		String queryString = "select e.id from " + field.getRelTargetEntityCd() + " e where e.code ='" + code + "'";
		logger.info("Using the follow query string: {}", queryString); 
		try {
			Query query = getEntityManager().createQuery(queryString);
			return (Integer) query.getSingleResult();
		} catch (NoResultException e) {
			throw new ApplicationException(RELATED_ENTITY_NOT_FOUND);
		} catch (Exception e) {
			throw new ApplicationException(ApplicationException.UNKNOWN_EXCEPTION);
		}
	}

}
