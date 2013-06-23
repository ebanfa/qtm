/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.notification.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * TemplateTypeTagRequest 
 * @author Edward Banfa
 */
public class TemplateTypeTagRequest extends BaseRequest {

    private Integer templateTypeId;
    private String templateTypeText;
    private String name;
    private String description;
    private String tagVal;
    private Integer id;
    private String code;

    public TemplateTypeTagRequest() {
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

    public String getTagVal() {
        return this.tagVal;
    }
    
    public void setTagVal(String tagVal) {
        this.tagVal = tagVal;
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


