/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceChannelTypeResponse 
 * @author Edward Banfa
 */
public class AdviceChannelTypeResponse extends BaseResponse {

    private Integer adviceChannelCategoryId;
    private String adviceChannelCategoryText;

    public AdviceChannelTypeResponse() {
    }

    public Integer getAdviceChannelCategoryId() {
        return this.adviceChannelCategoryId;
    }
    
    public void setAdviceChannelCategoryId(Integer adviceChannelCategoryId) {
        this.adviceChannelCategoryId = adviceChannelCategoryId;
    }

    public String getAdviceChannelCategoryText() {
        return this.adviceChannelCategoryText;
    }
    
    public void setAdviceChannelCategoryText(String adviceChannelCategoryText) {
        this.adviceChannelCategoryText = adviceChannelCategoryText;
    }


}


