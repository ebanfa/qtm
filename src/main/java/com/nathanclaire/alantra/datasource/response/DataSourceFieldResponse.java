/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataSourceFieldResponse 
 * @author Edward Banfa
 */
public class DataSourceFieldResponse extends BaseResponse {

    private Integer dataSourceStructureId;
    private String dataSourceStructureText;
    private Integer dataSourceFieldTypeId;
    private String dataSourceFieldTypeText;
    private String dsFieldTarget;

    public DataSourceFieldResponse() {
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

    public String getDsFieldTarget() {
        return this.dsFieldTarget;
    }
    
    public void setDsFieldTarget(String dsFieldTarget) {
        this.dsFieldTarget = dsFieldTarget;
    }


}


