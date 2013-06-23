/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.notification.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * TemplateRequest 
 * @author Edward Banfa
 */
public class TemplateRequest extends BaseRequest {

    private Integer templateTypeId;
    private String templateTypeText;
    private Integer templateClassificationId;
    private String templateClassificationText;
    private String name;
    private String subjectTxt;
    private String messageTxt;
    private String description;
    private Integer id;
    private String code;

    public TemplateRequest() {
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

    public Integer getTemplateClassificationId() {
        return this.templateClassificationId;
    }
    
    public void setTemplateClassificationId(Integer templateClassificationId) {
        this.templateClassificationId = templateClassificationId;
    }

    public String getTemplateClassificationText() {
        return this.templateClassificationText;
    }
    
    public void setTemplateClassificationText(String templateClassificationText) {
        this.templateClassificationText = templateClassificationText;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectTxt() {
        return this.subjectTxt;
    }
    
    public void setSubjectTxt(String subjectTxt) {
        this.subjectTxt = subjectTxt;
    }

    public String getMessageTxt() {
        return this.messageTxt;
    }
    
    public void setMessageTxt(String messageTxt) {
        this.messageTxt = messageTxt;
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


