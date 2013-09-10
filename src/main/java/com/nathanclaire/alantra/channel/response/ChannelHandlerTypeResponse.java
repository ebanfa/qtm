/**
 *  Alantra.
 */
package com.nathanclaire.alantra.channel.response;


import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class ChannelHandlerTypeResponse extends BaseResponse {
    private String name;
    private String description;

    public ChannelHandlerTypeResponse() {
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


