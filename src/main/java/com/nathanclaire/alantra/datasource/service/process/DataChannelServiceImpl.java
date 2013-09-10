/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelCategoryService;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelTypeService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataChannelServiceImpl extends BaseProcessService implements DataChannelService 
{
	@Inject DataChannelTypeService channelTypeService;
	@Inject DataChannelCategoryService channelCategoryService;
	@Inject com.nathanclaire.alantra.datasource.service.entity.DataChannelEntityService channelEntityService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataChannelService#getDataChannel(java.lang.String)
	 */
	@Override
	public DataChannel getDataChannel(String dataChannelCode) throws ApplicationException {
		return (DataChannel) EntityUtil.returnOrThrowIfNull(
				channelEntityService.findByCode(dataChannelCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataChannel");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataChannelService#getDataChannelType(java.lang.String)
	 */
	@Override
	public DataChannelType getDataChannelType(String typeCode) throws ApplicationException {
		return (DataChannelType) EntityUtil.returnOrThrowIfNull(
				channelTypeService.findByCode(typeCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataChannelType");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataChannelService#getDataChannelCategory(java.lang.String)
	 */
	@Override
	public DataChannelCategory getDataChannelCategory(String categoryCode) throws ApplicationException {
		return (DataChannelCategory) EntityUtil.returnOrThrowIfNull(
				channelCategoryService.findByCode(categoryCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataChannelCategory");
	}

}
