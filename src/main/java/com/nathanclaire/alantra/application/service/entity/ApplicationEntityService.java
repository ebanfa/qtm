/**
 * 
 */
package com.nathanclaire.alantra.application.service.entity;

import java.util.List;

import com.nathanclaire.alantra.application.model.ApplicationEntity;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.request.ApplicationEntityRequest;
import com.nathanclaire.alantra.application.response.ApplicationEntityResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa 
 *
 */
public interface ApplicationEntityService extends BaseEntityService<ApplicationEntity, ApplicationEntityResponse, ApplicationEntityRequest> {

	public List<ApplicationEntityField> getFieldsForEntity(String entityName) throws ApplicationException;
}
