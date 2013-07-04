/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * MessageApplicationTagRequest 
 * @author Edward Banfa
 */
public class MessageApplicationTagRequest extends BaseRequest {

    private Integer messageApplicationId;
    private String messageApplicationText;
    private String name;
    private String description;
    private String tagVal;
    private char isRegexFg;
    private Integer id;
    private String code;

    public MessageApplicationTagRequest() {
    }

    public Integer getMessageApplicationId() {
        return this.messageApplicationId;
    }
    
    public void setMessageApplicationId(Integer messageApplicationId) {
        this.messageApplicationId = messageApplicationId;
    }

    public String getMessageApplicationText() {
        return this.messageApplicationText;
    }
    
    public void setMessageApplicationText(String messageApplicationText) {
        this.messageApplicationText = messageApplicationText;
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


