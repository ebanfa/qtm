/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.joda.time.DateTime; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.request.DataInputJobRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobResponse;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.datasource.model.DataInputJobStatus;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobStatusService;
import com.nathanclaire.alantra.datasource.model.DataInputJobType;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobTypeService;
import com.nathanclaire.alantra.datasource.model.DataInput;
import com.nathanclaire.alantra.datasource.service.entity.DataInputEntityService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataInputJobServiceImpl 
	extends BaseEntityServiceImpl<DataInputJob, DataInputJobResponse, DataInputJobRequest> 
	implements DataInputJobService
{

private static final String LIST_ITEM_DATAINPUTJOBSTATUS = "dataInputJobStatus";
private static final String LIST_ITEM_DATAINPUTJOBTYPE = "dataInputJobType";
private static final String LIST_ITEM_DATAINPUT = "dataInput";
	private static final String ENTITY_NAME = "DataInputJob";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAINPUTJOB";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAINPUTJOB";
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject DataInputJobStatusService  dataInputJobStatusService;
	@Inject DataInputJobTypeService  dataInputJobTypeService;
	@Inject DataInputEntityService  dataInputService;
	
	/**
	 * @param entityClass
	 */
	public DataInputJobServiceImpl() {
		super(DataInputJob.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.dataInputJob.service.DataInputJob#findById(java.lang.Integer)
	 */
	@Override
	public DataInputJob findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.dataInputJob.service.DataInputJob#findByCode(java.lang.String)
	 */
	@Override
	public DataInputJob findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#findByName(java.lang.String)
	 */
	@Override
	public DataInputJob findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#findAll(java.util.Map)
	 */
	@Override
	public List<DataInputJob> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#createDataInputJob(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJob create(DataInputJobRequest dataInputJobRequest) throws ApplicationException {
		return createInstance(dataInputJobRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#deleteDataInputJob(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#updateDataInputJob(com.nathanclaire.alantra.dataInputJob.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJob update(DataInputJobRequest dataInputJobRequest) throws ApplicationException {
		return updateInstance(dataInputJobRequest);
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
        List<ListItemResponse> dataInputJobStatuss = dataInputJobStatusService.asListItem();
        List<ListItemResponse> dataInputJobTypes = dataInputJobTypeService.asListItem();
        List<ListItemResponse> dataInputs = dataInputService.asListItem();

        listItems.put(LIST_ITEM_DATAINPUTJOBSTATUS, dataInputJobStatuss);
        listItems.put(LIST_ITEM_DATAINPUTJOBTYPE, dataInputJobTypes);
        listItems.put(LIST_ITEM_DATAINPUT, dataInputs);
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataInputJob dataInputJob: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(dataInputJob.getId(), dataInputJob.getCode(), dataInputJob.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataInputJob convertRequestToModel(DataInputJobRequest dataInputJobRequest) 
     throws ApplicationException {
		DataInputJob dataInputJob = new DataInputJob();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(dataInputJobRequest, dataInputJob, allowedEntityFields);
    	//Process many to one relationships
        if (dataInputJobRequest.getDataInputJobStatusId() != null)
    	{
    		DataInputJobStatus dataInputJobStatus = getEntityManager().find(DataInputJobStatus.class, dataInputJobRequest.getDataInputJobStatusId());
    		dataInputJob.setDataInputJobStatus(dataInputJobStatus);
    	}
        if (dataInputJobRequest.getDataInputJobTypeId() != null)
    	{
    		DataInputJobType dataInputJobType = getEntityManager().find(DataInputJobType.class, dataInputJobRequest.getDataInputJobTypeId());
    		dataInputJob.setDataInputJobType(dataInputJobType);
    	}
        if (dataInputJobRequest.getDataInputId() != null)
    	{
    		DataInput dataInput = getEntityManager().find(DataInput.class, dataInputJobRequest.getDataInputId());
    		dataInputJob.setDataInput(dataInput);
    	}
		return dataInputJob;
	}
	
	@Override
	public DataInputJobResponse convertModelToResponse(DataInputJob model) throws ApplicationException {
		if (model == null) return null;
		DataInputJobResponse dataInputJobResponse = new DataInputJobResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, dataInputJobResponse, allowedEntityFields);
		if(model.getDataInputJobStatus() != null)
			dataInputJobResponse.setDataInputJobStatusId(model.getDataInputJobStatus().getId());
			dataInputJobResponse.setDataInputJobStatusText(model.getDataInputJobStatus().getName());
		if(model.getDataInputJobType() != null)
			dataInputJobResponse.setDataInputJobTypeId(model.getDataInputJobType().getId());
			dataInputJobResponse.setDataInputJobTypeText(model.getDataInputJobType().getName());
		if(model.getDataInput() != null)
			dataInputJobResponse.setDataInputId(model.getDataInput().getId());
			dataInputJobResponse.setDataInputText(model.getDataInput().getName());
		return dataInputJobResponse;
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param dataInputJobTypeId
	 * @param amount
	 */
	private void initializeQueryParameters(Integer dataInputJobStatusId, Integer dataInputJobTypeId, Integer dataInputId, String name, String description, Date startTs, Date endTs, Date nextRunTs, Integer diFreqVal, String diFreqCd, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(dataInputJobStatusId != null)
            queryParameters.add("dataInputJobStatus", dataInputJobStatusId.toString());
        if(dataInputJobTypeId != null)
            queryParameters.add("dataInputJobType", dataInputJobTypeId.toString());
        if(dataInputId != null)
            queryParameters.add("dataInput", dataInputId.toString());
        if(name != null)
            queryParameters.add("name", name.toString());
        if(description != null)
            queryParameters.add("description", description.toString());
        if(startTs != null)
            queryParameters.add("startTs", startTs.toString());
        if(endTs != null)
            queryParameters.add("endTs", endTs.toString());
        if(nextRunTs != null)
            queryParameters.add("nextRunTs", nextRunTs.toString());
        if(diFreqVal != null)
            queryParameters.add("diFreqVal", diFreqVal.toString());
        if(diFreqCd != null)
            queryParameters.add("diFreqCd", diFreqCd.toString());
        if(id != null)
            queryParameters.add("id", id.toString());
        if(code != null)
            queryParameters.add("code", code.toString());
        if(effectiveDt != null)
            queryParameters.add("effectiveDt", effectiveDt.toString());
        if(recSt != null)
            queryParameters.add("recSt", recSt.toString());
        if(versionNo != null)
            queryParameters.add("versionNo", versionNo.toString());
        if(rowTs != null)
            queryParameters.add("rowTs", rowTs.toString());
        if(createdDt != null)
            queryParameters.add("createdDt", createdDt.toString());
        if(createdByUsr != null)
            queryParameters.add("createdByUsr", createdByUsr.toString());
        if(lastModifiedDt != null)
            queryParameters.add("lastModifiedDt", lastModifiedDt.toString());
        if(lastModifiedUsr != null)
            queryParameters.add("lastModifiedUsr", lastModifiedUsr.toString());
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl#extractPredicates(javax.ws.rs.core.MultivaluedMap, javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Root)
	 */
	@Override
	protected Predicate[] extractPredicates(
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<DataInputJob> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("dataInputJobStatus")) {
            Integer dataInputJobStatusId = Integer.valueOf(queryParameters.getFirst("dataInputJobStatus"));
            predicates.add(criteriaBuilder.equal(root.get("dataInputJobStatus").get("id"), dataInputJobStatusId));
        }
        if (queryParameters.containsKey("dataInputJobStatus.code")) {
            String dataInputJobStatusCode = String.valueOf(queryParameters.getFirst("dataInputJobStatus.code"));
            predicates.add(criteriaBuilder.equal(root.get("dataInputJobStatus").get("code"), dataInputJobStatusCode));
        }
        if (queryParameters.containsKey("dataInputJobType")) {
            Integer dataInputJobTypeId = Integer.valueOf(queryParameters.getFirst("dataInputJobType"));
            predicates.add(criteriaBuilder.equal(root.get("dataInputJobType").get("id"), dataInputJobTypeId));
        }
        if (queryParameters.containsKey("dataInput")) {
            Integer dataInputId = Integer.valueOf(queryParameters.getFirst("dataInput"));
            predicates.add(criteriaBuilder.equal(root.get("dataInput").get("id"), dataInputId));
        }
		if (queryParameters.containsKey("name")) {
            String name = queryParameters.getFirst("name");
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
		if (queryParameters.containsKey("description")) {
            String description = queryParameters.getFirst("description");
            predicates.add(criteriaBuilder.equal(root.get("description"), description));
        }
		if (queryParameters.containsKey("startTs")) {
			DateTime startTs = new DateTime(queryParameters.getFirst("startTs"));
            predicates.add(criteriaBuilder.equal(root.get("startTs"), startTs.toDate()));
        }
		if (queryParameters.containsKey("endTs")) {
			DateTime endTs = new DateTime(queryParameters.getFirst("endTs"));
            predicates.add(criteriaBuilder.equal(root.get("endTs"), endTs.toDate()));
        }
		if (queryParameters.containsKey("nextRunTs")) {
			DateTime nextRunTs = new DateTime(queryParameters.getFirst("nextRunTs"));
            predicates.add(criteriaBuilder.equal(root.get("nextRunTs"), nextRunTs.toDate()));
        }
		if (queryParameters.containsKey("diFreqVal")) {
            Integer diFreqVal = Integer.valueOf(queryParameters.getFirst("diFreqVal"));
            predicates.add(criteriaBuilder.equal(root.get("diFreqVal"), diFreqVal));
        }
		if (queryParameters.containsKey("diFreqCd")) {
            String diFreqCd = queryParameters.getFirst("diFreqCd");
            predicates.add(criteriaBuilder.equal(root.get("diFreqCd"), diFreqCd));
        }
		if (queryParameters.containsKey("id")) {
            Integer id = Integer.valueOf(queryParameters.getFirst("id"));
            predicates.add(criteriaBuilder.equal(root.get("id"), id));
        }
		if (queryParameters.containsKey("code")) {
            String code = queryParameters.getFirst("code");
            predicates.add(criteriaBuilder.equal(root.get("code"), code));
        }
		if (queryParameters.containsKey("effectiveDt")) {
			DateTime effectiveDt = new DateTime(queryParameters.getFirst("effectiveDt"));
            predicates.add(criteriaBuilder.equal(root.get("effectiveDt"), effectiveDt.toDate()));
        }
		if (queryParameters.containsKey("recSt")) {
            String recSt = queryParameters.getFirst("recSt");
            predicates.add(criteriaBuilder.equal(root.get("recSt"), recSt));
        }
		if (queryParameters.containsKey("versionNo")) {
            Integer versionNo = Integer.valueOf(queryParameters.getFirst("versionNo"));
            predicates.add(criteriaBuilder.equal(root.get("versionNo"), versionNo));
        }
		if (queryParameters.containsKey("rowTs")) {
			DateTime rowTs = new DateTime(queryParameters.getFirst("rowTs"));
            predicates.add(criteriaBuilder.equal(root.get("rowTs"), rowTs.toDate()));
        }
		if (queryParameters.containsKey("createdDt")) {
			DateTime createdDt = new DateTime(queryParameters.getFirst("createdDt"));
            predicates.add(criteriaBuilder.equal(root.get("createdDt"), createdDt.toDate()));
        }
		if (queryParameters.containsKey("createdByUsr")) {
            String createdByUsr = queryParameters.getFirst("createdByUsr");
            predicates.add(criteriaBuilder.equal(root.get("createdByUsr"), createdByUsr));
        }
		if (queryParameters.containsKey("lastModifiedDt")) {
			DateTime lastModifiedDt = new DateTime(queryParameters.getFirst("lastModifiedDt"));
            predicates.add(criteriaBuilder.equal(root.get("lastModifiedDt"), lastModifiedDt.toDate()));
        }
		if (queryParameters.containsKey("lastModifiedUsr")) {
            String lastModifiedUsr = queryParameters.getFirst("lastModifiedUsr");
            predicates.add(criteriaBuilder.equal(root.get("lastModifiedUsr"), lastModifiedUsr));
        }
        return predicates.toArray(new Predicate[]{});
	}
}