/**
 * 
 */
package com.nathanclaire.alantra.product.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.product.model.ProductCategoryType;
import com.nathanclaire.alantra.product.rest.request.ProductCategoryTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ProductCategoryTypeServiceImpl extends BaseEntityServiceImpl<ProductCategoryType, ProductCategoryTypeRequest> implements ProductCategoryTypeService
{
	/**
	 * @param entityClass
	 */
	public ProductCategoryTypeServiceImpl() {
		super(ProductCategoryType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategoryType#findById(java.lang.Integer)
	 */
	@Override
	public ProductCategoryType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategoryType#findByCode(java.lang.String)
	 */
	@Override
	public ProductCategoryType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategoryType#findByName(java.lang.String)
	 */
	@Override
	public ProductCategoryType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategoryType#findAll(java.util.Map)
	 */
	@Override
	public List<ProductCategoryType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategoryType#createProductCategoryType(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductCategoryType createInstance(ProductCategoryTypeRequest productCategoryTypeRequest) {
		return createInsance(productCategoryTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategoryType#deleteProductCategoryType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.product.service.ProductCategoryType#updateProductCategoryType(com.nathanclaire.alantra.product.rest.request.ServiceRequest)
	 */
	@Override
	public ProductCategoryType updateInstance(ProductCategoryTypeRequest productCategoryTypeRequest) {
		return updateInstance(productCategoryTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ProductCategoryType loadModelFromRequest(ProductCategoryTypeRequest productCategoryTypeRequest) 
    {
		ProductCategoryType productCategoryType = new ProductCategoryType();
    	Integer productCategoryTypeId = productCategoryTypeRequest.getId();
    	// Are we editing a ProductCategoryType
    	if(productCategoryTypeId != null) 
    	{
    		productCategoryType = getEntityManager().find(ProductCategoryType.class, productCategoryTypeRequest.getId());
    		productCategoryType.setLastModifiedDt(productCategoryTypeRequest.getLastModifiedDt());
        	productCategoryType.setLastModifiedUsr(getCurrentUserName(productCategoryTypeRequest));
    	}
    	else
    	{
        	productCategoryType.setCreatedDt(getCurrentSystemDate());
        	productCategoryType.setCreatedByUsr(getCurrentUserName(productCategoryTypeRequest));
    	}
    	productCategoryType.setCode(productCategoryTypeRequest.getCode());
    	productCategoryType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	productCategoryType.setName(productCategoryTypeRequest.getName()); 
    	productCategoryType.setDescription(productCategoryTypeRequest.getDescription()); 
    	productCategoryType.setCode(productCategoryTypeRequest.getCode()); 
    	productCategoryType.setEffectiveDt(productCategoryTypeRequest.getEffectiveDt()); 
    	productCategoryType.setRecSt(productCategoryTypeRequest.getRecSt()); 
		return productCategoryType;
	}
}
