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
 * MessageAttachment 
 * @author Edward Banfa
 */
@Entity
@Table(name="MESSAGE_ATTACHMENT"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MessageAttachment  extends BaseEntity implements java.io.Serializable {

	private Message message;
    private String dataTy;
    private String dataUrl;

    public MessageAttachment() {
    }

    public MessageAttachment(Message message, String code, String dataTy, String dataUrl, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.dataTy = dataTy;
		this.dataUrl = dataUrl;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public MessageAttachment(Message message, String code, String dataTy, String dataUrl, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.message = message;
		this.code = code;
		this.dataTy = dataTy;
		this.dataUrl = dataUrl;
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
		
    @Column(name="DATA_TY" , nullable=false, length=15)
    public String getDataTy() 
    {
        return this.dataTy;
    }
    
    public void setDataTy(String dataTy) 
    {
        this.dataTy = dataTy;
    }
		
    @Column(name="DATA_URL" , nullable=false, length=150)
    public String getDataUrl() 
    {
        return this.dataUrl;
    }
    
    public void setDataUrl(String dataUrl) 
    {
        this.dataUrl = dataUrl;
    }


}


