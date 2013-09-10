/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extraction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.annotation.etl.EmailDataExtractor;
import com.nathanclaire.alantra.datasource.etl.util.CellData;
import com.nathanclaire.alantra.datasource.etl.util.RowDataLite;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataField;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.util.TableDataUtil;
import com.nathanclaire.alantra.messaging.annotation.messenger.POP3Messenger;
import com.nathanclaire.alantra.messaging.messenger.MessengerService;
import com.nathanclaire.alantra.messaging.util.MessageLite;


/**
 * @author Edward Banfa 
 *
 */
@Stateless
@EmailDataExtractor
public class EmailDataExtractorImpl  extends BaseDataExtractor<String> implements DataExtractor {

	@Inject @POP3Messenger MessengerService messengerService;
	private Logger logger = LoggerFactory.getLogger(EmailDataExtractorImpl.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#extractData(com.nathanclaire.alantra.datasource.model.Data, com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	@Override
	protected TableData extractData(DataChannel channel, Data data, TableData tableDataToBePopulated) throws ApplicationException 
	{
		logger.debug("Extracting data from {} for data channel {}", data, channel);
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[] {channel, data, tableDataToBePopulated, (data!=null) ? data.getDataStructure(): null});
		try 
		{
			DataStructure dataStructure = data.getDataStructure();
			List<String[]> extractedData = this.emailToStringArrayList(messengerService.getMessages(channel));
			int recordsRead = processRows(dataStructure, 
					dataStructure.getDataFields(), tableDataToBePopulated, extractedData);
			tableDataToBePopulated.setRecordsRead(recordsRead);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.EMAIL_DATA_EXTRACTOR_ERROR_CD);
		}
		return tableDataToBePopulated;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processStringDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellData processStringDataField(DataField dataField, CellData cellData, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		if(StringUtil.isValidString(data))
			cellData.setData(data);
		else
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, INVALID_STRING_VALUE, NO_VALUE_PROVIDED);
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processIntegerDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellData processIntegerDataField(DataField dataField, CellData cellData, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		if(StringUtil.isValidString(data))
		{
			try {
		        cellData.setData(new Integer(data));
			} catch (Exception e) {
				TableDataUtil.handleCellReadError(data, cellData, USR_INVALID_INTEGER_STRING, e.getMessage());
			}
		}
		else
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, USR_INVALID_INTEGER_STRING, NO_VALUE_PROVIDED);
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processDecimalDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellData processDecimalDataField(DataField dataField, CellData cellData, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		// Clean decimal fields
		if(StringUtil.isValidString(data))
		{
			data = StringUtil.cleanDecimalString(data);
			try {
				BigDecimal cellValueBigDecimal = new BigDecimal(data);
		        cellData.setData(cellValueBigDecimal);
			} catch (Exception e) {
				TableDataUtil.handleCellReadError(data, cellData, USR_INVALID_DECIMAL_STRING, e.getMessage());
			}
		}
		else
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, USR_INVALID_DECIMAL_STRING, NO_VALUE_PROVIDED);
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processDateDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellData processDateDataField(DataField dataField, CellData cellData, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		if(StringUtil.isValidString(data)) {
			cellData.setData(DateUtil.parseDateString(dataField.getFieldFormat(), data));
		}
		else 
        	TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, INVALID_DATE_VALUE, INVALID_CELL_DATA_TYPE);
		return cellData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#processRelationshipDataField(com.nathanclaire.alantra.datasource.model.DataField, java.lang.Object, com.nathanclaire.alantra.datasource.etl.RowDataLite)
	 */
	@Override
	protected CellData processRelationshipDataField(DataField dataField, CellData cellData, String data, RowDataLite currentRow) 
			throws ApplicationException 
	{
		if(StringUtil.isValidString(data))
			cellData.setData(data);
		else
			TableDataUtil.handleCellReadError(StringUtil.EMPTY_STRING, cellData, INVALID_STRING_VALUE, NO_VALUE_PROVIDED);
		return cellData;
	}

	/**
	 * @param messages
	 * @return
	 */
	private List<String[]> emailToStringArrayList(List<MessageLite> messages)
	{
		List<String[]> messageRows = new ArrayList<String[]>();
		for (MessageLite message : messages) 
		{
			String[] messageAsRow = 
				{ message.getMessageId(), message.getMessageFrom(), message.getMessageTo(), message.getSubjectLine(), 
					message.getMessageBody(), message.getAttachementMimeType(),	message.getAttachementFileName() };

			messageRows.add(messageAsRow);
		}
		return messageRows;
	}
}
