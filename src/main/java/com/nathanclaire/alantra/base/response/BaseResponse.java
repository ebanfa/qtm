/**
 * 
 */
package com.nathanclaire.alantra.base.response;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;

/**
 * @author Edward Banfa 
 *
 */
public class BaseResponse {
	
	protected Integer id;
	protected String name;
	protected String code;
	protected String description;
	protected Character recSt;
    protected Date createdDt;
    protected String createdByUsr;
    protected Date lastModifiedDt;
    protected String lastModifiedUsr;
    protected Date effectiveDt;
	
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
	 * @return the recSt
	 */
	public Character getRecSt() {
		return recSt;
	}
	/**
	 * @param recSt the recSt to set
	 */
	public void setRecSt(Character recSt) {
		this.recSt = recSt;
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
	@JsonDeserialize(using = DateDeserializer.class)
	public void setLastModifiedUsr(String lastModifiedUsr) {
		this.lastModifiedUsr = lastModifiedUsr;
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

}
