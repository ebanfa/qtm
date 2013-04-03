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

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.util.DateDeserializer;
import com.nathanclaire.alantra.base.util.DateSerializer;

/**
 * Currency 
 * @author Edward Banfa
 */
@Entity
@Table(name="CURRENCY"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Currency  extends BaseEntity implements java.io.Serializable {

    private String crncyNm;
    private String crncySym;
    private String remarks;

    public Currency() {
    }

    public Currency(String code, String crncyNm, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.crncyNm = crncyNm;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public Currency(String code, String crncyNm, String crncySym, String remarks, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.crncyNm = crncyNm;
		this.crncySym = crncySym;
		this.remarks = remarks;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    
		
    @Column(name="CRNCY_NM" , nullable=false, length=35)
    public String getCrncyNm() 
    {
        return this.crncyNm;
    }
    
    public void setCrncyNm(String crncyNm) 
    {
        this.crncyNm = crncyNm;
    }
		
    @Column(name="CRNCY_SYM" , unique=true, length=1)
    public String getCrncySym() 
    {
        return this.crncySym;
    }
    
    public void setCrncySym(String crncySym) 
    {
        this.crncySym = crncySym;
    }
		
    @Column(name="REMARKS" , unique=true)
    public String getRemarks() 
    {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }


}


