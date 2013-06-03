/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * DataSourceTypeRequest 
 * @author Edward Banfa
 */
public class DataSourceTypeRequest extends BaseRequest {

    private Integer dataSourceCategoryId;
    private String dataSourceCategoryText;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public DataSourceTypeRequest() {
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


