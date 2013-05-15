/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ApplicationFormTypeResponse 
 * @author Edward Banfa
 */
public class ApplicationFormTypeResponse extends BaseResponse {

    private String description;

    public ApplicationFormTypeResponse() {
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }


}


