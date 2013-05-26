/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * DataInputJobRequest 
 * @author Edward Banfa
 */
public class DataInputJobRequest extends BaseRequest {

    private Integer id;
    private Integer dataSourceId;
    private String code;
    private String name;
    private String description;
    private int diFreqVal;
    private String diFreqCd;

    public DataInputJobRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDataSourceId() {
        return this.dataSourceId;
    }
    
    public void setDataSourceId(Integer dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
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

    public int getDiFreqVal() {
        return this.diFreqVal;
    }
    
    public void setDiFreqVal(int diFreqVal) {
        this.diFreqVal = diFreqVal;
    }

    public String getDiFreqCd() {
        return this.diFreqCd;
    }
    
    public void setDiFreqCd(String diFreqCd) {
        this.diFreqCd = diFreqCd;
    }


}


