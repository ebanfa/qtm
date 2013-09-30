/**
 * 
 */
package com.nathanclaire.alantra.businessobject.service.process;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.businessobject.data.SearchData;
import com.nathanclaire.alantra.businessobject.data.SearchFieldData;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectQueryConstants;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectQueryBuilderServiceImpl implements BusinessObjectQueryBuilderService 
{
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessobject.service.process.BusinessObjectQueryBuilderService#buildQuery(com.nathanclaire.alantra.businessobject.data.SearchData)
	 */
	@Override
	public String buildQuery(SearchData data) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{}, "BusinessObjectQueryBuilderService.buildQuery");
		logger.debug("Building business object query string from search data {}", data);
		
		String query = BusinessObjectQueryConstants.E_SELECT.concat(
				BusinessObjectQueryConstants.SINGLE_WHITESPACE);
		
		query = query.concat(data.getBusinesObjectName());
		query = query.concat(BusinessObjectQueryConstants.SINGLE_WHITESPACE);
		query = query.concat(BusinessObjectQueryConstants.AS_E_SELECT);
		Map<String, SearchFieldData> searchFields = data.getSearchFields();

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
			String fieldOperator = BusinessObjectQueryConstants.EQUALS_TO_SYM;
			// TODO: Fix this
			/*if(field.getFieldDataTypeName() == "STRING" | 
					field.getFieldDataTypeName() == "DATE")*/
				fieldValue = BusinessObjectQueryConstants.SINGLE_QUOTES_BEGIN.concat(
						fieldValue.concat(BusinessObjectQueryConstants.SINGLE_QUOTES_END));
			// Process the search operator symbol for this field
			// so for GREATER_THAN the generated query will be
			// fieldName > fieldValue
			if(field.getFieldSearchOperator() == BusinessObjectQueryConstants.GREATER_THAN)
				fieldOperator = BusinessObjectQueryConstants.GREATER_THAN_SYM;
			else if(field.getFieldSearchOperator() == BusinessObjectQueryConstants.LESS_THAN)
				fieldOperator = BusinessObjectQueryConstants.LESS_THAN_SYM;
			else if(field.getFieldSearchOperator() == BusinessObjectQueryConstants.LIKE)
				fieldOperator = BusinessObjectQueryConstants.LIKE_SYM;
			// Skip the inter-field operator for the first field we 
			// are adding
			if(StringUtil.isValidString(fieldQueryExpression)) {
				fieldQueryExpression = 
						fieldQueryExpression.concat(BusinessObjectQueryConstants.SINGLE_WHITESPACE);
				fieldQueryExpression = 
						fieldQueryExpression.concat(BusinessObjectQueryConstants.FIELD_OP_AND);
			}
			fieldQueryExpression = 
					fieldQueryExpression.concat(BusinessObjectQueryConstants.SINGLE_WHITESPACE);
			fieldQueryExpression = 
					fieldQueryExpression.concat(fieldName);
			fieldQueryExpression = 
					fieldQueryExpression.concat(BusinessObjectQueryConstants.SINGLE_WHITESPACE);
			fieldQueryExpression = 
					fieldQueryExpression.concat(fieldOperator);
			fieldQueryExpression = 
					fieldQueryExpression.concat(BusinessObjectQueryConstants.SINGLE_WHITESPACE);
			fieldQueryExpression = 
					fieldQueryExpression.concat(fieldValue);
		}
		if(StringUtil.isValidString(fieldQueryExpression)){
			query = query.concat(BusinessObjectQueryConstants.SINGLE_WHITESPACE);
			query = query.concat(BusinessObjectQueryConstants.E_WHERE);
			query = query.concat(fieldQueryExpression);
		}
		logger.debug("The generated query: {}", query);
		return query;
	}

}
