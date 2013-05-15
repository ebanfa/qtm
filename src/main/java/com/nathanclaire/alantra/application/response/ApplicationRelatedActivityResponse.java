/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.application.response;

import java.util.Date;
import java.math.BigDecimal;

import com.nathanclaire.alantra.base.response.BaseResponse;

/**
 * ApplicationRelatedActivityResponse 
 * @author Edward Banfa
 */
public class ApplicationRelatedActivityResponse extends BaseResponse {

    private Integer sourceApplicationActivityId;
    private Integer destinationApplicationActivityId;
    private int relActSeq;

    public ApplicationRelatedActivityResponse() {
    }

    public Integer getSourceApplicationActivityId() {
        return this.sourceApplicationActivityId;
    }
    
    public void setSourceApplicationActivityId(Integer sourceApplicationActivityId) {
        this.sourceApplicationActivityId = sourceApplicationActivityId;
    }

    public Integer getDestinationApplicationActivityId() {
        return this.destinationApplicationActivityId;
    }
    
    public void setDestinationApplicationActivityId(Integer destinationApplicationActivityId) {
        this.destinationApplicationActivityId = destinationApplicationActivityId;
    }

    public int getRelActSeq() {
        return this.relActSeq;
    }
    
    public void setRelActSeq(int relActSeq) {
        this.relActSeq = relActSeq;
    }


}


