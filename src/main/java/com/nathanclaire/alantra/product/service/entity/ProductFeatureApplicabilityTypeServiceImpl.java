/**
 * 
 */
package com.nathanclaire.alantra.product.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.product.model.ProductFeatureApplicabilityType;
import com.nathanclaire.alantra.product.request.ProductFeatureApplicabilityTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ProductFeatureApplicabilityTypeServiceImpl extends BaseEntityServiceImpl<ProductFeatureApplicabilityType, ProductFeatureApplicabilityTypeRequest> implements ProductFeatureApplicabilityTypeService
{
	/**
	 * @param entityClass
	 */
	public ProductFeatureApplicabilityTypeServiceImpl() {
		super(ProductFeatureApplicabilityType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicabilityType#findById(java.lang.Integer)
	 */
	@Override
	public ProductFeatureApplicabilityType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicabilityType#findByCode(java.lang.String)
	 */
	@Override
	public ProductFeatureApplicabilityType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicabilityType#findByName(java.lang.String)
	 */
	@Override
	public ProductFeatureApplicabilityType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicabilityType#findAll(java.util.Map)
	 */
	@Override
	public List<ProductFeatureApplicabilityType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicabilityType#createProductFeatureApplicabilityType(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductFeatureApplicabilityType create(ProductFeatureApplicabilityTypeRequest productFeatureApplicabilityTypeRequest) {
		return createInstance(productFeatureApplicabilityTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicabilityType#deleteProductFeatureApplicabilityType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureApplicabilityType#updateProductFeatureApplicabilityType(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductFeatureApplicabilityType update(ProductFeatureApplicabilityTypeRequest productFeatureApplicabilityTypeRequest) {
		return updateInstance(productFeatureApplicabilityTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ProductFeatureApplicabilityType loadModelFromRequest(ProductFeatureApplicabilityTypeRequest productFeatureApplicabilityTypeRequest) 
    {
		ProductFeatureApplicabilityType productFeatureApplicabilityType = new ProductFeatureApplicabilityType();
    	Integer productFeatureApplicabilityTypeId = productFeatureApplicabilityTypeRequest.getId();
    	// Are we editing a ProductFeatureApplicabilityType
    	if(productFeatureApplicabilityTypeId != null) 
    	{
    		productFeatureApplicabilityType = getEntityManager().find(ProductFeatureApplicabilityType.class, productFeatureApplicabilityTypeRequest.getId());
    		productFeatureApplicabilityType.setLastModifiedDt(productFeatureApplicabilityTypeRequest.getLastModifiedDt());
        	productFeatureApplicabilityType.setLastModifiedUsr(getCurrentUserName(productFeatureApplicabilityTypeRequest));
    	}
    	else
    	{
        	productFeatureApplicabilityType.setCreatedDt(getCurrentSystemDate());
        	productFeatureApplicabilityType.setCreatedByUsr(getCurrentUserName(productFeatureApplicabilityTypeRequest));
    	}
    	productFeatureApplicabilityType.setCode(productFeatureApplicabilityTypeRequest.getCode());
    	productFeatureApplicabilityType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	productFeatureApplicabilityType.setName(productFeatureApplicabilityTypeRequest.getName()); 
    	productFeatureApplicabilityType.setDescription(productFeatureApplicabilityTypeRequest.getDescription()); 
    	productFeatureApplicabilityType.setCode(productFeatureApplicabilityTypeRequest.getCode()); 
    	productFeatureApplicabilityType.setEffectiveDt(productFeatureApplicabilityTypeRequest.getEffectiveDt()); 
    	productFeatureApplicabilityType.setRecSt(productFeatureApplicabilityTypeRequest.getRecSt()); 
		return productFeatureApplicabilityType;
	}
}
