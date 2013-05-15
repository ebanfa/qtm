/**
 * 
 */
package com.nathanclaire.alantra.application.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.application.model.ApplicationActivity;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.model.ApplicationRelatedActivity;
import com.nathanclaire.alantra.application.request.ApplicationRelatedActivityRequest;
import com.nathanclaire.alantra.application.response.ApplicationRelatedActivityResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ApplicationRelatedActivityServiceImpl 
	extends BaseEntityServiceImpl<ApplicationRelatedActivity, ApplicationRelatedActivityResponse, ApplicationRelatedActivityRequest> 
	implements ApplicationRelatedActivityService
{
	
	private static final String ENTITY_NAME = "ApplicationRelatedActivity";
	private static final String LIST_ACTIVITY_CODE = "LIST_APPLICATIONRELATEDACTIVITY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_APPLICATIONRELATEDACTIVITY";
	
	private static final String LIST_ITEM_SOURCEAPPLICATIONACTIVITY = "sourceApplicationActivity";
	private static final String LIST_ITEM_DESTINATIONAPPLICATIONACTIVITY = "destinationApplicationActivity";
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ApplicationActivityService  applicationActivityService;
	
	/**
	 * @param entityClass
	 */
	public ApplicationRelatedActivityServiceImpl() {
		super(ApplicationRelatedActivity.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationRelatedActivity#findById(java.lang.Integer)
	 */
	@Override
	public ApplicationRelatedActivity findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationRelatedActivity#findByCode(java.lang.String)
	 */
	@Override
	public ApplicationRelatedActivity findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationRelatedActivity#findByName(java.lang.String)
	 */
	@Override
	public ApplicationRelatedActivity findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationRelatedActivity#findAll(java.util.Map)
	 */
	@Override
	public List<ApplicationRelatedActivity> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationRelatedActivity#createApplicationRelatedActivity(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationRelatedActivity create(ApplicationRelatedActivityRequest applicationRelatedActivityRequest) {
		return createInstance(applicationRelatedActivityRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationRelatedActivity#deleteApplicationRelatedActivity(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.ApplicationRelatedActivity#updateApplicationRelatedActivity(com.nathanclaire.alantra.application.rest.request.ServiceRequest)
	 */
	@Override
	public ApplicationRelatedActivity update(ApplicationRelatedActivityRequest applicationRelatedActivityRequest) {
		return updateInstance(applicationRelatedActivityRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode() {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode() {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields() {
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() 
	{
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
		List<ListItemResponse> sourceApplicationActivitys = applicationActivityService.asListItem();
		List<ListItemResponse> destinationApplicationActivitys = applicationActivityService.asListItem();
    	
		listItems.put(LIST_ITEM_SOURCEAPPLICATIONACTIVITY, sourceApplicationActivitys); 
		listItems.put(LIST_ITEM_DESTINATIONAPPLICATIONACTIVITY, destinationApplicationActivitys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		for(ApplicationRelatedActivity applicationrelatedactivity: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(applicationrelatedactivity.getId(), applicationrelatedactivity.getCode(), null);
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ApplicationRelatedActivity convertRequestToModel(ApplicationRelatedActivityRequest applicationRelatedActivityRequest) 
    {
		ApplicationRelatedActivity applicationRelatedActivity = new ApplicationRelatedActivity();
		applicationRelatedActivity = this.loadDefaultFieldsFromRequest(applicationRelatedActivity, applicationRelatedActivityRequest);
    	//Process many to one relationships
    	/*if (applicationRelatedActivityRequest.getSourceApplicationActivity() != null)
    	{
    		ApplicationActivity sourceApplicationActivity = getEntityManager().find(ApplicationActivity.class, applicationRelatedActivityRequest.getSourceApplicationActivity());
    		applicationRelatedActivity.setSourceApplicationActivity(sourceApplicationActivity);
    	}
    	if (applicationRelatedActivityRequest.getDestinationApplicationActivity() != null)
    	{
    		ApplicationActivity destinationApplicationActivity = getEntityManager().find(ApplicationActivity.class, applicationRelatedActivityRequest.getDestinationApplicationActivity());
    		applicationRelatedActivity.setDestinationApplicationActivity(destinationApplicationActivity);
    	}*/
    	applicationRelatedActivity.setRelActSeq(applicationRelatedActivityRequest.getRelActSeq()); 
    	applicationRelatedActivity.setCode(applicationRelatedActivityRequest.getCode()); 
    	applicationRelatedActivity.setRecSt(applicationRelatedActivityRequest.getRecSt()); 
		return applicationRelatedActivity;
	}
	
	@Override
	public ApplicationRelatedActivityResponse convertModelToResponse(ApplicationRelatedActivity model) {
		if (model == null) return null;
		ApplicationRelatedActivityResponse applicationRelatedActivityResponse = new ApplicationRelatedActivityResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, applicationRelatedActivityResponse, allowedEntityFields);
		return applicationRelatedActivityResponse;
	}
}
