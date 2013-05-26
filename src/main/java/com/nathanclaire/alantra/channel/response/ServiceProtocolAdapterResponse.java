/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.channel.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ServiceProtocolAdapterResponse 
 * @author Edward Banfa
 */
public class ServiceProtocolAdapterResponse extends BaseResponse {

    private String className;

    public ServiceProtocolAdapterResponse() {
    }

    public String getClassName() {
        return this.className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }


}


