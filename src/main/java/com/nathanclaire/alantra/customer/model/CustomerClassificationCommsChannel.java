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
 * CustomerClassificationNotificationChannel 
 * @author Edward Banfa
 */
@Entity
@Table(name="CUSTOMER_CLASSIFICATION_COMMUNICATIONS_CHANNEL"
    ,catalog="alantra"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomerClassificationCommsChannel  extends BaseEntity implements java.io.Serializable {

	private DataChannel dataChannel;
	private CustomerClassification customerClassification;
	private Character defaultChannelFg;

    public CustomerClassificationCommsChannel() {
    }

    public CustomerClassificationCommsChannel(DataChannel dataChannel, CustomerClassification customerClassification, Character defaultChannelFg, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr) 
    {
		this.code = code;
		this.effectiveDt = effectiveDt;
		this.recSt = recSt;
		this.createdDt = createdDt;
		this.createdByUsr = createdByUsr;
    }
    public CustomerClassificationCommsChannel(DataChannel dataChannel, CustomerClassification customerClassification, Character defaultChannelFg, String code, Date effectiveDt, char recSt, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
		this.dataChannel = dataChannel;
		this.customerClassification = customerClassification;
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
    @JoinColumn(name="CHANNEL_CAT_ID", nullable=false)
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
    @JoinColumn(name="CUST_CLASS_ID", nullable=false)
    @JsonIgnore
    public CustomerClassification getCustomerClassification() 
    {
        return this.customerClassification;
    }
    
    public void setCustomerClassification(CustomerClassification customerClassification)
    {
        this.customerClassification = customerClassification;
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


