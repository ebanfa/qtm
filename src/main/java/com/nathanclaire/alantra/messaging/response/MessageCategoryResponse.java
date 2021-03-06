/**
 *  Alantra.
 */
package com.nathanclaire.alantra.messaging.response;


import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class MessageCategoryResponse extends BaseResponse {
    private String name;
    private String description;

    public MessageCategoryResponse() {
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


