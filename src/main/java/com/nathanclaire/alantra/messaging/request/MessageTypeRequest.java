/**
 *  Alantra.
 */
package com.nathanclaire.alantra.messaging.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class MessageTypeRequest extends BaseRequest {
    private Integer messageCategoryId;
    private String messageCategoryText;
    private String name;
    private String description;

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
}


