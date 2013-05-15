/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.request;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.request.BaseRequest;

/**
 * ApplicationRelatedActivityRequest 
 * @author Edward Banfa
 */
public class ApplicationRelatedActivityRequest extends BaseRequest {

    private Integer id;
    private Integer applicationActivityBySrcActId;
    private Integer applicationActivityByDstActId;
    private String code;
    private int relActSeq;

    public ApplicationRelatedActivityRequest() {
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationActivityBySrcActId() {
        return this.applicationActivityBySrcActId;
    }
    
    public void setApplicationActivityBySrcActId(Integer applicationActivityBySrcActId) {
        this.applicationActivityBySrcActId = applicationActivityBySrcActId;
    }

    public Integer getApplicationActivityByDstActId() {
        return this.applicationActivityByDstActId;
    }
    
    public void setApplicationActivityByDstActId(Integer applicationActivityByDstActId) {
        this.applicationActivityByDstActId = applicationActivityByDstActId;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public int getRelActSeq() {
        return this.relActSeq;
    }
    
    public void setRelActSeq(int relActSeq) {
        this.relActSeq = relActSeq;
    }


}


