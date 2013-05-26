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

	private String name;
	private String description;
    private Integer sourceApplicationActivityId;
    private Integer destinationApplicationActivityId;
    private int relActSeq;

    public ApplicationRelatedActivityResponse() {
    }

    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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


