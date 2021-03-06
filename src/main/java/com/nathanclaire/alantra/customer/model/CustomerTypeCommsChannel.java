/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.customer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * CustomerTypeNotificationChannel 
 * @author Edward Banfa
 */
@Entity
@Table(name="CUSTOMER_TYPE_COMMUNICATIONS_CHANNEL"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomerTypeCommsChannel  extends BaseEntity implements java.io.Serializable {

	private DataChannel dataChannel;
	private CustomerType customerType;
	private Character defaultChannelFg;

	
    public CustomerTypeCommsChannel() {
    }

    public CustomerTypeCommsChannel(DataChannel dataChannel, CustomerType customerType, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public CustomerTypeCommsChannel(Character defaultChannelFg, DataChannel dataChannel, CustomerType customerType, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.dataChannel = dataChannel;
		this.customerType = customerType;
		this.defaultChannelFg = defaultChannelFg;
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
		this.lastModifiedDt = lastModifiedDt;
		this.lastModifiedUsr = lastModifiedUsr;
    }
    
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CHANNEL_ID", nullable=false)
    @JsonIgnore
    public DataChannel getDataChannel() 
    {
        return this.dataChannel;
    }
    
    public void setDataChannel(DataChannel dataChannel)
    {
        this.dataChannel = dataChannel;
    }
    		
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUST_TY_ID", nullable=false)
    @JsonIgnore
    public CustomerType getCustomerType() 
    {
        return this.customerType;
    }
    
    public void setCustomerType(CustomerType customerType)
    {
        this.customerType = customerType;
    }

	/**
	 * @return the defaultChannelFg
	 */
    @Column(name="DEFAULT_CHANNEL_FG" , nullable=false, length=1)
	public Character getDefaultChannelFg() {
		return defaultChannelFg;
	}

	/**
	 * @param defaultChannelFg the defaultChannelFg to set
	 */
	public void setDefaultChannelFg(Character defaultChannelFg) {
		this.defaultChannelFg = defaultChannelFg;
	}

}


