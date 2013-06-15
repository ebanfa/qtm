/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataExtractor;
import com.nathanclaire.alantra.datasource.request.DataExtractorRequest;
import com.nathanclaire.alantra.datasource.response.DataExtractorResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataExtractorService extends BaseEntityService<DataExtractor, DataExtractorResponse, DataExtractorRequest>
{

	final String CSV_DATA_EXTRACTOR = "CSV_DATA_EXTRACTOR";
	final String EXCEL_DATA_EXTRACTOR = "EXCEL_DATA_EXTRACTOR";
	final String EMAIL_DATA_EXTRACTOR = "EMAIL_DATA_EXTRACTOR";
	
}
