/**
 *  Alantra.
 */
package com.nathanclaire.alantra.transaction.request;


import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * AdviceRequest 
 * @author Edward Banfa
 */
public class TxnConfirmationStatusRequest extends BaseRequest {
    private String name;
    private String description;

    public TxnConfirmationStatusRequest() {
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


