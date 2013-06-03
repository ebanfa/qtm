/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.datasource.model.TxnDataInputJobStats;
import com.nathanclaire.alantra.datasource.request.TxnDataInputJobStatsRequest;
import com.nathanclaire.alantra.datasource.response.TxnDataInputJobStatsResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TxnDataInputJobStatsServiceImpl 
	extends BaseEntityServiceImpl<TxnDataInputJobStats, TxnDataInputJobStatsResponse, TxnDataInputJobStatsRequest> 
	implements TxnDataInputJobStatsService
{
	private static final String ENTITY_NAME = "TxnDataInputJobStats";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_TXNDATAINPUTJOBSTATS";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_TXNDATAINPUTJOBSTATS";
	
	private Logger logger = LoggerFactory.getLogger(TxnDataInputJobStatsServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public TxnDataInputJobStatsServiceImpl() {
		super(TxnDataInputJobStats.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TxnDataInputJobStats#findById(java.lang.Integer)
	 */
	@Override
	public TxnDataInputJobStats findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TxnDataInputJobStats#findByCode(java.lang.String)
	 */
	@Override
	public TxnDataInputJobStats findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TxnDataInputJobStats#findByName(java.lang.String)
	 */
	@Override
	public TxnDataInputJobStats findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TxnDataInputJobStats#findAll(java.util.Map)
	 */
	@Override
	public List<TxnDataInputJobStats> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TxnDataInputJobStats#createTxnDataInputJobStats(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public TxnDataInputJobStats create(TxnDataInputJobStatsRequest txnDataInputJobStatsRequest) throws ApplicationException {
		return createInstance(txnDataInputJobStatsRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TxnDataInputJobStats#deleteTxnDataInputJobStats(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.TxnDataInputJobStats#updateTxnDataInputJobStats(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public TxnDataInputJobStats update(TxnDataInputJobStatsRequest txnDataInputJobStatsRequest) throws ApplicationException {
		return updateInstance(txnDataInputJobStatsRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode() throws ApplicationException {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode() throws ApplicationException {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName() throws ApplicationException {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields() throws ApplicationException {
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() 
	 throws ApplicationException {
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(TxnDataInputJobStats txndatainputjobstats: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(txndatainputjobstats.getId(), txndatainputjobstats.getCode(), txndatainputjobstats.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public TxnDataInputJobStats convertRequestToModel(TxnDataInputJobStatsRequest txnDataInputJobStatsRequest) 
     throws ApplicationException {
		TxnDataInputJobStats txnDataInputJobStats = new TxnDataInputJobStats();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(txnDataInputJobStatsRequest, txnDataInputJobStats, allowedEntityFields);
    	//Process many to one relationships
		return txnDataInputJobStats;
	}
	
	@Override
	public TxnDataInputJobStatsResponse convertModelToResponse(TxnDataInputJobStats model) throws ApplicationException {
		if (model == null) return null;
		TxnDataInputJobStatsResponse txnDataInputJobStatsResponse = new TxnDataInputJobStatsResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, txnDataInputJobStatsResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return txnDataInputJobStatsResponse;
	}
}
