/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.model;

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
import com.nathanclaire.alantra.party.model.Party;

/**
 * CustomerBlacklist 
 * @author Edward Banfa
 */
@Entity
@Table(name="CUSTOMER_BLACKLIST"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomerBlacklist  extends BaseEntity implements java.io.Serializable {

	private Party party;
    private String description;

    public CustomerBlacklist() {
    }

    public CustomerBlacklist(Party party, String code, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public CustomerBlacklist(Party party, String code, String description, Date effectiveDt, char recSt) 
    {
		this.party = party;
		this.code = code;
		this.description = description;
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
		
    @Column(name="DESCRIPTION" , unique=true, length=200)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }


}


