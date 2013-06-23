/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.model;

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
 * SystemGroup 
 * @author Edward Banfa
 */
@Entity
@Table(name="SYSTEM_GROUP"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SystemGroup  extends BaseEntity implements java.io.Serializable {

    private String name;
    private String description;
	private Set<SystemUserGroup> systemUserGroups = new HashSet<SystemUserGroup>(0);
	private Set<SystemUser> systemUsers = new HashSet<SystemUser>(0);

    public SystemGroup() {
    }

    public SystemGroup(String name, String description, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.name = name;
		this.description = description;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public SystemGroup(String name, String description, Set<SystemUserGroup> systemUserGroups, Set<SystemUser> systemUsers, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.name = name;
		this.description = description;
		this.systemUserGroups = systemUserGroups;
		this.systemUsers = systemUsers;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
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
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="systemGroup")
    @JsonIgnore
    public Set<SystemUserGroup> getSystemUserGroups() 
    {
        return this.systemUserGroups;
    }
    
    public void setSystemUserGroups(Set<SystemUserGroup> systemUserGroups) 
    {
        this.systemUserGroups = systemUserGroups;
    }			
			
    @OneToMany(fetch=FetchType.LAZY, mappedBy="systemGroup")
    @JsonIgnore
    public Set<SystemUser> getSystemUsers() 
    {
        return this.systemUsers;
    }
    
    public void setSystemUsers(Set<SystemUser> systemUsers) 
    {
        this.systemUsers = systemUsers;
    }			


}


