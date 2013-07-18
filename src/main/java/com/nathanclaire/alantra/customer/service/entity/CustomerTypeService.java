/**
 * 
 */
package com.nathanclaire.alantra.customer.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.customer.request.CustomerTypeRequest;
import com.nathanclaire.alantra.customer.response.CustomerTypeResponse;

/**
 * @author Edward Banfa
 *
 */
public interface CustomerTypeService extends BaseEntityService<CustomerType, CustomerTypeResponse, CustomerTypeRequest>
{
	public final String ORG_CUST_TYPE_CODE = "ORGANIZATION";
	public final String INDIVIDUAL_CUST_TYPE_CODE = "INDIVIDUAL";
	public final String PROFESSIONAL = "PROFESSIONAL";


}
