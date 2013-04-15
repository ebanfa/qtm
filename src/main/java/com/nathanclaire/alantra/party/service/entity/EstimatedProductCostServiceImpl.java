/**
 * 
 */
package com.nathanclaire.alantra.party.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.party.model.EstimatedProductCost;
import com.nathanclaire.alantra.party.rest.request.EstimatedProductCostRequest;

import com.nathanclaire.alantra.product.model.Product;
import com.nathanclaire.alantra.product.model.CostComponentType;
import com.nathanclaire.alantra.product.model.ProductFeature;
import com.nathanclaire.alantra.businessdata.model.GeoBoundry;

/**
 * @author administrator
 *
 */
@Stateless
public class EstimatedProductCostServiceImpl extends BaseEntityServiceImpl<EstimatedProductCost, EstimatedProductCostRequest> implements EstimatedProductCostService
{
	/**
	 * @param entityClass
	 */
	public EstimatedProductCostServiceImpl() {
		super(EstimatedProductCost.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.EstimatedProductCost#findById(java.lang.Integer)
	 */
	@Override
	public EstimatedProductCost findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.EstimatedProductCost#findByCode(java.lang.String)
	 */
	@Override
	public EstimatedProductCost findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.EstimatedProductCost#findByName(java.lang.String)
	 */
	@Override
	public EstimatedProductCost findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.EstimatedProductCost#findAll(java.util.Map)
	 */
	@Override
	public List<EstimatedProductCost> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.EstimatedProductCost#createEstimatedProductCost(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public EstimatedProductCost createInstance(EstimatedProductCostRequest estimatedProductCostRequest) {
		return createInsance(estimatedProductCostRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.EstimatedProductCost#deleteEstimatedProductCost(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.party.service.EstimatedProductCost#updateEstimatedProductCost(com.nathanclaire.alantra.party.rest.request.ServiceRequest)
	 */
	@Override
	public EstimatedProductCost updateInstance(EstimatedProductCostRequest estimatedProductCostRequest) {
		return updateInstance(estimatedProductCostRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected EstimatedProductCost loadModelFromRequest(EstimatedProductCostRequest estimatedProductCostRequest) 
    {
		EstimatedProductCost estimatedProductCost = new EstimatedProductCost();
    	Integer estimatedProductCostId = estimatedProductCostRequest.getId();
    	// Are we editing a EstimatedProductCost
    	if(estimatedProductCostId != null) 
    	{
    		estimatedProductCost = getEntityManager().find(EstimatedProductCost.class, estimatedProductCostRequest.getId());
    		estimatedProductCost.setLastModifiedDt(estimatedProductCostRequest.getLastModifiedDt());
        	estimatedProductCost.setLastModifiedUsr(getCurrentUserName(estimatedProductCostRequest));
    	}
    	else
    	{
        	estimatedProductCost.setCreatedDt(getCurrentSystemDate());
        	estimatedProductCost.setCreatedByUsr(getCurrentUserName(estimatedProductCostRequest));
    	}
    	estimatedProductCost.setCode(estimatedProductCostRequest.getCode());
    	estimatedProductCost.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (estimatedProductCostRequest.getProduct() != null)
    	{
    		Product product = getEntityManager().find(Product.class, estimatedProductCostRequest.getProduct());
    		estimatedProductCost.setProduct(product);
    	}
    	if (estimatedProductCostRequest.getCostComponentType() != null)
    	{
    		CostComponentType costComponentType = getEntityManager().find(CostComponentType.class, estimatedProductCostRequest.getCostComponentType());
    		estimatedProductCost.setCostComponentType(costComponentType);
    	}
    	if (estimatedProductCostRequest.getProductFeature() != null)
    	{
    		ProductFeature productFeature = getEntityManager().find(ProductFeature.class, estimatedProductCostRequest.getProductFeature());
    		estimatedProductCost.setProductFeature(productFeature);
    	}
    	if (estimatedProductCostRequest.getGeoBoundry() != null)
    	{
    		GeoBoundry geoBoundry = getEntityManager().find(GeoBoundry.class, estimatedProductCostRequest.getGeoBoundry());
    		estimatedProductCost.setGeoBoundry(geoBoundry);
    	}
    	estimatedProductCost.setCost(estimatedProductCostRequest.getCost()); 
    	estimatedProductCost.setDescription(estimatedProductCostRequest.getDescription()); 
    	estimatedProductCost.setFromDt(estimatedProductCostRequest.getFromDt()); 
    	estimatedProductCost.setToDt(estimatedProductCostRequest.getToDt()); 
    	estimatedProductCost.setCode(estimatedProductCostRequest.getCode()); 
    	estimatedProductCost.setEffectiveDt(estimatedProductCostRequest.getEffectiveDt()); 
    	estimatedProductCost.setRecSt(estimatedProductCostRequest.getRecSt()); 
		return estimatedProductCost;
	}
}
