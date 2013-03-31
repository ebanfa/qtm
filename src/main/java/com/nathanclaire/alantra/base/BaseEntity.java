/**
 * 
 */
package com.nathanclaire.alantra.base;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * @author Edward Banfa 
 *
 */
@MappedSuperclass
public class BaseEntity 
{
	protected Integer id;
    protected String code;
	protected Date effectiveDt;
    protected char recSt;
    protected Integer versionNo;
    protected Date rowTs;
    protected Date createdDt;
    protected String createdByUsr;
    protected Date lastModifiedDt;
    protected String lastModifiedUsr;
	/**
	 * 
	 */
	public BaseEntity() {
		// TODO Auto-generated constructor stub
	}
	
	@Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="CODE" , nullable=false, length=35)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
	
	@Temporal(TemporalType.DATE)
    @Column(name="EFFECTIVE_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getEffectiveDt() {
        return this.effectiveDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setEffectiveDt(Date effectiveDt) {
        this.effectiveDt = effectiveDt;
    }
		
    @Column(name="REC_ST" , nullable=false, length=1)
    public char getRecSt() {
        return this.recSt;
    }
    
    public void setRecSt(char recSt) {
        this.recSt = recSt;
    }
		
    @Column(name="VERSION_NO" , unique=true)
    public Integer getVersionNo() {
        return this.versionNo;
    }
    
    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }
			
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ROW_TS" , length=19)
    public Date getRowTs() {
        return this.rowTs;
    }
    
    public void setRowTs(Date rowTs) {
        this.rowTs = rowTs;
    }
		    
    @Temporal(TemporalType.DATE)
    @Column(name="CREATED_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getCreatedDt() {
        return this.createdDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }
		
    @Column(name="CREATED_BY_USR" , nullable=false, length=35)
    public String getCreatedByUsr() {
        return this.createdByUsr;
    }
    
    public void setCreatedByUsr(String createdByUsr) {
        this.createdByUsr = createdByUsr;
    }
		    
    @Temporal(TemporalType.DATE)
    @Column(name="LAST_MODIFIED_DT" , length=19)
	@JsonSerialize(using = DateSerializer.class)
    public Date getLastModifiedDt() {
        return this.lastModifiedDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setLastModifiedDt(Date lastModifiedDt) {
        this.lastModifiedDt = lastModifiedDt;
    }
		
    @Column(name="LAST_MODIFIED_USR" , unique=true, length=35)
    public String getLastModifiedUsr() {
        return this.lastModifiedUsr;
    }
    
    public void setLastModifiedUsr(String lastModifiedUsr) {
        this.lastModifiedUsr = lastModifiedUsr;
    }

}
