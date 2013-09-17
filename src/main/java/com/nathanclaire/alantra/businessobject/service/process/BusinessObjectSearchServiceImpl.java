/**
 * 
 */
package com.nathanclaire.alantra.businessobject.service.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectDataImpl;
import com.nathanclaire.alantra.businessobject.data.SearchData;
import com.nathanclaire.alantra.businessobject.data.SearchFieldData;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class BusinessObjectSearchServiceImpl implements
		BusinessObjectSearchService {

	private static final String E_SELECT = "select e from";
	private static final String AS_E_SELECT = "e";
	private static final String E_WHERE = "where";
	private static final String FIELD_OP_AND = "and";
	private static final String FIELD_OP_OR = "or";
	private static final String SINGLE_QUOTES_BEGIN = "'";
	private static final String SINGLE_QUOTES_END = "'";
	private static final String SINGLE_WHITESPACE = " ";
	private static final String LIKE = "L";
	private static final String LIKE_SYM = "L";
	private static final String EQUALS_TO = "ET";
	private static final String EQUALS_TO_SYM = "=";
	private static final String LESS_THAN = "LT";
	private static final String LESS_THAN_SYM = "<";
	private static final String GREATER_THAN = "GT";
	private static final String GREATER_THAN_SYM = ">";
	private static final String QUERY_TERMINATOR = ";";
	
	@Inject private EntityManager entityManager;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessobject.service.process.BusinessObjectSearchService#find(com.nathanclaire.alantra.businessobject.util.BusinessObjectSearchInfo)
	 */
	@Override
	public List<BusinessObjectData> find(SearchData searchInfo)
			throws ApplicationException {
		
		List<BusinessObjectData> dataList = new ArrayList<BusinessObjectData>();
		try {
			String queryString = getSearchQuery(searchInfo);
			Query query = entityManager.createQuery(queryString);
			List<Object> businessObjects = query.getResultList();
			for(Object businessObject : businessObjects){
				BusinessObjectData businessObjectData = new BusinessObjectDataImpl();
				BusinessObjectUtil.copyDataToBusinessObject(businessObjectData, businessObject);
				logger.debug("Processed >>>>>>> {}", businessObjectData);
				dataList.add(businessObjectData);
			}
			logger.debug("Received {} business objects", businessObjects.size());
		} catch (Exception e) {
			ExceptionUtil.logAndProcessException(e, ErrorCodes.BOSS_BUSINESS_OBJ_QUERY_ERROR_CD);
		}
		return dataList;
		
	}

	/**
	 * Build a JPA query string from the search data provided.
	 * 
	 * @param searchInfo
	 * @return
	 */
	private String getSearchQuery(SearchData searchInfo) {
		String query = E_SELECT.concat(SINGLE_WHITESPACE);
		
		query = query.concat(searchInfo.getBusinesObjectName());
		query = query.concat(SINGLE_WHITESPACE);
		query = query.concat(AS_E_SELECT);
		Map<String, SearchFieldData> searchFields = searchInfo.getSearchFields();

		Integer currentFieldIndex = 0;
		String fieldQueryExpression = "";
		for (String fieldName : searchFields.keySet())
		{
			currentFieldIndex++;
			// The expression for the field we are processing
			SearchFieldData field = searchFields.get(fieldName);
			String fieldValue = field.getFieldValue();
			if(!StringUtil.isValidString(fieldValue))
				continue;
			// Default search operator
			String fieldOperator = EQUALS_TO_SYM;
			// TODO: Fix this
			/*if(field.getFieldDataTypeName() == "STRING" | 
					field.getFieldDataTypeName() == "DATE")*/
				fieldValue = SINGLE_QUOTES_BEGIN.concat(fieldValue.concat(SINGLE_QUOTES_END));
			// Process the search operator symbol for this field
			// so for GREATER_THAN the generated query will be
			// fieldName > fieldValue
			if(field.getFieldSearchOperator() == GREATER_THAN)
				fieldOperator = GREATER_THAN_SYM;
			else if(field.getFieldSearchOperator() == LESS_THAN)
				fieldOperator = LESS_THAN_SYM;
			else if(field.getFieldSearchOperator() == LIKE)
				fieldOperator = LIKE_SYM;
			// Skip the inter-field operator for the first field we 
			// are adding
			if(StringUtil.isValidString(fieldQueryExpression)) {
				fieldQueryExpression = fieldQueryExpression.concat(SINGLE_WHITESPACE);
				fieldQueryExpression = fieldQueryExpression.concat(FIELD_OP_AND);
			}
			fieldQueryExpression = fieldQueryExpression.concat(SINGLE_WHITESPACE);
			fieldQueryExpression = fieldQueryExpression.concat(fieldName);
			fieldQueryExpression = fieldQueryExpression.concat(SINGLE_WHITESPACE);
			fieldQueryExpression = fieldQueryExpression.concat(fieldOperator);
			fieldQueryExpression = fieldQueryExpression.concat(SINGLE_WHITESPACE);
			fieldQueryExpression = fieldQueryExpression.concat(fieldValue);
		}
		if(StringUtil.isValidString(fieldQueryExpression)){
			query = query.concat(SINGLE_WHITESPACE);
			query = query.concat(E_WHERE);
			query = query.concat(fieldQueryExpression);
		}
		//query = query.concat(QUERY_TERMINATOR);
		logger.debug("The generated query: {}", query);
		return query;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessobject.service.process.BusinessObjectSearchService#findById(com.nathanclaire.alantra.businessobject.util.SearchData)
	 */
	@Override
	public BusinessObjectData findById(SearchData searchInfo)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
