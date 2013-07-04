/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * MessageApplicationActionTagRequest 
 * @author Edward Banfa
 */
public class MessageApplicationActionTagRequest extends BaseRequest {

    private Integer messageApplicationActionId;
    private String messageApplicationActionText;
    private String name;
    private String description;
    private String tagVal;
    private char isRegexFg;
    private Integer id;
    private String code;

    public MessageApplicationActionTagRequest() {
    }

    public Integer getMessageApplicationActionId() {
        return this.messageApplicationActionId;
    }
    
    public void setMessageApplicationActionId(Integer messageApplicationActionId) {
        this.messageApplicationActionId = messageApplicationActionId;
    }

    public String getMessageApplicationActionText() {
        return this.messageApplicationActionText;
    }
    
    public void setMessageApplicationActionText(String messageApplicationActionText) {
        this.messageApplicationActionText = messageApplicationActionText;
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

    public char getIsRegexFg() {
        return this.isRegexFg;
    }
    
    public void setIsRegexFg(char isRegexFg) {
        this.isRegexFg = isRegexFg;
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


