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

/**
 * MessageAction 
 * @author Edward Banfa
 */
@Entity
@Table(name="MESSAGE_ACTION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MessageAction  extends BaseEntity implements java.io.Serializable {

	private MessageApplicationAction messageApplicationAction;
	private Message message;

    public MessageAction() {
    }

    public MessageAction(MessageApplicationAction messageApplicationAction, Message message, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public MessageAction(MessageApplicationAction messageApplicationAction, Message message, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.messageApplicationAction = messageApplicationAction;
		this.message = message;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MSG_APPL_ACTION_ID", nullable=false)
    @JsonIgnore
    public MessageApplicationAction getMessageApplicationAction() 
    {
        return this.messageApplicationAction;
    }
    
    public void setMessageApplicationAction(MessageApplicationAction messageApplicationAction)
    {
        this.messageApplicationAction = messageApplicationAction;
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


}


