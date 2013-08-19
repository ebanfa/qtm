/**
 *  Alantra.
 */
package com.nathanclaire.alantra.transaction.response;


import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * AdviceResponse 
 * @author Edward Banfa
 */
public class TxnConfirmationStatusResponse extends BaseResponse {
    private String name;
    private String description;

    public TxnConfirmationStatusResponse() {
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


