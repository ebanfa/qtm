/**
 * 
 */
package com.nathanclaire.alantra.advice.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.nathanclaire.alantra.base.rest.BaseEntityRESTService;
import com.nathanclaire.alantra.advice.model.AdviceTypeTag;
import com.nathanclaire.alantra.advice.rest.request.AdviceTypeTagRequest;
import com.nathanclaire.alantra.advice.service.entity.AdviceTypeTagService;

/**
 * @author administrator
 *
 */
@Path("/advicetypetag")
@Stateless
public class AdviceTypeTagRESTService extends BaseEntityRESTService<AdviceTypeTag, AdviceTypeTagRequest> 
{
	@Inject
	AdviceTypeTagService adviceTypeTagService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getAll(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected List<AdviceTypeTag> getAll(MultivaluedMap<String, String> queryParameters) {
		return adviceTypeTagService.findAll(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getSingleInstance(java.lang.Integer)
	 */
	@Override
	protected AdviceTypeTag getSingleInstance(Integer id) {
		return adviceTypeTagService.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#getInstanceCount(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	protected Map<String, Long> getInstanceCount(
			MultivaluedMap<String, String> queryParameters) {
		return adviceTypeTagService.getCount(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#createInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response createInstance(AdviceTypeTagRequest request) {
		adviceTypeTagService.createInstance(request);
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.rest.BaseEntityRESTService#editInstance(com.nathanclaire.alantra.base.rest.request.BaseRequest)
	 */
	@Override
	protected Response editInstance(AdviceTypeTagRequest request) {
		adviceTypeTagService.updateInstance(request);
		return null;
	}
	
}
