/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ApplicationFormFieldTypeResponse 
 * @author Edward Banfa
 */
public class ApplicationFormFieldTypeResponse extends BaseResponse {

    private String description;

    public ApplicationFormFieldTypeResponse() {
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }


}


