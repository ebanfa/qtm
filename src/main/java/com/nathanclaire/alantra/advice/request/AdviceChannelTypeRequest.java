/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceChannelTypeRequest 
 * @author Edward Banfa
 */
public class AdviceChannelTypeRequest extends BaseRequest {

    private Integer adviceChannelCategoryId;
    private String adviceChannelCategoryText;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public AdviceChannelTypeRequest() {
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


