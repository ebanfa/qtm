/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * DataChannelTypeRequest 
 * @author Edward Banfa
 */
public class DataChannelTypeRequest extends BaseRequest {

    private Integer dataChannelAdapterId;
    private String dataChannelAdapterText;
    private Integer dataChannelCategoryId;
    private String dataChannelCategoryText;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public DataChannelTypeRequest() {
    }

    public Integer getDataChannelAdapterId() {
        return this.dataChannelAdapterId;
    }
    
    public void setDataChannelAdapterId(Integer dataChannelAdapterId) {
        this.dataChannelAdapterId = dataChannelAdapterId;
    }

    public String getDataChannelAdapterText() {
        return this.dataChannelAdapterText;
    }
    
    public void setDataChannelAdapterText(String dataChannelAdapterText) {
        this.dataChannelAdapterText = dataChannelAdapterText;
    }

    public Integer getDataChannelCategoryId() {
        return this.dataChannelCategoryId;
    }
    
    public void setDataChannelCategoryId(Integer dataChannelCategoryId) {
        this.dataChannelCategoryId = dataChannelCategoryId;
    }

    public String getDataChannelCategoryText() {
        return this.dataChannelCategoryText;
    }
    
    public void setDataChannelCategoryText(String dataChannelCategoryText) {
        this.dataChannelCategoryText = dataChannelCategoryText;
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


