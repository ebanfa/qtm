/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * MessageAttachements 
 * @author Edward Banfa
 */
@Entity
@Table(name="MESSAGE_ATTACHEMENTS"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MessageAttachements  extends BaseEntity implements java.io.Serializable {

	private Message message;
    private byte data;
    private String dataTy;

    public MessageAttachements() {
    }

    public MessageAttachements(Message message, String code, byte data, String dataTy, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.data = data;
		this.dataTy = dataTy;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public MessageAttachements(Message message, String code, byte data, String dataTy, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.message = message;
		this.code = code;
		this.data = data;
		this.dataTy = dataTy;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MSG_ID", nullable=false)
    @JsonIgnore
    public Message getMessage() 
    {
        return this.message;
    }
    
    public void setMessage(Message message)
    {
        this.message = message;
    }
		
    @Column(name="DATA" , nullable=false)
    public byte getData() 
    {
        return this.data;
    }
    
    public void setData(byte data) 
    {
        this.data = data;
    }
		
    @Column(name="DATA_TY" , nullable=false, length=15)
    public String getDataTy() 
    {
        return this.dataTy;
    }
    
    public void setDataTy(String dataTy) 
    {
        this.dataTy = dataTy;
    }


}


