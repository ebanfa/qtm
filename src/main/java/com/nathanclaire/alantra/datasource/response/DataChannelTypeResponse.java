/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * DataChannelTypeResponse 
 * @author Edward Banfa
 */
public class DataChannelTypeResponse extends BaseResponse {

    private Integer dataChannelAdapterId;
    private String dataChannelAdapterText;
    private Integer dataChannelCategoryId;
    private String dataChannelCategoryText;

    public DataChannelTypeResponse() {
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


}


