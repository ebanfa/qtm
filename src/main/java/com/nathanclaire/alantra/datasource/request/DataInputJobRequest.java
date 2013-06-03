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

    private Integer dataInputJobStatusId;
    private String dataInputJobStatusText;
    private Integer dataInputJobCategoryId;
    private String dataInputJobCategoryText;
    private Integer dataInputJobTypeId;
    private String dataInputJobTypeText;
    private Integer dataSourceId;
    private String dataSourceText;
    private String name;
    private String description;
    private int diFreqVal;
    private String diFreqCd;
    private Integer id;
    private String code;

    public DataInputJobRequest() {
    }

    public Integer getDataInputJobStatusId() {
        return this.dataInputJobStatusId;
    }
    
    public void setDataInputJobStatusId(Integer dataInputJobStatusId) {
        this.dataInputJobStatusId = dataInputJobStatusId;
    }

    public String getDataInputJobStatusText() {
        return this.dataInputJobStatusText;
    }
    
    public void setDataInputJobStatusText(String dataInputJobStatusText) {
        this.dataInputJobStatusText = dataInputJobStatusText;
    }

    public Integer getDataInputJobCategoryId() {
        return this.dataInputJobCategoryId;
    }
    
    public void setDataInputJobCategoryId(Integer dataInputJobCategoryId) {
        this.dataInputJobCategoryId = dataInputJobCategoryId;
    }

    public String getDataInputJobCategoryText() {
        return this.dataInputJobCategoryText;
    }
    
    public void setDataInputJobCategoryText(String dataInputJobCategoryText) {
        this.dataInputJobCategoryText = dataInputJobCategoryText;
    }

    public Integer getDataInputJobTypeId() {
        return this.dataInputJobTypeId;
    }
    
    public void setDataInputJobTypeId(Integer dataInputJobTypeId) {
        this.dataInputJobTypeId = dataInputJobTypeId;
    }

    public String getDataInputJobTypeText() {
        return this.dataInputJobTypeText;
    }
    
    public void setDataInputJobTypeText(String dataInputJobTypeText) {
        this.dataInputJobTypeText = dataInputJobTypeText;
    }

    public Integer getDataSourceId() {
        return this.dataSourceId;
    }
    
    public void setDataSourceId(Integer dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getDataSourceText() {
        return this.dataSourceText;
    }
    
    public void setDataSourceText(String dataSourceText) {
        this.dataSourceText = dataSourceText;
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


