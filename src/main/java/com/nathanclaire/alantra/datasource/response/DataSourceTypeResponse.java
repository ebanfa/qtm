/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataSourceTypeResponse 
 * @author Edward Banfa
 */
public class DataSourceTypeResponse extends BaseResponse {

    private Integer dataSourceCategoryId;
    private String dataSourceCategoryText;

    public DataSourceTypeResponse() {
    }

    public Integer getDataSourceCategoryId() {
        return this.dataSourceCategoryId;
    }
    
    public void setDataSourceCategoryId(Integer dataSourceCategoryId) {
        this.dataSourceCategoryId = dataSourceCategoryId;
    }

    public String getDataSourceCategoryText() {
        return this.dataSourceCategoryText;
    }
    
    public void setDataSourceCategoryText(String dataSourceCategoryText) {
        this.dataSourceCategoryText = dataSourceCategoryText;
    }


}

