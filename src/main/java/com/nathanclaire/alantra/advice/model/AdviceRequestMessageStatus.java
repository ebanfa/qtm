/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.advice.model;

import java.util.Date;
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
 * AdviceRequestMessageStatus 
 * @author Edward Banfa
 */
@Entity
@Table(name="ADVICE_REQUEST_MESSAGE_STATUS"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AdviceRequestMessageStatus  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<AdviceRequestMessage> adviceRequestMessages = new HashSet<AdviceRequestMessage>(0);

    public AdviceRequestMessageStatus() {
    }

    public AdviceRequestMessageStatus(String code, String name, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public AdviceRequestMessageStatus(String code, String name, String description, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<AdviceRequestMessage> adviceRequestMessages ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.adviceRequestMessages = adviceRequestMessages;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="adviceRequestMessageStatus")
    @JsonIgnore
    public Set<AdviceRequestMessage> getAdviceRequestMessages() 
    {
        return this.adviceRequestMessages;
    }
    
    public void setAdviceRequestMessages(Set<AdviceRequestMessage> adviceRequestMessages) 
    {
        this.adviceRequestMessages = adviceRequestMessages;
    }			


}


