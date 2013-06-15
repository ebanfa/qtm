/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * MessageTypeResponse 
 * @author Edward Banfa
 */
public class MessageTypeResponse extends BaseResponse {

    private Integer messageCategoryId;
    private String messageCategoryText;

    public MessageTypeResponse() {
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


}


