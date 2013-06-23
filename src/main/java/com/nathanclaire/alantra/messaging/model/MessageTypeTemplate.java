/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.model;

import java.util.Date;

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
import com.nathanclaire.alantra.notification.model.Template;

/**
 * MessageTypeTemplate 
 * @author Edward Banfa
 */
@Entity
@Table(name="MESSAGE_TYPE_TEMPLATE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MessageTypeTemplate  extends BaseEntity implements java.io.Serializable {

	private Template template;
	private MessageType messageType;

    public MessageTypeTemplate() {
    }

    public MessageTypeTemplate(Template template, MessageType messageType, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public MessageTypeTemplate(Template template, MessageType messageType, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.template = template;
		this.messageType = messageType;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TEMPLATE_ID", nullable=false)
    @JsonIgnore
    public Template getTemplate() 
    {
        return this.template;
    }
    
    public void setTemplate(Template template)
    {
        this.template = template;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MESSAGE_TY_ID", nullable=false)
    @JsonIgnore
    public MessageType getMessageType() 
    {
        return this.messageType;
    }
    
    public void setMessageType(MessageType messageType)
    {
        this.messageType = messageType;
    }


}


