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
 * Organization 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORGANIZATION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Organization  extends BaseEntity implements java.io.Serializable {

	private Party party;
    private String taxIdNo;

    public Organization() {
    }

    public Organization(Party party, String code, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public Organization(Party party, String code, String taxIdNo, Date effectiveDt, char recSt) 
    {
		this.party = party;
		this.code = code;
		this.taxIdNo = taxIdNo;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ID", nullable=false)
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
		
    @Column(name="TAX_ID_NO" , unique=true, length=35)
    public String getTaxIdNo() 
    {
        return this.taxIdNo;
    }
    
    public void setTaxIdNo(String taxIdNo) 
    {
        this.taxIdNo = taxIdNo;
    }


}


