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
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;

/**
 * TelecommunicationsNumber 
 * @author Edward Banfa
 */
@Entity
@Table(name="TELECOMMUNICATIONS_NUMBER"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TelecommunicationsNumber  extends BaseEntity implements java.io.Serializable {

	private ContactMechanism contactMechanism;
    private String areaCode;
    private String contactNo;
    private String countryCd;
    private String description;

    public TelecommunicationsNumber() {
    }

    public TelecommunicationsNumber(ContactMechanism contactMechanism, String code, String contactNo, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.contactNo = contactNo;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public TelecommunicationsNumber(ContactMechanism contactMechanism, String code, String areaCode, String contactNo, String countryCd, String description, Date effectiveDt, char recSt) 
    {
		this.contactMechanism = contactMechanism;
		this.code = code;
		this.areaCode = areaCode;
		this.contactNo = contactNo;
		this.countryCd = countryCd;
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
		
    @Column(name="AREA_CODE" , unique=true, length=35)
    public String getAreaCode() 
    {
        return this.areaCode;
    }
    
    public void setAreaCode(String areaCode) 
    {
        this.areaCode = areaCode;
    }
		
    @Column(name="CONTACT_NO" , nullable=false, length=35)
    public String getContactNo() 
    {
        return this.contactNo;
    }
    
    public void setContactNo(String contactNo) 
    {
        this.contactNo = contactNo;
    }
		
    @Column(name="COUNTRY_CD" , unique=true, length=35)
    public String getCountryCd() 
    {
        return this.countryCd;
    }
    
    public void setCountryCd(String countryCd) 
    {
        this.countryCd = countryCd;
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


