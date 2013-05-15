/**
 * 
 */
package com.nathanclaire.alantra.base.request;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;

/**
 * @author Edward Banfa 
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BaseRequest {

    private Integer id;
    private String code;
    private String name;
	protected String description;
    private Character encrpytedFg;
	private Date effectiveDt;
    private char recSt;
    private Integer versionNo;
    private Date rowTs;
    private Date createdDt;
    private String createdByUsr;
    private Date lastModifiedDt;
    private String lastModifiedUsr;
    
	/**
	 * 
	 */
	public BaseRequest() {
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

	/**
	 * @return the effectiveDt
	 */
	@JsonSerialize(using = DateSerializer.class)
	public Date getEffectiveDt() {
		return effectiveDt;
	}
	/**
	 * @param effectiveDt the effectiveDt to set
	 */
	@JsonDeserialize(using = DateDeserializer.class)
	public void setEffectiveDt(Date effectiveDt) {
		this.effectiveDt = effectiveDt;
	}
	/**
	 * @return the recSt
	 */
	public char getRecSt() {
		return recSt;
	}
	/**
	 * @param recSt the recSt to set
	 */
	public void setRecSt(char recSt) {
		this.recSt = recSt;
	}
	/**
	 * @return the versionNo
	 */
	public Integer getVersionNo() {
		return versionNo;
	}
	/**
	 * @param versionNo the versionNo to set
	 */
	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	/**
	 * @return the rowTs
	 */
	public Date getRowTs() {
		return rowTs;
	}
	/**
	 * @param rowTs the rowTs to set
	 */
	public void setRowTs(Date rowTs) {
		this.rowTs = rowTs;
	}
	/**
	 * @return the createdDt
	 */
	@JsonSerialize(using = DateSerializer.class)
	public Date getCreatedDt() {
		return createdDt;
	}
	/**
	 * @param createdDt the createdDt to set
	 */
	@JsonDeserialize(using = DateDeserializer.class)
	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
	/**
	 * @return the createdByUsr
	 */
	public String getCreatedByUsr() {
		return createdByUsr;
	}
	/**
	 * @param createdByUsr the createdByUsr to set
	 */
	public void setCreatedByUsr(String createdByUsr) {
		this.createdByUsr = createdByUsr;
	}
	/**
	 * @return the lastModifiedDt
	 */
	@JsonSerialize(using = DateSerializer.class)
	public Date getLastModifiedDt() {
		return lastModifiedDt;
	}
	/**
	 * @param lastModifiedDt the lastModifiedDt to set
	 */
	@JsonDeserialize(using = DateDeserializer.class)
	public void setLastModifiedDt(Date lastModifiedDt) {
		this.lastModifiedDt = lastModifiedDt;
	}
	/**
	 * @return the lastModifiedUsr
	 */
	public String getLastModifiedUsr() {
		return lastModifiedUsr;
	}
	/**
	 * @param lastModifiedUsr the lastModifiedUsr to set
	 */
	public void setLastModifiedUsr(String lastModifiedUsr) {
		this.lastModifiedUsr = lastModifiedUsr;
	}
	/**
	 * @return the encrpytedFg
	 */
	public Character getEncrpytedFg() {
		return encrpytedFg;
	}
	/**
	 * @param encrpytedFg the encrpytedFg to set
	 */
	public void setEncrpytedFg(Character encrpytedFg) {
		this.encrpytedFg = encrpytedFg;
	}
	
	

}
