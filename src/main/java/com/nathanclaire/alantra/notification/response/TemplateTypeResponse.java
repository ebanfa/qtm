/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.notification.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * TemplateTypeResponse 
 * @author Edward Banfa
 */
public class TemplateTypeResponse extends BaseResponse {

    private Integer templateCategoryId;
    private String templateCategoryText;

    public TemplateTypeResponse() {
    }

    public Integer getTemplateCategoryId() {
        return this.templateCategoryId;
    }
    
    public void setTemplateCategoryId(Integer templateCategoryId) {
        this.templateCategoryId = templateCategoryId;
    }

    public String getTemplateCategoryText() {
        return this.templateCategoryText;
    }
    
    public void setTemplateCategoryText(String templateCategoryText) {
        this.templateCategoryText = templateCategoryText;
    }


}


