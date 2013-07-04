/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.customer.model.CustomerMessage;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.security.model.SystemUserMessage;

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

	private MessageClassification messageClassification;
	private MessageType messageType;
	private MessageApplication messageApplication;
	private MessageStatus messageStatus;
	private DataChannel dataChannel;
    private String messageFrom;
    private String messageTo;
    private String messageSubject;
    private String messageTxt;
    private String msgAttachmentTy;
    private String msgAttachmentFile;
	private Set<MessageAction> messageActions = new HashSet<MessageAction>(0);
	private Set<CustomerMessage> customerMessages = new HashSet<CustomerMessage>(0);
	private Set<SystemUserMessage> systemUserMessages = new HashSet<SystemUserMessage>(0);
	private Set<MessageAttachment> messageAttachments = new HashSet<MessageAttachment>(0);

    public Message() {
    }

    public Message(MessageClassification messageClassification, MessageType messageType, MessageApplication messageApplication, MessageStatus messageStatus, DataChannel dataChannel, String code, String messageFrom, String messageTo, String messageTxt, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.messageFrom = messageFrom;
		this.messageTo = messageTo;
		this.messageTxt = messageTxt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public Message(MessageClassification messageClassification, MessageType messageType, MessageApplication messageApplication, MessageStatus messageStatus, DataChannel dataChannel, String code, String messageFrom, String messageTo, String messageSubject, String messageTxt, String msgAttachmentTy, String msgAttachmentFile, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<MessageAction> messageActions, Set<CustomerMessage> customerMessages, Set<SystemUserMessage> systemUserMessages, Set<MessageAttachment> messageAttachments ) 
    {
		this.messageClassification = messageClassification;
		this.messageType = messageType;
		this.messageApplication = messageApplication;
		this.messageStatus = messageStatus;
		this.dataChannel = dataChannel;
		this.code = code;
		this.messageFrom = messageFrom;
		this.messageTo = messageTo;
		this.messageSubject = messageSubject;
		this.messageTxt = messageTxt;
		this.msgAttachmentTy = msgAttachmentTy;
		this.msgAttachmentFile = msgAttachmentFile;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.messageActions = messageActions;
		this.customerMessages = customerMessages;
		this.systemUserMessages = systemUserMessages;
		this.messageAttachments = messageAttachments;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MSG_CLASS_ID", nullable=false)
    @JsonIgnore
    public MessageClassification getMessageClassification() 
    {
        return this.messageClassification;
    }
    
    public void setMessageClassification(MessageClassification messageClassification)
    {
        this.messageClassification = messageClassification;
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
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MSG_APPL_ID", nullable=false)
    @JsonIgnore
    public MessageApplication getMessageApplication() 
    {
        return this.messageApplication;
    }
    
    public void setMessageApplication(MessageApplication messageApplication)
    {
        this.messageApplication = messageApplication;
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
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHANNEL_ID", nullable=false)
    @JsonIgnore
    public DataChannel getDataChannel() 
    {
        return this.dataChannel;
    }
    
    public void setDataChannel(DataChannel dataChannel)
    {
        this.dataChannel = dataChannel;
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
		
    @Column(name="MSG_ATTACHMENT_TY" , unique=true, length=150)
    public String getMsgAttachmentTy() 
    {
        return this.msgAttachmentTy;
    }
    
    public void setMsgAttachmentTy(String msgAttachmentTy) 
    {
        this.msgAttachmentTy = msgAttachmentTy;
    }
		
    @Column(name="MSG_ATTACHMENT_FILE" , unique=true)
    public String getMsgAttachmentFile() 
    {
        return this.msgAttachmentFile;
    }
    
    public void setMsgAttachmentFile(String msgAttachmentFile) 
    {
        this.msgAttachmentFile = msgAttachmentFile;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="message")
    @JsonIgnore
    public Set<MessageAction> getMessageActions() 
    {
        return this.messageActions;
    }
    
    public void setMessageActions(Set<MessageAction> messageActions) 
    {
        this.messageActions = messageActions;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="message")
    @JsonIgnore
    public Set<CustomerMessage> getCustomerMessages() 
    {
        return this.customerMessages;
    }
    
    public void setCustomerMessages(Set<CustomerMessage> customerMessages) 
    {
        this.customerMessages = customerMessages;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="message")
    @JsonIgnore
    public Set<SystemUserMessage> getSystemUserMessages() 
    {
        return this.systemUserMessages;
    }
    
    public void setSystemUserMessages(Set<SystemUserMessage> systemUserMessages) 
    {
        this.systemUserMessages = systemUserMessages;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="message")
    @JsonIgnore
    public Set<MessageAttachment> getMessageAttachments() 
    {
        return this.messageAttachments;
    }
    
    public void setMessageAttachments(Set<MessageAttachment> messageAttachments) 
    {
        this.messageAttachments = messageAttachments;
    }			


}


