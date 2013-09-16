/**
 * 
 */
package com.nathanclaire.alantra.businessobject.service.process;

import java.util.ArrayList;
import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectSearchInfo;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectSearchServiceImpl implements
		BusinessObjectSearchService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessobject.service.process.BusinessObjectSearchService#find(com.nathanclaire.alantra.businessobject.util.BusinessObjectSearchInfo)
	 */
	@Override
	public List<BusinessObjectData> find(BusinessObjectSearchInfo searchInfo)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return new ArrayList<BusinessObjectData>();
	}

}
