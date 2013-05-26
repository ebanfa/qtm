/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.model;

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

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * Message 
 * @author Edward Banfa
 */
@Entity
@Table(name="MESSAGE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Message  extends BaseEntity implements java.io.Serializable {

	private MessageType messageType;
    private Advice advice;
	private MessageStatus messageStatus;
    private String messageFrom;
    private String messageTo;
    private String messageSubject;
    private String messageTxt;

    public Message() {
    }

    public Message(MessageType messageType, MessageStatus messageStatus, String messageFrom, String messageTo, String messageTxt) 
    {
		this.messageFrom = messageFrom;
		this.messageTo = messageTo;
		this.messageTxt = messageTxt;
    }
    public Message(MessageType messageType, Advice advice, MessageStatus messageStatus, String messageFrom, String messageTo, String messageSubject, String messageTxt) 
    {
		this.messageType = messageType;
		this.advice = advice;
		this.messageStatus = messageStatus;
		this.messageFrom = messageFrom;
		this.messageTo = messageTo;
		this.messageSubject = messageSubject;
		this.messageTxt = messageTxt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MSG_TY_ID", nullable=false)
    @JsonIgnore
    public MessageType getMessageType() 
    {
        return this.messageType;
    }
    
    public void setMessageType(MessageType messageType)
    {
        this.messageType = messageType;
    }
		
    @Column(name="ADVICE_ID" , unique=true)
    public Advice getAdvice() 
    {
        return this.advice;
    }
    
    public void setAdvice(Advice advice) 
    {
        this.advice = advice;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MSG_STATUS_ID", nullable=false)
    @JsonIgnore
    public MessageStatus getMessageStatus() 
    {
        return this.messageStatus;
    }
    
    public void setMessageStatus(MessageStatus messageStatus)
    {
        this.messageStatus = messageStatus;
    }
		
    @Column(name="MESSAGE_FROM" , nullable=false, length=75)
    public String getMessageFrom() 
    {
        return this.messageFrom;
    }
    
    public void setMessageFrom(String messageFrom) 
    {
        this.messageFrom = messageFrom;
    }
		
    @Column(name="MESSAGE_TO" , nullable=false, length=75)
    public String getMessageTo() 
    {
        return this.messageTo;
    }
    
    public void setMessageTo(String messageTo) 
    {
        this.messageTo = messageTo;
    }
		
    @Column(name="MESSAGE_SUBJECT" , unique=true, length=100)
    public String getMessageSubject() 
    {
        return this.messageSubject;
    }
    
    public void setMessageSubject(String messageSubject) 
    {
        this.messageSubject = messageSubject;
    }
		
    @Column(name="MESSAGE_TXT" , nullable=false)
    public String getMessageTxt() 
    {
        return this.messageTxt;
    }
    
    public void setMessageTxt(String messageTxt) 
    {
        this.messageTxt = messageTxt;
    }


}


