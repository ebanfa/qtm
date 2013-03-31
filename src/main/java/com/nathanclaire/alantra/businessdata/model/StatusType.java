/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.businessdata.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.IDENTITY;
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
 * StatusType 
 * @author Edward Banfa
 */
@Entity
@Table(name="STATUS_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class StatusType  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;

    public StatusType() {
    }

    public StatusType(String name, String code, Date effectiveDt, char recSt) 
    {
		this.name = name;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public StatusType(String name, String description, String code, Date effectiveDt, char recSt) 
    {
		this.name = name;
		this.description = description;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
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


