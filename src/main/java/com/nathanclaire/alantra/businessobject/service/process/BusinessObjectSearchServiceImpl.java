/**
 * 
 */
package com.nathanclaire.alantra.businessobject.service.process;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectDataImpl;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectFieldData;
import com.nathanclaire.alantra.businessobject.data.SearchData;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class BusinessObjectSearchServiceImpl implements	BusinessObjectSearchService {
	
	@Inject private EntityManager entityManager;
	@Inject ApplicationEntityService applicationEntityService;
	@Inject BusinessObjectQueryBuilderService queryBuilderService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessobject.service.process.BusinessObjectSearchService#find(com.nathanclaire.alantra.businessobject.util.BusinessObjectSearchInfo)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BusinessObjectData> find(SearchData searchData, List<BusinessObjectFieldData> fieldsWanted)
			throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{searchData, fieldsWanted}, "BusinessObjectSearchService.findById");
		
		List<BusinessObjectData> dataList = new ArrayList<BusinessObjectData>();
		try {
			String queryString = queryBuilderService.buildQuery(searchData);
			Query query = entityManager.createQuery(queryString);
			List<Object> businessObjects = query.getResultList();
			
			for(Object businessObject : businessObjects){
				BusinessObjectData businessObjectData = new BusinessObjectDataImpl();
				BusinessObjectUtil.copyDataToBusinessObject(businessObjectData, businessObject, fieldsWanted);
				businessObjectData.setBusinessObjectName(searchData.getBusinesObjectName());
				dataList.add(businessObjectData);
			}
			logger.debug("Received {} business objects", businessObjects.size());
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.logAndProcessException(e, ErrorCodes.BOSS_BUSINESS_OBJ_QUERY_ERROR_CD);
		}
		return dataList;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessobject.service.process.BusinessObjectSearchService#findById(com.nathanclaire.alantra.businessobject.util.SearchData)
	 */
	@Override
	public BusinessObjectData findById(String businessObjectName, 
			Integer businessObjectId, List<BusinessObjectFieldData> fieldsWanted) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{businessObjectName, businessObjectId, fieldsWanted}, "BusinessObjectSearchService.findById");
		
		BusinessObjectData businessObjectData = null;
		try {
			ApplicationEntity entity = 
					applicationEntityService.findByName(businessObjectName);
			businessObjectData = new BusinessObjectDataImpl();
			// Get the class of the entity.
			Object businessObject = entityManager.find(EntityUtil.getEntityClass(entity), businessObjectId);
			BusinessObjectUtil.copyDataToBusinessObject(businessObjectData, businessObject, fieldsWanted);
			businessObjectData.setBusinessObjectName(businessObjectName);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BOSS_SINGLE_BUSINESS_OBJ_QUERY_ERROR_CD);
		}
		return businessObjectData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessobject.service.process.BusinessObjectSearchService#findSingle(com.nathanclaire.alantra.businessobject.data.SearchData, java.util.List)
	 */
	@Override
	public BusinessObjectData findSingle(SearchData searchData, List<BusinessObjectFieldData> fieldsWanted)
			throws ApplicationException 
	{
		BusinessObjectData businessObjectData = null;
		try {
			List<BusinessObjectData> listOfBusinessObjectData = this.find(searchData, fieldsWanted);
			// return the first element if the list is not empty
			// else return null
			if (!listOfBusinessObjectData.isEmpty())
				return listOfBusinessObjectData.get(0);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BOSS_SINGLE_BUSINESS_OBJ_QUERY_ERROR_CD);
		}
		return businessObjectData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessobject.service.process.BusinessObjectSearchService#findByCode(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public BusinessObjectData findByCode(String businessObjectName,	String businessObjectCode, 
			List<BusinessObjectFieldData> fieldsWanted) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
}
