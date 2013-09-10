/**
 * 
 */
package com.nathanclaire.alantra.customer.service.entity;

import java.util.ArrayList;
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

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerCategory;
import com.nathanclaire.alantra.customer.model.CustomerClassification;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.customer.request.CustomerRequest;
import com.nathanclaire.alantra.customer.response.CustomerResponse;

/**
 * @author Edward Banfa
 *
 */
@Stateless(name="CustomerEntityServiceImpl")
public class CustomerServiceImpl 
	extends BaseEntityServiceImpl<Customer, CustomerResponse, CustomerRequest> 
	implements CustomerService
{

	public static final String PRIM_PHONE_CRITERIA = "primaryMobile";
	public static final String SEC_PHONE_CRITERIA = "secondaryMobile";
	public static final String PRIM_EMAIL_CRITERIA = "primaryEmail";
	public static final String SEC_EMAIL_CRITERIA = "secondaryEmail";
	
	private static final String LIST_ITEM_CUSTOMERCLASSIFICATION = "customerClassification";
	private static final String LIST_ITEM_CUSTOMERTYPE = "customerType";
	private static final String ENTITY_NAME = "Customer";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMER";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMER";
	
	private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Inject	CustomerTypeService  customerTypeService;
	@Inject	CustomerCategoryService  customerCategoryService;
	@Inject	ApplicationEntityService  applicationEntityService;
	@Inject	CustomerClassificationService  customerClassificationService;
	
	/**
	 * @param entityClass
	 */
	public CustomerServiceImpl() {
		super(Customer.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#findById(java.lang.Integer)
	 */
	@Override
	public Customer findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#findByCode(java.lang.String)
	 */
	@Override
	public Customer findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#findByName(java.lang.String)
	 */
	@Override
	public Customer findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.entity.CustomerService#findByIds(java.util.List)
	 */
	@Override
	public List<Customer> findByIds(List<Integer> idsOfCustomers) throws ApplicationException {
		List<Customer> customers = new ArrayList<Customer>();
		for(Integer id : idsOfCustomers)
			customers.add(findById(id));
		return customers;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.entity.CustomerService#findByType(java.lang.String)
	 */
	@Override
	public List<Customer> findByType(String code) throws ApplicationException {
		CustomerType customerType = customerTypeService.findByCode(code);
		List<Customer> customers = new ArrayList<Customer>();
		customers.addAll(customerType.getCustomers());
		return customers;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.entity.CustomerService#findByCategory(java.lang.String)
	 */
	@Override
	public List<Customer> findByCategory(String code) throws ApplicationException {
		List<Customer> customers = new ArrayList<Customer>();
		CustomerCategory customerCategory = customerCategoryService.findByCode(code);
		for(CustomerType customerType: customerCategory.getCustomerTypes())
			customers.addAll(customerType.getCustomers());
		return customers;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.entity.CustomerService#findByClassification(java.lang.String)
	 */
	@Override
	public List<Customer> findByClassification(String code) throws ApplicationException {
		CustomerClassification customerClassification = customerClassificationService.findByCode(code);
		List<Customer> customers = new ArrayList<Customer>();
		customers.addAll(customerClassification.getCustomers());
		return customers;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#findAll(java.util.Map)
	 */
	@Override
	public List<Customer> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#createCustomer(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public Customer create(CustomerRequest customerRequest) throws ApplicationException {
		return createInstance(customerRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#deleteCustomer(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#updateCustomer(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public Customer update(CustomerRequest customerRequest) throws ApplicationException {
		return updateInstance(customerRequest);
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
		List<ListItemResponse> customerClassifications = customerClassificationService.asListItem();
		List<ListItemResponse> customerTypes = customerTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_CUSTOMERCLASSIFICATION, customerClassifications); 
		listItems.put(LIST_ITEM_CUSTOMERTYPE, customerTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Customer customer: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customer.getId(), customer.getCode(), customer.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Customer convertRequestToModel(CustomerRequest customerRequest) 
     throws ApplicationException {
		Customer customer = new Customer();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(customerRequest, customer, allowedEntityFields);
    	//Process many to one relationships
    	if (customerRequest.getCustomerClassificationId() != null)
    	{
    		CustomerClassification customerClassification = getEntityManager().find(CustomerClassification.class, customerRequest.getCustomerClassificationId());
    		customer.setCustomerClassification(customerClassification);
    	}
    	if (customerRequest.getCustomerTypeId() != null)
    	{
    		CustomerType customerType = getEntityManager().find(CustomerType.class, customerRequest.getCustomerTypeId());
    		customer.setCustomerType(customerType);
    	}
		return customer;
	}
	
	@Override
	public CustomerResponse convertModelToResponse(Customer model) throws ApplicationException {
		if (model == null) return null;
		CustomerResponse customerResponse = new CustomerResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, customerResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCustomerClassification() != null)
			customerResponse.setCustomerClassificationId(model.getCustomerClassification().getId());
			customerResponse.setCustomerClassificationText(model.getCustomerClassification().getName());
		if(model.getCustomerType() != null)
			customerResponse.setCustomerTypeId(model.getCustomerType().getId());
			customerResponse.setCustomerTypeText(model.getCustomerType().getName());
		return customerResponse;
	}

	/**
	 * @param sourceAddress
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public  Customer findCustomerByPhone(String sourceAddress) throws ApplicationException 
	{
		Map<String, String> criteria = new HashMap<String, String>();
		// First we search by primary phone number
		criteria.put(PRIM_PHONE_CRITERIA, sourceAddress);
		List<Customer> results = findByCriteria(criteria);
		if(!results.isEmpty())
			return results.get(0);
		// If the search came up empty we try to search by the secondary phone number
		criteria.clear();
		criteria.put(SEC_PHONE_CRITERIA, sourceAddress);
		results = findByCriteria(criteria);
		if(!results.isEmpty())
			return results.get(0);
		// The search is hopeless so return null
		return null;
	}

	/**
	 * @param sourceAddress
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public Customer findCustomerByEmail(String sourceAddress) throws ApplicationException 
	{
		Map<String, String> criteria = new HashMap<String, String>();
		// First we search by primary phone number
		criteria.put(PRIM_EMAIL_CRITERIA, sourceAddress);
		List<Customer> results = findByCriteria(criteria);
		if(!results.isEmpty())
			return results.get(0);
		// If the search came up empty we try to search by the secondary phone number
		criteria.clear();
		criteria.put(SEC_EMAIL_CRITERIA, sourceAddress);
		results = findByCriteria(criteria);
		if(!results.isEmpty())
			return results.get(0);
		// The search is hopeless so return null
		return null;
	}
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl#extractPredicates(javax.ws.rs.core.MultivaluedMap, javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Root)
	 */
	@Override
	protected Predicate[] extractPredicates(
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<Customer> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("customerClassification")) {
            Integer customerClassificationId = Integer.valueOf(queryParameters.getFirst("customerClassification"));
            predicates.add(criteriaBuilder.equal(root.get("customerClassification").get("id"), customerClassificationId));
        }
        if (queryParameters.containsKey("customerType")) {
            Integer customerTypeId = Integer.valueOf(queryParameters.getFirst("customerType"));
            predicates.add(criteriaBuilder.equal(root.get("customerType").get("id"), customerTypeId));
        }
		if (queryParameters.containsKey("pin")) {
            String pin = queryParameters.getFirst("pin");
            predicates.add(criteriaBuilder.equal(root.get("pin"), pin));
        }
		if (queryParameters.containsKey("name")) {
            String name = queryParameters.getFirst("name");
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
		if (queryParameters.containsKey("primaryEmail")) {
            String primaryEmail = queryParameters.getFirst("primaryEmail");
            predicates.add(criteriaBuilder.equal(root.get("primaryEmail"), primaryEmail));
        }
		if (queryParameters.containsKey("secondaryEmail")) {
            String secondaryEmail = queryParameters.getFirst("secondaryEmail");
            predicates.add(criteriaBuilder.equal(root.get("secondaryEmail"), secondaryEmail));
        }
		if (queryParameters.containsKey("primaryMobile")) {
            String primaryMobile = queryParameters.getFirst("primaryMobile");
            predicates.add(criteriaBuilder.equal(root.get("primaryMobile"), primaryMobile));
        }
		if (queryParameters.containsKey("secondaryMobile")) {
            String secondaryMobile = queryParameters.getFirst("secondaryMobile");
            predicates.add(criteriaBuilder.equal(root.get("secondaryMobile"), secondaryMobile));
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
