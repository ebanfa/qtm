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

import com.nathanclaire.alantra.base.BaseEntity;
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;
import com.nathanclaire.alantra.businessdata.model.PostalAddressBoundry;

/**
 * PostalAddress 
 * @author Edward Banfa
 */
@Entity
@Table(name="POSTAL_ADDRESS"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PostalAddress  extends BaseEntity implements java.io.Serializable {

	private ContactMechanism contactMechanism;
    private String address1;
    private String address2;
    private String directions;
    private String description;
	private Set<PostalAddressBoundry> postalAddressBoundries = new HashSet<PostalAddressBoundry>(0);

    public PostalAddress() {
    }

    public PostalAddress(ContactMechanism contactMechanism, String code, String address1, String address2, String directions, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.address1 = address1;
		this.address2 = address2;
		this.directions = directions;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public PostalAddress(ContactMechanism contactMechanism, String code, String address1, String address2, String directions, String description, Date effectiveDt, char recSt, Set<PostalAddressBoundry> postalAddressBoundries ) 
    {
		this.contactMechanism = contactMechanism;
		this.code = code;
		this.address1 = address1;
		this.address2 = address2;
		this.directions = directions;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.postalAddressBoundries = postalAddressBoundries;
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
		
    @Column(name="ADDRESS1" , nullable=false, length=150)
    public String getAddress1() 
    {
        return this.address1;
    }
    
    public void setAddress1(String address1) 
    {
        this.address1 = address1;
    }
		
    @Column(name="ADDRESS2" , nullable=false, length=150)
    public String getAddress2() 
    {
        return this.address2;
    }
    
    public void setAddress2(String address2) 
    {
        this.address2 = address2;
    }
		
    @Column(name="DIRECTIONS" , nullable=false)
    public String getDirections() 
    {
        return this.directions;
    }
    
    public void setDirections(String directions) 
    {
        this.directions = directions;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="postalAddress")
    @JsonIgnore
    public Set<PostalAddressBoundry> getPostalAddressBoundries() 
    {
        return this.postalAddressBoundries;
    }
    
    public void setPostalAddressBoundries(Set<PostalAddressBoundry> postalAddressBoundries) 
    {
        this.postalAddressBoundries = postalAddressBoundries;
    }			


}


