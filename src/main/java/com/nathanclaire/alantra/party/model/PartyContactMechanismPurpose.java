/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.model;

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

import com.nathanclaire.alantra.base.BaseEntity;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * PartyContactMechanismPurpose 
 * @author Edward Banfa
 */
@Entity
@Table(name="PARTY_CONTACT_MECHANISM_PURPOSE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PartyContactMechanismPurpose  extends BaseEntity implements java.io.Serializable {

	private ContactMechanismPurposeType contactMechanismPurposeType;
	private ContactMechanism contactMechanism;
    private Date fromDt;
    private Date toDt;
    private String name;
    private String description;

    public PartyContactMechanismPurpose() {
    }

    public PartyContactMechanismPurpose(ContactMechanismPurposeType contactMechanismPurposeType, ContactMechanism contactMechanism, String code, Date fromDt, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.fromDt = fromDt;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public PartyContactMechanismPurpose(ContactMechanismPurposeType contactMechanismPurposeType, ContactMechanism contactMechanism, String code, Date fromDt, Date toDt, String name, String description, Date effectiveDt, char recSt) 
    {
		this.contactMechanismPurposeType = contactMechanismPurposeType;
		this.contactMechanism = contactMechanism;
		this.code = code;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MECH_PURPOSE_TY_ID", nullable=false)
    @JsonIgnore
    public ContactMechanismPurposeType getContactMechanismPurposeType() 
    {
        return this.contactMechanismPurposeType;
    }
    
    public void setContactMechanismPurposeType(ContactMechanismPurposeType contactMechanismPurposeType)
    {
        this.contactMechanismPurposeType = contactMechanismPurposeType;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONT_MECH_ID", nullable=false)
    @JsonIgnore
    public ContactMechanism getContactMechanism() 
    {
        return this.contactMechanism;
    }
    
    public void setContactMechanism(ContactMechanism contactMechanism)
    {
        this.contactMechanism = contactMechanism;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="FROM_DT" , nullable=false, length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="TO_DT" , length=10)
	@JsonSerialize(using = DateSerializer.class)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
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


}


