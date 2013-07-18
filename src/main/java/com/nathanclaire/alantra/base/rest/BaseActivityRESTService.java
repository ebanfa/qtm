/**
 * 
 */
package com.nathanclaire.alantra.base.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.model.ApplicationRelatedActivity;
import com.nathanclaire.alantra.application.response.ApplicationActivityResponse;
import com.nathanclaire.alantra.application.response.ApplicationEntityFieldResponse;
import com.nathanclaire.alantra.application.response.ApplicationRelatedActivityResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationActivityService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityFieldService;
import com.nathanclaire.alantra.application.service.entity.ApplicationRelatedActivityService;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.response.BaseActivityResponse;
import com.nathanclaire.alantra.base.response.EditActivityResponse;
import com.nathanclaire.alantra.base.response.ListActivityResponse;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.Messages;
import com.nathanclaire.alantra.base.util.StringUtil;

/**
 * <p>
 *   A number of RESTful services implement GET operations on a particular type of entity. For
 *   observing the DRY principle, the generic operations are implemented in the <code>BaseActivityRESTService</code>
 *   class, and the other services can inherit from here.
 * </p>
 *
 * <p>
 *    Subclasses will declare a base path using the JAX-RS {@link Path} annotation, for example:
 * </p>
 *
 * <pre>
 * <code>
 * &#064;Path("/widgets")
 * public class WidgetService extends BaseActivityRESTService<Widget> {
 * ...
 * }
 * </code>
 * </pre>
 *
 * <p>
 *   will support the following methods:
 * </p>
 *
 * <pre>
 * <code>
 *   GET /widgets
 *   GET /widgets/:id
 *   GET /widgets/count
 * </code>
 * </pre>
 *
 *  <p>
 *     Subclasses may specify various criteria for filtering entities when retrieving a list of them, by supporting
 *     custom query parameters. Pagination is supported by default through the query parameters <code>first</code>
 *     and <code>maxResults</code>.
 * </p>
 *
 * <p>
 *     The class is abstract because it is not intended to be used directly, but subclassed by actual JAX-RS
 *     endpoints.
 * </p>
 *
 */
public abstract class BaseActivityRESTService<T,V> {

	private static final String ACTIVITY_NOT_FOUND = "BaseActivityRESTService.ACTIVITY_NOT_FOUND";
	private static final String EDIT_ACTIVITY_NOT_FOUND = "BaseActivityRESTService.LIST_EDIT_NOT_FOUND";
	private static final String LIST_ACTIVITY_NOT_FOUND = "BaseActivityRESTService.LIST_ACTIVITY_NOT_FOUND";

	@Inject
	ApplicationRelatedActivityService applicationRelatedActivityService;
	
	@Inject
	ApplicationActivityService applicationActivityService;
	@Inject
	ApplicationEntityFieldService applicationEntityFieldService;;

    /**
     * Default constructor
     */
    public BaseActivityRESTService() {}
    
    /**
     * <p>
     *     A method for retrieving the list activity of an entity.
     * </p>
     * @param id entity id
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ListActivityResponse<T> getListActivityWithEntityInstances(@Context UriInfo uriInfo) 
    {
		return getListActivityResponse(uriInfo.getPathParameters());
    }
    
    /**
     * <p>
     *     A method for retrieving individual entity instances.
     * </p>
     * @param id entity id
     * @return
     */
    @GET
    @Path("/single/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public EditActivityResponse<T> getEditActivityWithEntityInstance(@PathParam("id") Integer id) {
    	return getEditActivityResponse(id);
    }

