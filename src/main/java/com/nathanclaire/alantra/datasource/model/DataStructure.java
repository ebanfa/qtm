/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.datasource.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;

/**
 * DataStructure 
 * @author Edward Banfa
 */
@Entity
@Table(name="DATA_STRUCTURE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataStructure  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
    private Character dsStructDelimeter;
    private Character skipFirstFg;
    private Character strictFg;
    private String targetPriEntityCd;
    private String targetSecEntityCd;
	private Set<DataField> dataFields = new HashSet<DataField>(0);
	private Set<Data> datas = new HashSet<Data>(0);

    public DataStructure() {
    }

    public DataStructure(String code, String name, String targetPriEntityCd, String targetSecEntityCd, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.name = name;
		this.targetPriEntityCd = targetPriEntityCd;
		this.targetSecEntityCd = targetSecEntityCd;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public DataStructure(String code, String name, String description, Character dsStructDelimeter, Character skipFirstFg, Character strictFg, String targetPriEntityCd, String targetSecEntityCd, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr, Set<DataField> dataFields, Set<Data> datas ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.dsStructDelimeter = dsStructDelimeter;
		this.skipFirstFg = skipFirstFg;
		this.strictFg = strictFg;
		this.targetPriEntityCd = targetPriEntityCd;
		this.targetSecEntityCd = targetSecEntityCd;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
		this.dataFields = dataFields;
		this.datas = datas;
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
		
    @Column(name="DS_STRUCT_DELIMETER" , unique=true, length=1)
    public Character getDsStructDelimeter() 
    {
        return this.dsStructDelimeter;
    }
    
    public void setDsStructDelimeter(Character dsStructDelimeter) 
    {
        this.dsStructDelimeter = dsStructDelimeter;
    }
		
    @Column(name="SKIP_FIRST_FG" , unique=true, length=1)
    public Character getSkipFirstFg() 
    {
        return this.skipFirstFg;
    }
    
    public void setSkipFirstFg(Character skipFirstFg) 
    {
        this.skipFirstFg = skipFirstFg;
    }
		
    @Column(name="STRICT_FG" , unique=true, length=1)
    public Character getStrictFg() 
    {
        return this.strictFg;
    }
    
    public void setStrictFg(Character strictFg) 
    {
        this.strictFg = strictFg;
    }
		
    @Column(name="TARGET_PRI_ENTITY_CD" , nullable=false, length=75)
    public String getTargetPriEntityCd() 
    {
        return this.targetPriEntityCd;
    }
    
    public void setTargetPriEntityCd(String targetPriEntityCd) 
    {
        this.targetPriEntityCd = targetPriEntityCd;
    }
		
    @Column(name="TARGET_SEC_ENTITY_CD" , nullable=false, length=75)
    public String getTargetSecEntityCd() 
    {
        return this.targetSecEntityCd;
    }
    
    public void setTargetSecEntityCd(String targetSecEntityCd) 
    {
        this.targetSecEntityCd = targetSecEntityCd;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataStructure")
    @JsonIgnore
    public Set<DataField> getDataFields() 
    {
        return this.dataFields;
    }
    
    public void setDataFields(Set<DataField> dataFields) 
    {
        this.dataFields = dataFields;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="dataStructure")
    @JsonIgnore
    public Set<Data> getDatas() 
    {
        return this.datas;
    }
    
    public void setDatas(Set<Data> datas) 
    {
        this.datas = datas;
    }			


}


