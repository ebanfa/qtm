/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.party.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;
import com.nathanclaire.alantra.invoice.model.Invoice;

/**
 * ContactMechanism 
 * @author Edward Banfa
 */
@Entity
@Table(name="CONTACT_MECHANISM"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ContactMechanism  extends BaseEntity implements java.io.Serializable {

	private ContactMechanismType contactMechanismType;
    private String name;
    private String description;
	private Set<PostalAddress> postalAddresses = new HashSet<PostalAddress>(0);
	private Set<PartyContactMechanismPurpose> partyContactMechanismPurposes = new HashSet<PartyContactMechanismPurpose>(0);
	private Set<ContactMechanismLink> contactMechanismLinksForToId = new HashSet<ContactMechanismLink>(0);
	private Set<ContactMechanismLink> contactMechanismLinksForFromId = new HashSet<ContactMechanismLink>(0);
	private Set<Invoice> invoices = new HashSet<Invoice>(0);
	private Set<TelecommunicationsNumber> telecommunicationsNumbers = new HashSet<TelecommunicationsNumber>(0);
	private Set<ElectronicAddress> electronicAddresses = new HashSet<ElectronicAddress>(0);
	private Set<PartyContactMechanism> partyContactMechanisms = new HashSet<PartyContactMechanism>(0);

    public ContactMechanism() {
    }

    public ContactMechanism(ContactMechanismType contactMechanismType, String code, String name, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ContactMechanism(ContactMechanismType contactMechanismType, String code, String name, String description, Date effectiveDt, char recSt, Set<PostalAddress> postalAddresses, Set<PartyContactMechanismPurpose> partyContactMechanismPurposes, Set<ContactMechanismLink> contactMechanismLinksForToId, Set<ContactMechanismLink> contactMechanismLinksForFromId, Set<Invoice> invoices, Set<TelecommunicationsNumber> telecommunicationsNumbers, Set<ElectronicAddress> electronicAddresses, Set<PartyContactMechanism> partyContactMechanisms ) 
    {
		this.contactMechanismType = contactMechanismType;
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.postalAddresses = postalAddresses;
		this.partyContactMechanismPurposes = partyContactMechanismPurposes;
		this.contactMechanismLinksForToId = contactMechanismLinksForToId;
		this.contactMechanismLinksForFromId = contactMechanismLinksForFromId;
		this.invoices = invoices;
		this.telecommunicationsNumbers = telecommunicationsNumbers;
		this.electronicAddresses = electronicAddresses;
		this.partyContactMechanisms = partyContactMechanisms;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONT_MECH_TY_ID", nullable=false)
    @JsonIgnore
    public ContactMechanismType getContactMechanismType() 
    {
        return this.contactMechanismType;
    }
    
    public void setContactMechanismType(ContactMechanismType contactMechanismType)
    {
        this.contactMechanismType = contactMechanismType;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<PostalAddress> getPostalAddresses() 
    {
        return this.postalAddresses;
    }
    
    public void setPostalAddresses(Set<PostalAddress> postalAddresses) 
    {
        this.postalAddresses = postalAddresses;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<PartyContactMechanismPurpose> getPartyContactMechanismPurposes() 
    {
        return this.partyContactMechanismPurposes;
    }
    
    public void setPartyContactMechanismPurposes(Set<PartyContactMechanismPurpose> partyContactMechanismPurposes) 
    {
        this.partyContactMechanismPurposes = partyContactMechanismPurposes;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanismByToId")
    @JsonIgnore
    public Set<ContactMechanismLink> getContactMechanismLinksForToId() 
    {
        return this.contactMechanismLinksForToId;
    }
    
    public void setContactMechanismLinksForToId(Set<ContactMechanismLink> contactMechanismLinksForToId) 
    {
        this.contactMechanismLinksForToId = contactMechanismLinksForToId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanismByFromId")
    @JsonIgnore
    public Set<ContactMechanismLink> getContactMechanismLinksForFromId() 
    {
        return this.contactMechanismLinksForFromId;
    }
    
    public void setContactMechanismLinksForFromId(Set<ContactMechanismLink> contactMechanismLinksForFromId) 
    {
        this.contactMechanismLinksForFromId = contactMechanismLinksForFromId;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<Invoice> getInvoices() 
    {
        return this.invoices;
    }
    
    public void setInvoices(Set<Invoice> invoices) 
    {
        this.invoices = invoices;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<TelecommunicationsNumber> getTelecommunicationsNumbers() 
    {
        return this.telecommunicationsNumbers;
    }
    
    public void setTelecommunicationsNumbers(Set<TelecommunicationsNumber> telecommunicationsNumbers) 
    {
        this.telecommunicationsNumbers = telecommunicationsNumbers;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<ElectronicAddress> getElectronicAddresses() 
    {
        return this.electronicAddresses;
    }
    
    public void setElectronicAddresses(Set<ElectronicAddress> electronicAddresses) 
    {
        this.electronicAddresses = electronicAddresses;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="contactMechanism")
    @JsonIgnore
    public Set<PartyContactMechanism> getPartyContactMechanisms() 
    {
        return this.partyContactMechanisms;
    }
    
    public void setPartyContactMechanisms(Set<PartyContactMechanism> partyContactMechanisms) 
    {
        this.partyContactMechanisms = partyContactMechanisms;
    }			


}


