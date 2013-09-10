/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extraction;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.annotation.etl.CSVDataExtractor;
import com.nathanclaire.alantra.datasource.annotation.etl.EmailDataExtractor;
import com.nathanclaire.alantra.datasource.annotation.etl.ExcelDataExtractor;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.model.DataInput;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelAdapterService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class DataExtractorProducerImpl implements DataExtractorProducer {
	
	@Inject @CSVDataExtractor DataExtractor csvDataExtractor;
	@Inject @ExcelDataExtractor DataExtractor excelDataExtractor;
	@Inject @EmailDataExtractor DataExtractor emailDataExtractor;

	@Override
	public DataExtractor getDataExtractor(DataInput dataInput)	throws ApplicationException {
		com.nathanclaire.alantra.datasource.model.DataChannelAdapter dataExtractor = getDataExtractorConfig(dataInput);
		if(dataExtractor.getCode().equals(DataChannelAdapterService.CSV_FILE_ADAPTER))
			return csvDataExtractor;
		if(dataExtractor.getCode().equals(DataChannelAdapterService.EXCEL_FILE_ADAPTER))
			return excelDataExtractor;
		if(dataExtractor.getCode().equals(DataChannelAdapterService.SMTP_POP_ADAPTER))
			return emailDataExtractor;
		return null;
	}

	/**
	 * @param dataInput
	 * @return
	 */
	private com.nathanclaire.alantra.datasource.model.DataChannelAdapter getDataExtractorConfig(
			DataInput dataInput) {
		DataChannel dataSource = dataInput.getDataChannel();
		DataChannelType dataSourceType = dataSource.getDataChannelType();
		com.nathanclaire.alantra.datasource.model.DataChannelAdapter dataExtractor = dataSourceType.getDataChannelAdapter();
		return dataExtractor;
	}

}
