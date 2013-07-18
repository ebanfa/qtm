/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.security.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * CurrentUserSession 
 * @author Edward Banfa
 */
@Entity
@Table(name="CURRENT_USER_SESSION"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CurrentUserSession  extends BaseEntity implements java.io.Serializable {

	private SystemUser systemUser;
    private Date loginTime;
    private Date lastActivityTime;

    public CurrentUserSession() {
    }

    public CurrentUserSession(SystemUser systemUser, Date loginTime, Date lastActivityTime, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.loginTime = loginTime;
		this.lastActivityTime = lastActivityTime;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public CurrentUserSession(SystemUser systemUser, Date loginTime, Date lastActivityTime, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.systemUser = systemUser;
		this.loginTime = loginTime;
		this.lastActivityTime = lastActivityTime;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID", nullable=false)
    @JsonIgnore
    public SystemUser getSystemUser() 
    {
        return this.systemUser;
    }
    
    public void setSystemUser(SystemUser systemUser)
    {
        this.systemUser = systemUser;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="LOGIN_TIME" , nullable=false, length=19)
	@JsonSerialize(using = DateSerializer.class)
    public Date getLoginTime() 
    {
        return this.loginTime;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setLoginTime(Date loginTime) 
    {
        this.loginTime = loginTime;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="LAST_ACTIVITY_TIME" , nullable=false, length=19)
	@JsonSerialize(using = DateSerializer.class)
    public Date getLastActivityTime() 
    {
        return this.lastActivityTime;
    }
    
    @JsonDeserialize(using = DateDeserializer.class)
    public void setLastActivityTime(Date lastActivityTime) 
    {
        this.lastActivityTime = lastActivityTime;
    }


}


