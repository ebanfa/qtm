/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model.party;

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

import com.nathanclaire.alantra.model.BaseEntity;
import com.nathanclaire.alantra.util.DateDeserializer;
import com.nathanclaire.alantra.util.DateSerializer;

/**
 * ElectronicAddress 
 * @author Edward Banfa
 */
@Entity
@Table(name="ELECTRONIC_ADDRESS"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ElectronicAddress  extends BaseEntity implements java.io.Serializable {

	private ContactMechanism contactMechanism;
    private String elecAddrTxt;
    private String description;

    public ElectronicAddress() {
    }

    public ElectronicAddress(ContactMechanism contactMechanism, String code, String elecAddrTxt, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.elecAddrTxt = elecAddrTxt;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public ElectronicAddress(ContactMechanism contactMechanism, String code, String elecAddrTxt, String description, Date effectiveDt, char recSt) 
    {
		this.contactMechanism = contactMechanism;
		this.code = code;
		this.elecAddrTxt = elecAddrTxt;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
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
		
    @Column(name="ELEC_ADDR_TXT" , nullable=false, length=75)
    public String getElecAddrTxt() 
    {
        return this.elecAddrTxt;
    }
    
    public void setElecAddrTxt(String elecAddrTxt) 
    {
        this.elecAddrTxt = elecAddrTxt;
    }
		
    @Column(name="DESCRIPTION" , unique=true)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }


}


