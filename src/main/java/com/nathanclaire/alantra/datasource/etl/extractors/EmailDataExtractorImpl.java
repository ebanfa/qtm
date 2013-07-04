/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.extractors;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.CellData;
import com.nathanclaire.alantra.datasource.etl.RowData;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.service.process.DataInputJobRunnerImpl;
import com.nathanclaire.alantra.messaging.annotation.POP3Messenger;
import com.nathanclaire.alantra.messaging.messenger.Messenger;
import com.nathanclaire.alantra.messaging.util.MessageLite;


/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class EmailDataExtractorImpl  extends BaseDataExtractor<String> implements EmailDataExtractor {

	@Inject @POP3Messenger Messenger messenger;
	private Logger logger = LoggerFactory.getLogger(EmailDataExtractorImpl.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.extractors.BaseDataExtractor#extractData(com.nathanclaire.alantra.datasource.model.Data, com.nathanclaire.alantra.datasource.etl.TableData)
	 */
	@Override
	protected TableData extractData(Data data, TableData tableDataToBePopulated) throws ApplicationException {

		DataChannel dataChannel = data.getDataChannel();
		DataStructure dataStructure = data.getDataStructure();
		logger.debug("Extracting data from {} for data channel {}", data.getCode(), dataChannel.getCode());
		List<MessageLite> messages = messenger.getMessages(dataChannel);
		logger.debug("Extracted {} messages", messages);
		List<String[]> extractedData = this.emailToStringArrayList(messages);
		logger.debug("Email messages as String list {}", extractedData);
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
