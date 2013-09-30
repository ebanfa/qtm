/**
 * 
 */
package com.nathanclaire.alantra.base.util;

/**
 * @author Edward Banfa
 *
 */
public class RelatedActivityResponse {
	
	private String name;
	private String description;
    private Integer sourceActivityId;
    private Integer destinationActivityId;
    private int relActSeq;
    
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
	/**
	 * @return the sourceActivityId
	 */
	public Integer getSourceActivityId() {
		return sourceActivityId;
	}
	/**
	 * @param sourceActivityId the sourceActivityId to set
	 */
	public void setSourceActivityId(Integer sourceActivityId) {
		this.sourceActivityId = sourceActivityId;
	}
	/**
	 * @return the destinationActivityId
	 */
	public Integer getDestinationActivityId() {
		return destinationActivityId;
	}
	/**
	 * @param destinationActivityId the destinationActivityId to set
	 */
	public void setDestinationActivityId(Integer destinationActivityId) {
		this.destinationActivityId = destinationActivityId;
	}
	/**
	 * @return the relActSeq
	 */
	public int getRelActSeq() {
		return relActSeq;
	}
	/**
	 * @param relActSeq the relActSeq to set
	 */
	public void setRelActSeq(int relActSeq) {
		this.relActSeq = relActSeq;
	}

}
