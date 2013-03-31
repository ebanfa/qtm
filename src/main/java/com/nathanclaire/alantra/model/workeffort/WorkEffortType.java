/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.model.workeffort;

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
import javax.persistence.OneToMany;
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
 * WorkEffortType 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT_TYPE"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffortType  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<WorkEffort> workEfforts = new HashSet<WorkEffort>(0);

    public WorkEffortType() {
    }

    public WorkEffortType(String code, String name, String description, Date effectiveDt, char recSt) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
    }
    public WorkEffortType(String code, String name, String description, Date effectiveDt, char recSt, Set<WorkEffort> workEfforts ) 
    {
		this.code = code;
		this.name = name;
		this.description = description;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.workEfforts = workEfforts;
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
		
    @Column(name="DESCRIPTION" , nullable=false, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortType")
    @JsonIgnore
    public Set<WorkEffort> getWorkEfforts() 
    {
        return this.workEfforts;
    }
    
    public void setWorkEfforts(Set<WorkEffort> workEfforts) 
    {
        this.workEfforts = workEfforts;
    }			


}


