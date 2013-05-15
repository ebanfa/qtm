/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ApplicationEntityFieldTypeResponse 
 * @author Edward Banfa
 */
public class ApplicationEntityFieldTypeResponse extends BaseResponse {

    private String description;

    public ApplicationEntityFieldTypeResponse() {
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }


}


