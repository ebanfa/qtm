/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.messaging.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * MessageStatus 
 * @author Edward Banfa
 */
@Entity
@Table(name="MESSAGE_STATUS"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class MessageStatus  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<Message> messages = new HashSet<Message>(0);

    public MessageStatus() {
    }

    public MessageStatus(String name) 
    {
		this.name = name;
    }
    public MessageStatus(String name, String description, Set<Message> messages ) 
    {
		this.name = name;
		this.description = description;
		this.messages = messages;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="messageStatus")
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


