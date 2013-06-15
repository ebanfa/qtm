/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * DataInputProcessorsRequest 
 * @author Edward Banfa
 */
public class DataInputProcessorsRequest extends BaseRequest {

    private Integer dataProcessorId;
    private String dataProcessorText;
    private Integer dataInputId;
    private String dataInputText;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public DataInputProcessorsRequest() {
    }

    public Integer getDataProcessorId() {
        return this.dataProcessorId;
    }
    
    public void setDataProcessorId(Integer dataProcessorId) {
        this.dataProcessorId = dataProcessorId;
    }

    public String getDataProcessorText() {
        return this.dataProcessorText;
    }
    
    public void setDataProcessorText(String dataProcessorText) {
        this.dataProcessorText = dataProcessorText;
    }

    public Integer getDataInputId() {
        return this.dataInputId;
    }
    
    public void setDataInputId(Integer dataInputId) {
        this.dataInputId = dataInputId;
    }

    public String getDataInputText() {
        return this.dataInputText;
    }
    
    public void setDataInputText(String dataInputText) {
        this.dataInputText = dataInputText;
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