    @GET
    @Path("/searchFields")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ApplicationEntityFieldResponse> getEntitySearchFields(@QueryParam("entityName") String entityName)
    {
    	List<ApplicationEntityFieldResponse> fieldResponses = new ArrayList<ApplicationEntityFieldResponse>();
    	try {
    		List<ApplicationEntityField> fields = applicationEntityFieldService.getEntitySearchFields(entityName);
    		for(ApplicationEntityField field: fields)
    			fieldResponses.add(applicationEntityFieldService.convertModelToResponse(field));
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return fieldResponses;
    }
    
    /**
     * <p>
     *     A method for retrieving individual entity instances.
     * </p>
     * @param id entity id
     * @return
     */
    @GET
    @Path("/single")
    @Produces(MediaType.APPLICATION_JSON)
    public EditActivityResponse<T> getEditActivityWithoutEntityInstance() {
		return getEditActivityResponse(null);
    }

    /**
     * @param request
     * @return
     */
    @POST 
    @Path("/single")
    @Consumes(MediaType.APPLICATION_JSON)
    public EditActivityResponse<T> create(V request) 
    {
    	EditActivityResponse<T> response = null;
    	try {
    		BaseRequest baseRequest = (BaseRequest) request;
    		baseRequest.setCreatedDt(new Date());
    		baseRequest.setCreatedByUsr("System");
    		response = this.saveEntityInstance(request);
		} 
		catch (Exception e) {
			response = this.getEditActivityResponse(null);
			processActivityResponseException(response, e);
		}
    	return response;
    }
    
    /**
     * <p>  Edit a Host. Data is contained in the HostRequest object </p>
     * @param request
     * @return
     */
    @PUT 
    @Path("/single/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    public EditActivityResponse<T> edit(V request) 
    {
    	EditActivityResponse<T> response = null;
		try {
    		BaseRequest baseRequest = (BaseRequest) request;
    		baseRequest.setLastModifiedDt(new Date());
			response = this.saveEditedEntityInstance(request);
		} 
		catch (Exception e) {
			response = this.getEditActivityResponse(null);
			processActivityResponseException(response, e);
		}
		return response;
    }
    
    /**
     * <p>
     *     A method for retrieving individual entity instances.
     * </p>
     * @param id entity id
     * @return
     */
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public ListActivityResponse<T> delete(List<Integer> idsOfEntitiesToDelete) {
    	ListActivityResponse<T> response = null;
		try {
			response = this.deleteEntityInstances(idsOfEntitiesToDelete);
		} 
		catch (Exception e) {
			response = this.getListActivityResponse(null);
			processActivityResponseException(response, e);
		}
		return response;
    }
    /**
     * @param uriInfo
     * @return
     */
    @GET
    @Path("/relatedlistitems")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, List<ListItemResponse>> getRelatedEntitiesAsListItems(@Context UriInfo uriInfo)
    {
    	Map<String, List<ListItemResponse>> relatedEntitiesAsListItems = new HashMap<String, List<ListItemResponse>>();
    	try {
			return this.prepareRelatedEntitiesListItems(uriInfo.getQueryParameters());
		} 
		catch (ApplicationException e) {
			e.printStackTrace();
		}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return relatedEntitiesAsListItems;
    }

	/**
	 * @param queryParameters
	 * @return
	 * @throws ApplicationException
	 */
    protected ListActivityResponse<T> getListActivityResponse(MultivaluedMap<String, String> queryParameters){
		ListActivityResponse<T> response = null;
		ApplicationActivityResponse activity = null;
		try {
			activity = 
					applicationActivityService.convertModelToResponse(applicationActivityService.findByCode(getListActivityCode()));
			if(activity == null)
				throw new ApplicationException(LIST_ACTIVITY_NOT_FOUND);
			response = populateListActivityResponse(activity, prepareListActivityResponse(activity, queryParameters), queryParameters);
		} 
		catch (Exception e) 
		{
			response = prepareListActivityResponse(activity, queryParameters);
			processActivityResponseException(response, e);
		}
		return response;
	}

	/**
	 * @param id
	 * @return
	 */
	protected EditActivityResponse<T> getEditActivityResponse(Integer id){
		EditActivityResponse<T> response = null;
		ApplicationActivityResponse activity = null;
		try {
			activity = applicationActivityService.convertModelToResponse(
					applicationActivityService.findByCode(getEditActivityCode()));
			if(activity == null)
				throw new ApplicationException(EDIT_ACTIVITY_NOT_FOUND);
			return populateEditActivityResponse(id, activity, prepareEditActivityResponse(activity));
		} 
		catch (Exception e) 
		{
			response = prepareEditActivityResponse(activity);
			processActivityResponseException(response, e);
		}
		return response;
	}

	/**
	 * @param activity
	 * @param e
	 * @return
	 */
	protected BaseActivityResponse processActivityResponseException(
			BaseActivityResponse response, Exception e) {
		response.setErrorCode(1);
		response.setErrorMessage(e.getMessage());
		return response;
	}
    
    /**
     * Prepare the list activity response object that will be returned
     * @param activity
     * @param queryParameters
     * @return
     */
    protected ListActivityResponse<T> prepareListActivityResponse(
    		ApplicationActivityResponse activity, MultivaluedMap<String, String> queryParameters)
    {
    	ListActivityResponse<T> response = new ListActivityResponse<T>();
    	prepareActivityResponse(response, activity);
    	return response;
    }
    
    /**
     * Prepare the edit activity response object.
     * @param activity
     * @return
     */
    protected EditActivityResponse<T> prepareEditActivityResponse(ApplicationActivityResponse activity)
    {
		EditActivityResponse<T> response = new EditActivityResponse<T>();
		prepareActivityResponse(response, activity);
    	return response;
    }

    /**
     * Load all the related activities for the given activity. The related activities will each be converted
     * to ActivityResponse objects.
     * @param activity
     * @return
     */
    protected List<ApplicationRelatedActivityResponse> getRelatedActivities(ApplicationActivityResponse activity)
    {
    	List<ApplicationRelatedActivityResponse> relatedActivityResponses = new ArrayList<ApplicationRelatedActivityResponse>();
    	try {
			List<ApplicationRelatedActivity> relatedActivities = applicationRelatedActivityService.getRelatedActivities(activity.getCode());
			for (ApplicationRelatedActivity relatedActivity : relatedActivities)
			{
				relatedActivityResponses.add(applicationRelatedActivityService.convertModelToResponse(relatedActivity));
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
    	return relatedActivityResponses;
    }
    
    /**
     * Populate the list activity with data.
     * @param activity
     * @param response
     * @param queryParameters
     * @return
     */
    protected abstract ListActivityResponse<T> populateListActivityResponse(ApplicationActivityResponse activity, 
    		ListActivityResponse<T> response, MultivaluedMap<String, String> queryParameters ) throws ApplicationException;
    
    /**
     * NOTE: Make this method abstract!!!
     * @param id
     * @return
     */
    protected abstract EditActivityResponse<T> populateEditActivityResponse(Integer id,	ApplicationActivityResponse activity,
			EditActivityResponse<T> response) throws ApplicationException;
    
    /**
     * NOTE: Make this method abstract
     * @param entityInstance
     * @return
     */
    protected abstract EditActivityResponse<T> saveEntityInstance(V entityInstance) throws ApplicationException;
    
    /**
     * NOTE: Make this method abstract
     * @param entityInstance
     * @return
     */
    protected abstract EditActivityResponse<T> saveEditedEntityInstance(V entityInstance) throws ApplicationException;

    /**
     * NOTE: Make this method abstract
     * @param entityInstance
     * @return
     */
    protected abstract ListActivityResponse<T> deleteEntityInstances(List<Integer> idsOfEntitiesToDelete) throws ApplicationException;
    
    /**
     * @param multivaluedMap
     * @return
     */
    protected abstract Map<String, List<ListItemResponse>> prepareRelatedEntitiesListItems(MultivaluedMap<String, String> multivaluedMap)
     throws ApplicationException;
    
    /**
     * Get the activity code for the list activity
     * @return
     */
    protected abstract String getListActivityCode() throws ApplicationException;
    
    /**
     * Get the activity code for the edit activity
     * @return
     */
    protected abstract String getEditActivityCode() throws ApplicationException;
    
    /**
     * Utility method to prepare an activity response object.
     * @param response
     * @param activity
     */
    private void prepareActivityResponse(BaseActivityResponse response, ApplicationActivityResponse activity)
    {
    	if(response == null)
    		response = new BaseActivityResponse();
    	if (activity != null)
    	{
        	response.setId(activity.getId());
        	response.setName(activity.getName());
        	response.setDisplayNm(activity.getDisplayNm());
        	response.setCode(activity.getCode());
        	response.setActivityUrl(activity.getActivityUrl());
        	response.setRelatedActivities(getRelatedActivities(activity));
    	}
    	else 
    	{
        	response.setId(-1);
        	response.setName(Messages.getString(ACTIVITY_NOT_FOUND));
        	response.setCode(StringUtil.EMPTY_STRING);
        	response.setActivityUrl(StringUtil.EMPTY_STRING);
        	response.setRelatedActivities(new ArrayList<ApplicationRelatedActivityResponse>());
    	}
    }
    
    /**
     * @param parameterName
     * @param queryParameters
     * @return
     */
    protected String extractParameter(String parameterName, MultivaluedMap<String, String> queryParameters)
    {
    	if (queryParameters.containsKey(parameterName)) 
    	{
            return queryParameters.getFirst(parameterName);
    	}
		return "";
	}
    
}
