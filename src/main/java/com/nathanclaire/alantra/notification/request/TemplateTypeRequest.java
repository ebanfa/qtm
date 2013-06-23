/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.notification.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * TemplateTypeRequest 
 * @author Edward Banfa
 */
public class TemplateTypeRequest extends BaseRequest {

    private Integer templateCategoryId;
    private String templateCategoryText;
    private String name;
    private String description;
    private Integer id;
    private String code;

    public TemplateTypeRequest() {
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


