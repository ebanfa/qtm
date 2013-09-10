/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * DataRequest 
 * @author Edward Banfa
 */
public class DataRequest extends BaseRequest {

    /**
	 * @param dataTypeId
	 * @param dataStructureId
	 * @param name
	 * @param code
	 */
	public DataRequest(Integer dataTypeId, Integer dataStructureId,
			String name, String code) {
		super();
		this.dataTypeId = dataTypeId;
		this.dataStructureId = dataStructureId;
		this.name = name;
		this.code = code;
	}

	private Integer dataTypeId;
    private String dataTypeText;
    private Integer dataStructureId;
    private String dataStructureText;
    private Integer dataChannelId;
    private String dataChannelText;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public DataRequest() {
    }

    public Integer getDataTypeId() {
        return this.dataTypeId;
    }
    
    public void setDataTypeId(Integer dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getDataTypeText() {
        return this.dataTypeText;
    }
    
    public void setDataTypeText(String dataTypeText) {
        this.dataTypeText = dataTypeText;
    }

    public Integer getDataStructureId() {
        return this.dataStructureId;
    }
    
    public void setDataStructureId(Integer dataStructureId) {
        this.dataStructureId = dataStructureId;
    }

    public String getDataStructureText() {
        return this.dataStructureText;
    }
    
    public void setDataStructureText(String dataStructureText) {
        this.dataStructureText = dataStructureText;
    }

    public Integer getDataChannelId() {
        return this.dataChannelId;
    }
    
    public void setDataChannelId(Integer dataChannelId) {
        this.dataChannelId = dataChannelId;
    }

    public String getDataChannelText() {
        return this.dataChannelText;
    }
    
    public void setDataChannelText(String dataChannelText) {
        this.dataChannelText = dataChannelText;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }


}


