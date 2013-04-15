/**
 * 
 */
package com.nathanclaire.alantra.product.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.product.model.ProductFeatureType;
import com.nathanclaire.alantra.product.rest.request.ProductFeatureTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ProductFeatureTypeServiceImpl extends BaseEntityServiceImpl<ProductFeatureType, ProductFeatureTypeRequest> implements ProductFeatureTypeService
{
	/**
	 * @param entityClass
	 */
	public ProductFeatureTypeServiceImpl() {
		super(ProductFeatureType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureType#findById(java.lang.Integer)
	 */
	@Override
	public ProductFeatureType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureType#findByCode(java.lang.String)
	 */
	@Override
	public ProductFeatureType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureType#findByName(java.lang.String)
	 */
	@Override
	public ProductFeatureType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureType#findAll(java.util.Map)
	 */
	@Override
	public List<ProductFeatureType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureType#createProductFeatureType(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductFeatureType createInstance(ProductFeatureTypeRequest productFeatureTypeRequest) {
		return createInsance(productFeatureTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureType#deleteProductFeatureType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductFeatureType#updateProductFeatureType(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductFeatureType updateInstance(ProductFeatureTypeRequest productFeatureTypeRequest) {
		return updateInstance(productFeatureTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ProductFeatureType loadModelFromRequest(ProductFeatureTypeRequest productFeatureTypeRequest) 
    {
		ProductFeatureType productFeatureType = new ProductFeatureType();
    	Integer productFeatureTypeId = productFeatureTypeRequest.getId();
    	// Are we editing a ProductFeatureType
    	if(productFeatureTypeId != null) 
    	{
    		productFeatureType = getEntityManager().find(ProductFeatureType.class, productFeatureTypeRequest.getId());
    		productFeatureType.setLastModifiedDt(productFeatureTypeRequest.getLastModifiedDt());
        	productFeatureType.setLastModifiedUsr(getCurrentUserName(productFeatureTypeRequest));
    	}
    	else
    	{
        	productFeatureType.setCreatedDt(getCurrentSystemDate());
        	productFeatureType.setCreatedByUsr(getCurrentUserName(productFeatureTypeRequest));
    	}
    	productFeatureType.setCode(productFeatureTypeRequest.getCode());
    	productFeatureType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	productFeatureType.setName(productFeatureTypeRequest.getName()); 
    	productFeatureType.setDescription(productFeatureTypeRequest.getDescription()); 
    	productFeatureType.setCode(productFeatureTypeRequest.getCode()); 
    	productFeatureType.setEffectiveDt(productFeatureTypeRequest.getEffectiveDt()); 
    	productFeatureType.setRecSt(productFeatureTypeRequest.getRecSt()); 
		return productFeatureType;
	}
}
