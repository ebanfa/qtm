/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * DataSourceFieldRequest 
 * @author Edward Banfa
 */
public class DataSourceFieldRequest extends BaseRequest {

    private Integer dataSourceStructureId;
    private String dataSourceStructureText;
    private Integer dataSourceFieldTypeId;
    private String dataSourceFieldTypeText;
    private String name;
    private String description;
    private String dsFieldTarget;
    private Integer id;
    private String code;

    public DataSourceFieldRequest() {
    }

    public Integer getDataSourceStructureId() {
        return this.dataSourceStructureId;
    }
    
    public void setDataSourceStructureId(Integer dataSourceStructureId) {
        this.dataSourceStructureId = dataSourceStructureId;
    }

    public String getDataSourceStructureText() {
        return this.dataSourceStructureText;
    }
    
    public void setDataSourceStructureText(String dataSourceStructureText) {
        this.dataSourceStructureText = dataSourceStructureText;
    }

    public Integer getDataSourceFieldTypeId() {
        return this.dataSourceFieldTypeId;
    }
    
    public void setDataSourceFieldTypeId(Integer dataSourceFieldTypeId) {
        this.dataSourceFieldTypeId = dataSourceFieldTypeId;
    }

    public String getDataSourceFieldTypeText() {
        return this.dataSourceFieldTypeText;
    }
    
    public void setDataSourceFieldTypeText(String dataSourceFieldTypeText) {
        this.dataSourceFieldTypeText = dataSourceFieldTypeText;
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

    public String getDsFieldTarget() {
        return this.dsFieldTarget;
    }
    
    public void setDsFieldTarget(String dsFieldTarget) {
        this.dsFieldTarget = dsFieldTarget;
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


