/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.notification.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * TemplateResponse 
 * @author Edward Banfa
 */
public class TemplateResponse extends BaseResponse {

    private Integer templateTypeId;
    private String templateTypeText;
    private Integer templateClassificationId;
    private String templateClassificationText;
    private String subjectTxt;
    private String messageTxt;

    public TemplateResponse() {
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


}


