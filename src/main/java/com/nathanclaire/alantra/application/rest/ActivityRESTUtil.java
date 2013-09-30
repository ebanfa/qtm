/**
 * 
 */
package com.nathanclaire.alantra.application.rest;

import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationActivity;
import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectFieldData;
import com.nathanclaire.alantra.businessobject.data.SearchData;
import com.nathanclaire.alantra.businessobject.data.SearchFieldData;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectQueryConstants;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectRESTUtil;

/**
 * REST utilities for {@link ApplicationActivity}s.
 * 
 * @author Edward Banfa
 *
 */
public class ActivityRESTUtil {

	
	public static final String ACTIVITY_URL = "activityURL";
	public static final String ACTIVITY_URL_FIELD_NM = "activityUrl";
	public static final String ACTIVITY_TY_FIELD_NM = "applicationActivityType";
	public static final String ACTIVITY_NM_FIELD_NM = "name";
	public static final String ENITTY_FIELD_NM = "applicationEntity";
	public static final String ENTITY_ID = "entityId";
	
	private static Logger logger = LoggerFactory.getLogger(ActivityRESTUtil.class);

	/**
	 * Extracts the {@code activityURL} from the
	 * {@code queryParameters} supplied.
	 * 
	 * @param queryParameters the query parameters
	 * @return the extracted activity URL
	 * @throws ApplicationException if an exception was encountered
	 */
	public static String extractActivityURL(MultivaluedMap<String, String> queryParameters) 
			throws ApplicationException 
	{
		return null;
	}

	/**
	 * Extracts activity search parameters from the provided 
	 * {@code queryParameters}.
	 * 
	 * @param queryParameters the query parameters
	 * @return the search data
	 * @throws ApplicationException if an exception was encountered
	 */
	public static SearchData mapToBusinessObjectSearchData(MultivaluedMap<String, String> queryParameters) 
			throws ApplicationException
	{
		SearchData searchData = new SearchData();
				//BusinessObjectRESTUtil.extractDefaultParameters(queryParameters);
		logger.debug("Processing queryParameters {}", queryParameters);
		if(!queryParameters.containsKey(ACTIVITY_URL))
			throw new ApplicationException(
					ErrorCodes.ARU_LOAD_ACTIVITY_ERROR_CD, 
					ErrorCodes.ARU_INVALID_ACTIVITY_URL_ERROR_MSG);
		
		SearchFieldData fieldData = new SearchFieldData();
		fieldData.setFieldName(ACTIVITY_URL_FIELD_NM);
		fieldData.setFieldValue(queryParameters.getFirst(ACTIVITY_URL));
		fieldData.setFieldSearchOperator(BusinessObjectQueryConstants.EQUALS_TO);
		searchData.addField(ACTIVITY_URL_FIELD_NM, fieldData);
		logger.debug("Processing {}", searchData);
		return searchData;
	}
	
	/**
	 * @param queryParameters
	 * @return
	 * @throws ApplicationException
	 */
	public static String getActivityURL(MultivaluedMap<String, String> queryParameters) 
			throws ApplicationException
	{
		logger.debug("Processing queryParameters {}", queryParameters);
		if(!queryParameters.containsKey(ACTIVITY_URL))
			throw new ApplicationException(
					ErrorCodes.ARU_LOAD_ACTIVITY_ERROR_CD, 
					ErrorCodes.ARU_INVALID_ACTIVITY_URL_ERROR_MSG);
		String activityURL = queryParameters.getFirst(ACTIVITY_URL);
		return activityURL;
	}

	/**
	 * Converts a {@link BusinessObjectData} into an {@link ActivityResponse}.
	 * 
	 * @param activityBOData the business object data 
	 * 						 that we are converting
	 * @return the activity response
	 * @throws ApplicationException if an exception
	 */
	public static ActivityResponse businessObjectToActivityResponse(BusinessObjectData activityBOData) 
			throws ApplicationException
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{activityBOData}, "AbstractRESTService.businessObjectToActivityResponse");
		ActivityResponse response = new ActivityResponse();
		logger.debug("Building activity response from {}", activityBOData.getDataValues());
		

		BusinessObjectFieldData entity = 
				(BusinessObjectFieldData) activityBOData.getDataValue(ENITTY_FIELD_NM);
		String entityCode = (String) entity.getFieldValue();
		// We set the entity code of the entity we are loading the activity for
		// as the business object name. 
		response.setBusinessObjectName(entityCode);
		response.setBusinessObjectData(activityBOData);
		// Activity URL field
		BusinessObjectFieldData activityURL = 
				(BusinessObjectFieldData) activityBOData.getDataValue(ACTIVITY_URL_FIELD_NM);
		response.setActivityURL((String) activityURL.getFieldValue());
		// Activity name field
		BusinessObjectFieldData activityNm = 
				(BusinessObjectFieldData) activityBOData.getDataValue(ACTIVITY_NM_FIELD_NM);
		response.setActivityName((String) activityNm.getFieldValue());
		// Activity type field
		BusinessObjectFieldData activityTy = 
				(BusinessObjectFieldData) activityBOData.getDataValue(ACTIVITY_TY_FIELD_NM);
		response.setActivityTypeName((String) activityTy.getFieldValue());
		response.setErrors(false);
		return response;
	}
	
	public static ActivityResponse applicationActivityToActivityResponse(ApplicationActivity activity)
	{
		ActivityResponse response = new ActivityResponse();
		logger.debug("Building activity response from application activity {}", activity);
		response.setActivityName(activity.getName());
		response.setActivityURL(activity.getActivityUrl());
		response.setActivityTypeName(activity.getApplicationActivityType().getCode());
		ApplicationEntity entity = activity.getApplicationEntity();
		if(entity != null)
			response.setBusinessObjectName(entity.getName());
		return response;
	}

	/**
	 * Generates an error response {@link ActivityResponse} for the supplied
	 * {@link Exception} and {@link MultivaluedMap}. 
	 * 
	 * @param e the exception
	 * @param queryParameters the request of that resulted in the exception
	 * @return an activity response with adequate information on the exception
	 */
	public static ActivityResponse generateErrorResponse(ApplicationException e, MultivaluedMap<String, String> queryParameters) {
		// TODO Auto-generated method stub
		ActivityResponse response = new ActivityResponse();
		response.setErrors(true);
		return response;
	}

	/**
	 * @param queryParameters
	 * @return
	 */
	public static boolean requestContainsEntityId(MultivaluedMap<String, String> queryParameters) {
		if(queryParameters.containsKey(ENTITY_ID))
			return true;
		return false;
	}

	/**
	 * @param queryParameters
	 * @return
	 * @throws ApplicationException
	 */
	public static Integer getEntityIdFromRequest(MultivaluedMap<String, String> queryParameters) 
			throws ApplicationException {
		if(!queryParameters.containsKey(ENTITY_ID))
			throw new ApplicationException(
					ErrorCodes.ARU_LOAD_ACTIVITY_ERROR_CD, 
					ErrorCodes.ARU_INVALID_ENTITY_ID_ERROR_MSG);
		String entityIdParam = queryParameters.getFirst(ENTITY_ID);
		Integer entityId = null;
		try {
			entityId = Integer.valueOf(entityIdParam);
		} catch (NumberFormatException e) {
			throw new ApplicationException(
					ErrorCodes.ARU_LOAD_ACTIVITY_ERROR_CD, 
					ErrorCodes.ARU_INVALID_ENTITY_ID_ERROR_MSG);
		}
		return entityId;
	}

}
