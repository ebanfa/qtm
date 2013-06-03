/**
 * 
 */
package com.nathanclaire.alantra.application.service.entity;

import java.util.List;

import com.nathanclaire.alantra.application.model.ApplicationActivityGroup;
import com.nathanclaire.alantra.application.request.ApplicationActivityGroupRequest;
import com.nathanclaire.alantra.application.response.ApplicationActivityGroupResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
public interface ApplicationActivityGroupService extends BaseEntityService<ApplicationActivityGroup, ApplicationActivityGroupResponse, ApplicationActivityGroupRequest>
{
	public List<ApplicationActivityGroup> findGroupsInModule(Integer moduleId) throws ApplicationException;
}
