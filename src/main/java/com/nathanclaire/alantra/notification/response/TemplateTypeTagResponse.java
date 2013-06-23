/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.notification.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * TemplateTypeTagResponse 
 * @author Edward Banfa
 */
public class TemplateTypeTagResponse extends BaseResponse {

    private Integer templateTypeId;
    private String templateTypeText;
    private String tagVal;

    public TemplateTypeTagResponse() {
    }

    public Integer getTemplateTypeId() {
        return this.templateTypeId;
    }
    
    public void setTemplateTypeId(Integer templateTypeId) {
        this.templateTypeId = templateTypeId;
    }

    public String getTemplateTypeText() {
        return this.templateTypeText;
    }
    
    public void setTemplateTypeText(String templateTypeText) {
        this.templateTypeText = templateTypeText;
    }

    public String getTagVal() {
        return this.tagVal;
    }
    
    public void setTagVal(String tagVal) {
        this.tagVal = tagVal;
    }


}


