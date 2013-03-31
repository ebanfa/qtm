/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * ContactMechanismLink 
 * @author Edward Banfa
 */
@Entity
@Table(name="CONTACT_MECHANISM_LINK"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ContactMechanismLink  extends BaseEntity implements java.io.Serializable {

	private ContactMechanism contactMechanismByToId;
	private ContactMechanism contactMechanismByFromId;
    private String description;

    public ContactMechanismLink() {
    }

    public ContactMechanismLink(ContactMechanism contactMechanismByToId, ContactMechanism contactMechanismByFromId, String code, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ContactMechanismLink(ContactMechanism contactMechanismByToId, ContactMechanism contactMechanismByFromId, String code, String description, Date effectiveDt, char recSt) 
    {
		this.contactMechanismByToId = contactMechanismByToId;
		this.contactMechanismByFromId = contactMechanismByFromId;
		this.code = code;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_ID", nullable=false)
    @JsonIgnore
    public ContactMechanism getContactMechanismByToId() 
    {
        return this.contactMechanismByToId;
    }
    
    public void setContactMechanismByToId(ContactMechanism contactMechanismByToId)
    {
        this.contactMechanismByToId = contactMechanismByToId;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_ID", nullable=false)
    @JsonIgnore
    public ContactMechanism getContactMechanismByFromId() 
    {
        return this.contactMechanismByFromId;
    }
    
    public void setContactMechanismByFromId(ContactMechanism contactMechanismByFromId)
    {
        this.contactMechanismByFromId = contactMechanismByFromId;
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


}


