/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extractors;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.CellData;
import com.nathanclaire.alantra.datasource.etl.RowData;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.messaging.service.process.mail.MailService;
import com.nathanclaire.alantra.messaging.util.MessageLite;


/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class EmailDataExtractorImpl  extends BaseDataExtractor<String> implements EmailDataExtractor {

	@Inject MailService mailService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#extractData(com.nathanclaire.alantra.datasource.model.Data, com.nathanclaire.alantra.datasource.etl.TableData)
	 */
	@Override
	protected TableData extractData(Data data, TableData tableDataToBePopulated) throws ApplicationException {

		DataChannel dataChannel = data.getDataChannel();
		DataStructure dataStructure = data.getDataStructure();
		
		List<MessageLite> messages = mailService.getMessages(dataChannel);
		List<String[]> extractedData = this.emailToStringArrayList(messages);
		tableDataToBePopulated.setSourceChannelText(getDataChannelCategory(data).getCode());
		int recordsRead = processRows(dataStructure, dataStructure.getDataFields(), tableDataToBePopulated, extractedData);
		tableDataToBePopulated.setRecordsRead(recordsRead);
		
		return tableDataToBePopulated;
	}

	@Override
	protected void getCellData(String cellName, String dataType, String data, RowData currentRow) {
		CellData cellData = new CellData();
		cellData.setName(cellName);
		cellData.setDataType(dataType);
		/*// Clean decimal fields
		if(dataType.equals(DataFieldTypeService.DECIMAL))
			data = cleanDecimal(data);*/
		cellData.setData((String) data);
		currentRow.getColumns().add(cellData);
	}
	

	/**
	 * @param messages
	 * @return
	 */
	private List<String[]> emailToStringArrayList(List<MessageLite> messages)
	{
		List<String[]> messageRows = new ArrayList<String[]>();
		for(MessageLite message : messages)
		{
			String[] messageAsRow = {message.getMessageId(), message.getMessageFrom(), message.getMessageTo(), 
					message.getSubjectLine(), message.getMessageBody(), message.getAttachementMimeType(), message.getAttachementFileName()};

			messageRows.add(messageAsRow);
		}
		return messageRows;
	}
}
