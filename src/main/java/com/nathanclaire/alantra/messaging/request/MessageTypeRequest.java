/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * MessageTypeRequest 
 * @author Edward Banfa
 */
public class MessageTypeRequest extends BaseRequest {

    private Integer messageCategoryId;
    private String messageCategoryText;
    private String name;
    private String description;
    private Character autoRespFg;
    private Integer id;
    private String code;

    public MessageTypeRequest() {
    }

    public Integer getMessageCategoryId() {
        return this.messageCategoryId;
    }
    
    public void setMessageCategoryId(Integer messageCategoryId) {
        this.messageCategoryId = messageCategoryId;
    }

    public String getMessageCategoryText() {
        return this.messageCategoryText;
    }
    
    public void setMessageCategoryText(String messageCategoryText) {
        this.messageCategoryText = messageCategoryText;
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

    public Character getAutoRespFg() {
        return this.autoRespFg;
    }
    
    public void setAutoRespFg(Character autoRespFg) {
        this.autoRespFg = autoRespFg;
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


