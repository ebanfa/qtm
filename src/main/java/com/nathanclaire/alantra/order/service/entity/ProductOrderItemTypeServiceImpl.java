/**
 * 
 */
package com.nathanclaire.alantra.order.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.order.model.ProductOrderItemType;
import com.nathanclaire.alantra.order.request.ProductOrderItemTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ProductOrderItemTypeServiceImpl extends BaseEntityServiceImpl<ProductOrderItemType, ProductOrderItemTypeRequest> implements ProductOrderItemTypeService
{
	/**
	 * @param entityClass
	 */
	public ProductOrderItemTypeServiceImpl() {
		super(ProductOrderItemType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItemType#findById(java.lang.Integer)
	 */
	@Override
	public ProductOrderItemType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItemType#findByCode(java.lang.String)
	 */
	@Override
	public ProductOrderItemType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItemType#findByName(java.lang.String)
	 */
	@Override
	public ProductOrderItemType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItemType#findAll(java.util.Map)
	 */
	@Override
	public List<ProductOrderItemType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItemType#createProductOrderItemType(com.nathanclaire.alantra.order.rest.request.ServiceRequest)
	 */
	@Override
	public ProductOrderItemType create(ProductOrderItemTypeRequest productOrderItemTypeRequest) {
		return createInstance(productOrderItemTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItemType#deleteProductOrderItemType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.order.service.ProductOrderItemType#updateProductOrderItemType(com.nathanclaire.alantra.order.rest.request.ServiceRequest)
	 */
	@Override
	public ProductOrderItemType update(ProductOrderItemTypeRequest productOrderItemTypeRequest) {
		return updateInstance(productOrderItemTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ProductOrderItemType loadModelFromRequest(ProductOrderItemTypeRequest productOrderItemTypeRequest) 
    {
		ProductOrderItemType productOrderItemType = new ProductOrderItemType();
    	Integer productOrderItemTypeId = productOrderItemTypeRequest.getId();
    	// Are we editing a ProductOrderItemType
    	if(productOrderItemTypeId != null) 
    	{
    		productOrderItemType = getEntityManager().find(ProductOrderItemType.class, productOrderItemTypeRequest.getId());
    		productOrderItemType.setLastModifiedDt(productOrderItemTypeRequest.getLastModifiedDt());
        	productOrderItemType.setLastModifiedUsr(getCurrentUserName(productOrderItemTypeRequest));
    	}
    	else
    	{
        	productOrderItemType.setCreatedDt(getCurrentSystemDate());
        	productOrderItemType.setCreatedByUsr(getCurrentUserName(productOrderItemTypeRequest));
    	}
    	productOrderItemType.setCode(productOrderItemTypeRequest.getCode());
    	productOrderItemType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	productOrderItemType.setName(productOrderItemTypeRequest.getName()); 
    	productOrderItemType.setDescription(productOrderItemTypeRequest.getDescription()); 
    	productOrderItemType.setCode(productOrderItemTypeRequest.getCode()); 
    	productOrderItemType.setEffectiveDt(productOrderItemTypeRequest.getEffectiveDt()); 
    	productOrderItemType.setRecSt(productOrderItemTypeRequest.getRecSt()); 
		return productOrderItemType;
	}
}
