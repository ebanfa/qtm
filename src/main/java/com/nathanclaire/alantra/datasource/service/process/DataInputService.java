/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.loading.DataLoader;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataInput;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataInputJobStatus;
import com.nathanclaire.alantra.datasource.model.DataInputJobType;

/**
 * @author Edward Banfa
 *
 */
public interface DataInputService {
	
	public Boolean isCyclicDataInputJob(DataInputJob inputJob) throws ApplicationException;
	
	public Boolean isNonCyclicDataInputJob(DataInputJob inputJob) throws ApplicationException;
	
	public void validateDataInputJob(DataInputJob inputJob) throws ApplicationException;

	public List<DataInputJob> getJobsByStatus(String statusCode) throws ApplicationException;
	
	public DataInputJob getDataInputJob(String jobCode) throws ApplicationException;
	
	public DataInputJob getDataInputJob(Integer inputJobId) throws ApplicationException;
	
	public DataInputJobType getDataInputJobType(String jobTypeCode) throws ApplicationException;
	
	public DataInputJobStatus getDataInputJobStatus(String statusCode) throws ApplicationException;
	
	public List<DataInputJob> getAllRunnableJobs() throws ApplicationException;
	
	public List<DataInputJob> getAllRunningJobs() throws ApplicationException;
	
	public Boolean isReadyToRun(DataInputJob inputJob) throws ApplicationException;
	
	public DataInput getDataInput(String dataInputCode) throws ApplicationException;
	
	public DataInput createDataInput(Data data, DataChannel channel, String name, String code ) throws ApplicationException;
	
	public DataInputJob createDataInputJob(DataInputJobType dataInputJobType, DataInputJobStatus inputJobStatus, 
			DataInput dataInput, String name, String code, String diFreqCode, Integer diFreqValue) throws ApplicationException;
	
	

}
