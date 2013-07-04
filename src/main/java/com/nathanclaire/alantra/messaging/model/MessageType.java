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

/**
 * MessageType 
 * @author Edward Banfa
 */
@Entity
@Table(name="MESSAGE_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MessageType  extends BaseEntity implements java.io.Serializable {

	private MessageCategory messageCategory;
    private String name;
    private String description;
    private Character autoRespFg;
	private Set<Message> messages = new HashSet<Message>(0);

    public MessageType() {
    }

    public MessageType(MessageCategory messageCategory, String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public MessageType(MessageCategory messageCategory, String code, String name, String description, Date effectiveDt, Character autoRespFg, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<Message> messages ) 
    {
		this.messageCategory = messageCategory;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.autoRespFg = autoRespFg;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.messages = messages;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MSG_CAT_ID", nullable=false)
    @JsonIgnore
    public MessageCategory getMessageCategory() 
    {
        return this.messageCategory;
    }
    
    public void setMessageCategory(MessageCategory messageCategory)
    {
        this.messageCategory = messageCategory;
    }
		
    @Column(name="NAME" , nullable=false, length=75)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
		
    @Column(name="DESCRIPTION" , unique=true, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
		
    @Column(name="AUTO_RESP_FG" , unique=true, length=1)
    public Character getAutoRespFg() 
    {
        return this.autoRespFg;
    }
    
    public void setAutoRespFg(Character autoRespFg) 
    {
        this.autoRespFg = autoRespFg;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="messageType")
    @JsonIgnore
    public Set<Message> getMessages() 
    {
        return this.messages;
    }
    
    public void setMessages(Set<Message> messages) 
    {
        this.messages = messages;
    }			


}


