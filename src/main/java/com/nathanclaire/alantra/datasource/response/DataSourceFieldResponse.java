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
    private Integer dataSourceFieldTypeId;
    private String dsFieldTarget;

    public DataSourceFieldResponse() {
    }

    public Integer getDataSourceStructureId() {
        return this.dataSourceStructureId;
    }
    
    public void setDataSourceStructureId(Integer dataSourceStructureId) {
        this.dataSourceStructureId = dataSourceStructureId;
    }

    public Integer getDataSourceFieldTypeId() {
        return this.dataSourceFieldTypeId;
    }
    
    public void setDataSourceFieldTypeId(Integer dataSourceFieldTypeId) {
        this.dataSourceFieldTypeId = dataSourceFieldTypeId;
    }

    public String getDsFieldTarget() {
        return this.dsFieldTarget;
    }
    
    public void setDsFieldTarget(String dsFieldTarget) {
        this.dsFieldTarget = dsFieldTarget;
    }


}


